package com.onboard.plugin.git.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.domain.model.User;
import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.PullRequestReviewerService;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.mapper.PullRequestExample;
import com.onboard.plugin.git.mapper.PullRequestMapper;
import com.onboard.plugin.git.model.BasicCommit;
import com.onboard.plugin.git.model.Diff;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.PullRequest.Status;
import com.onboard.plugin.git.utils.CommitUtils;
import com.onboard.plugin.git.utils.CommonUtil;
import com.onboard.plugin.git.utils.GitOperation;
import com.onboard.service.collaboration.SubscriberService;
import com.onboard.service.collaboration.identifiable.DefaultIdentifiableService;
import com.onboard.service.web.SessionService;

@Transactional
@Service("pullRequestServiceBean")
public class PullRequestServiceImpl extends DefaultIdentifiableService implements PullRequestService {

    private static final Logger logger = LoggerFactory.getLogger(PullRequestServiceImpl.class);

    private static final String DEFAULT_PULL_REQUEST_URL_TEMPLATE = "/%s/projects/%s/repos/%s/pull-requests/%s/overview";

    @Autowired
    private PullRequestMapper pullRequestMapper;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private PullRequestReviewerService pullRequestReviewerService;

    @Autowired
    private GitOperation gitOp;

    @Autowired
    private SessionService session;

    @Autowired
    private CommitUtils commitUtils;

    @Override
    public List<PullRequest> getPullRequestListInRepository(int repoId, Integer status) {
        return getPullRequestListInRepositoryByStartLimit(repoId, status, -1, -1);
    }

    @Override
    public List<PullRequest> getPullRequestListInRepositoryByStartLimit(int repoId, Integer status, int start, int limit) {
        PullRequest sample = new PullRequest();
        sample.setRepositoryId(repoId);
        sample.setDeleted(false);
        sample.setStatus(status);
        PullRequestExample example = new PullRequestExample(sample);
        example.setStart(start);
        example.setLimit(limit);
        example.setOrderByClause("id desc");

        List<PullRequest> pullRequestList = pullRequestMapper.selectByExample(example);
        List<PullRequest> result = pullRequestList;
        for (PullRequest pullRequest : result) {
            pullRequestReviewerService.fillPullRequestReviewers(pullRequest);
        }

        return result;
    }

    @Override
    public PullRequest getPullRequestById(int id) {
        PullRequest obj = pullRequestMapper.selectByPrimaryKey(id);
        if (obj == null) {
            return null;
        }
        PullRequest pullRequest = new PullRequest(obj);
        pullRequestReviewerService.fillPullRequestReviewers(pullRequest);
        subscriberService.fillSubcribers(pullRequest);
        // commentService.fillCommentable(pullRequest, 0, -1);
        return pullRequest;

    }

    @Override
    public List<BasicCommit> getCommitsOfPullRequest(PullRequest pullRequest) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(pullRequest.getRepositoryId());
            Iterable<RevCommit> commits = gitOp.getCommits(repository, pullRequest.getDestinationHash(),
                    pullRequest.getSourceHash());
            List<BasicCommit> ret = new ArrayList<BasicCommit>();
            for (RevCommit commit : commits) {
                ret.add(CommitUtils.commitToMap(commit));
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null) {
                repository.close();
            }
        }
    }

    private int getScopeIdOfNewRequest(PullRequest pr) {
        PullRequest sample = new PullRequest();
        sample.setRepositoryId(pr.getRepositoryId());
        PullRequestExample example = new PullRequestExample(sample);
        example.setLimit(1);
        example.setOrderByClause("scopeId desc");
        List<PullRequest> result = pullRequestMapper.selectByExample(example);
        if (result == null || result.size() == 0) {
            return 1;
        }

        return result.get(0).getScopeId() + 1;
    }

    @Override
    public PullRequest createPullRequest(PullRequest pr) {
        pr.setCreated(new Date());
        pr.setUpdated(pr.getCreated());
        pr.setDeleted(false);
        pr.setStatus(Status.OPEN.value());
        pr.setCreatorId(session.getCurrentUser().getId());
        pr.setCreatorName(session.getCurrentUser().getName());
        pr.setTodoId(1);
        pr.setScopeId(getScopeIdOfNewRequest(pr));
        ObjectId sourceLastCommitId = repositoryService.getBranchObjectId(pr.getRepositoryId(), pr.getSource());
        pr.setSourceHash(sourceLastCommitId.getName());
        // set destinationHash as merge-base
        setPullRequestDestinationHash(pr);
        pullRequestMapper.insert(pr);
        if (pr.getSubscribers() == null) {
            pr.setSubscribers(new ArrayList<User>());
        }
        subscriberService.generateSubscribers(pr, session.getCurrentUser());
        subscriberService.addSubscribers(pr);
        return pr;
    }

    @Override
    public List<Diff> getDiffOfPullRequest(int repoId, int pullRequestId) {

        return null;
    }

    @Override
    public PullRequest updatePullRequest(PullRequest pullRequest) {
        PullRequest orginal = pullRequestMapper.selectByPrimaryKey(pullRequest.getId());
        pullRequestMapper.updateByPrimaryKeySelective(pullRequest);
        // 生成activity
        if (!orginal.getStatus().equals(pullRequest.getStatus())) {
            if (pullRequest.getStatus().equals(PullRequest.Status.OPEN.value())) {
                // after reopen, update sourcehash and destinationHash in pullrequest
                PullRequest updated = new PullRequest(pullRequestMapper.selectByPrimaryKey(pullRequest.getId()));
                ObjectId sourceLastCommitId = repositoryService.getBranchObjectId(updated.getRepositoryId(), updated.getSource());
                this.updatePullRequestAfterCommit(updated, sourceLastCommitId);
            }
            if (pullRequest.getStatus().equals(PullRequest.Status.MERGED.value())) {
                this.merge(pullRequest);
            }
        }
        return pullRequest;
    }

    private List<DiffEntry> getDiffOfPullRequest(PullRequest pullRequest) throws IOException {

        org.eclipse.jgit.lib.Repository repository = commonUtil.getRepository(pullRequest.getRepositoryId());
        RevWalk revWalk = new RevWalk(repository);
        RevCommit sourceCommit = revWalk.parseCommit(ObjectId.fromString(pullRequest.getSourceHash()));
        RevCommit destinationCommit = revWalk.parseCommit(ObjectId.fromString(pullRequest.getDestinationHash()));

        DiffFormatter diffFmt = new DiffFormatter(new ByteArrayOutputStream());
        diffFmt.setRepository(repository);
        return diffFmt.scan(destinationCommit.getTree(), sourceCommit.getTree());
    }

    @Override
    public void fillPullRequestFileTree(PullRequest pullRequest) {
        try {
            pullRequest.setFileTree(commonUtil.parseJsonTree(getDiffOfPullRequest(pullRequest)));
        } catch (JsonGenerationException e) {
            logger.error("Fail to fetch file tree of the pull request: ", e);
        } catch (JsonMappingException e) {
            logger.error("Fail to fetch file tree of the pull request: ", e);
        } catch (IOException e) {
            logger.error("Fail to fetch file tree of the pull request: ", e);
        }

    }

    @Override
    public boolean canMerge(PullRequest pullRequest) {
        org.eclipse.jgit.lib.Repository r = null;
        try {
            r = commonUtil.getRepository(pullRequest.getRepositoryId());
            return gitOp.canMerge(r, pullRequest.getSource(), pullRequest.getDestination());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    private void merge(PullRequest pullRequest) {

        org.eclipse.jgit.lib.Repository r = null;
        try {
            r = commonUtil.getRepository(pullRequest.getRepositoryId());
            gitOp.merge(r, pullRequest.getSource(), pullRequest.getDestination());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    @Override
    public List<PullRequest> getPullRequestsByExample(PullRequest pr, int start, int limit) {
        PullRequestExample example = new PullRequestExample(pr);
        example.setLimit(limit);
        example.setStart(start);
        return pullRequestMapper.selectByExample(example);
    }

    @Override
    public Identifiable getIdentifiableById(Integer id) {
        return this.pullRequestMapper.selectByPrimaryKey(id);
    }

    @Override
    public String modelType() {
        return new PullRequest().getType();
    }

    @Override
    public String getIdentifiableURI(Identifiable identifiable) {
        PullRequest pullRequest = (PullRequest) identifiable;
        return String.format(DEFAULT_PULL_REQUEST_URL_TEMPLATE, pullRequest.getCompanyId(), pullRequest.getProjectId(),
                pullRequest.getRepositoryId(), pullRequest.getId());
    }

    @Override
    public void updatePullRequestAfterCommit(PullRequest pullRequest, ObjectId newLastCommitId) {
        // update source hash
        pullRequest.setSourceHash(ObjectId.toString(newLastCommitId));
        // update destination hash
        setPullRequestDestinationHash(pullRequest);
        this.updatePullRequest(pullRequest);
    }

    private void setPullRequestDestinationHash(PullRequest pullRequest) {
        ObjectId destinationLastCommitId = repositoryService.getBranchObjectId(pullRequest.getRepositoryId(),
                pullRequest.getDestination());
        RevCommit base = commitUtils.getBaseCommit(pullRequest.getRepositoryId(),
                ObjectId.fromString(pullRequest.getSourceHash()), destinationLastCommitId);
        // if no merge base, destinationHash should be destinationLastCommitId
        if (base == null) {
            pullRequest.setDestinationHash(destinationLastCommitId.getName());
        } else {
            pullRequest.setDestinationHash(base.getId().getName());
        }
    }

    @Override
    public int countPullReqestsByRepoIdStatus(int repoId, int status) {
        PullRequest pullRequest = new PullRequest(repoId);
        pullRequest.setDeleted(false);
        pullRequest.setStatus(status);
        return pullRequestMapper.countByExample(new PullRequestExample(pullRequest));
    }
}
