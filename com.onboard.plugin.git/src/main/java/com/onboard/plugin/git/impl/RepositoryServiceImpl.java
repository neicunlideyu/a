package com.onboard.plugin.git.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.mapper.RepositoryExample;
import com.onboard.plugin.git.mapper.RepositoryMapper;
import com.onboard.plugin.git.model.*;
import com.onboard.plugin.git.utils.CommitUtils;
import com.onboard.plugin.git.utils.CommonUtil;
import com.onboard.plugin.git.utils.GitOperation;
import com.onboard.plugin.git.utils.GitShow;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Transactional
@Service("repositoryServiceBean")
public class RepositoryServiceImpl implements RepositoryService {

    public static final Logger logger = LoggerFactory.getLogger(RepositoryServiceImpl.class);

    @Autowired
    private GitOperation gitOp;

    @Autowired
    private RepositoryMapper repositoryMapper;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public Repository createRepository(Repository repository) {
        repository.setSlug(repository.getName().trim());
        repository.setCreated(new Date());
        repository.setUpdated(repository.getCreated());
        repository.setDeleted(false);
        repositoryMapper.insert(repository);
        gitOp.createRepository(repository.getId());
        return repository;
    }

    @Override
    public Repository getRepositoryById(int id) {
        return repositoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Repository> getRepositoriesByProjectId(int projectId) {
        Repository sample = new Repository();
        sample.setDeleted(false);
        sample.setProjectId(projectId);
        List<Repository> repos = repositoryMapper.selectByExample(new RepositoryExample(sample));

        if (repos == null || repos.isEmpty()) {
            return Lists.newArrayList();
        }

        // Sort Repository by Updated time descending
        Collections.sort(repos, new Comparator<Repository>() {
            @Override
            public int compare(Repository o1, Repository o2) {
                // Sort Repository by Updated time descending
                return (o2.getUpdated().compareTo(o1.getUpdated()));
            }
        });

        for (Repository repo : repos) {
            if (repo.getUpdatedBranch() != null) {
                List<BasicCommit> commits = this.getCommitsOfBranch(repo.getId(), repo.getUpdatedBranch());
                repo.setLastCommit(commits.get(0));
            }
        }
        return repos;
    }

    @Override
    public List<BasicCommit> getCommitsOfBranch(int repoId, String branchRef) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            Iterable<RevCommit> commits = gitOp.getCommits(repository, null, branchRef);
            List<BasicCommit> ret = new ArrayList<BasicCommit>();
            for (RevCommit commit : commits) {
                ret.add(CommitUtils.commitToMap(commit));
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public int countCommitsOfBranch(int repoId, String branchRef) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            Iterable<RevCommit> commits = gitOp.getCommits(repository, null, branchRef);
            return Iterables.size(commits);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public List<BasicCommit> getCommitsOfBranchByStartLimit(int repoId, String branchRef, int start, int limit) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            Iterable<RevCommit> commits = gitOp.getCommitsByStartLimit(repository, null, branchRef, start, limit);

            List<BasicCommit> ret = new ArrayList<BasicCommit>();
            for (RevCommit commit : commits) {
                ret.add(CommitUtils.commitToMap(commit));
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public List<BasicCommit> getCommitsBetweenBranch(int repoId, String since, String until) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            Iterable<RevCommit> commits = gitOp.getCommits(repository, since, until);
            List<BasicCommit> ret = new ArrayList<BasicCommit>();
            for (RevCommit commit : commits) {
                ret.add(CommitUtils.commitToMap(commit));
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public List<Branch> getBranchesOfRepository(int repoId) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            List<Ref> branches = gitOp.getBranches(repository);
            List<Branch> ret = new ArrayList<Branch>();
            for (Ref branch : branches) {
                ret.add(new Branch(branch.getObjectId().getName(), branch.getName()));
            }
            // sort branch by display name
            Collections.sort(ret, new Comparator<Branch>() {
                @Override
                public int compare(Branch o1, Branch o2) {
                    return o1.getDisplayName().toLowerCase().compareTo(o2.getDisplayName().toLowerCase());
                }
            });
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public List<Tag> getTagsOfRepository(int repoId) {
        org.eclipse.jgit.lib.Repository r = null;
        try {
            r = commonUtil.getRepository(repoId);
            List<Ref> tags = gitOp.getTags(r);
            List<Tag> ret = new ArrayList<Tag>();
            for (Ref tag : tags) {
                ret.add(new Tag(tag.getObjectId().getName(), tag.getName()));
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (r != null)
                r.close();
        }
    }

    @Override
    public int getPathTypeInRepository(int repoId, String path, String revStr) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            return gitOp.getPathType(repository, revStr, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public String getFileContentInRepository(int repoId, String path, String revStr) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            ObjectId id = repository.resolve(String.format("%s:%s", revStr, path));
            RevWalk rw = new RevWalk(repository);
            return GitShow.show(repository, rw.parseAny(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public List<Item> getFileListInRepository(int repoId, String path, String revStr) {
        List<Item> ret = new ArrayList<Item>();
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            ObjectId id = repository.resolve(String.format("%s:%s", revStr, path));
            logger.debug(String.format("%s:%s", revStr, path));
            logger.debug("{}", id);
            List<String> result = GitShow.show(repository, (RevTree) new RevWalk(repository).parseAny(id));
            for (String str : result) {
                int type = str.endsWith("/") ? Constants.OBJ_TREE : Constants.OBJ_BLOB;
                str = str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
                ret.add(new Item(type, str));
            }

            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public BasicCommit getCommitByRevisionId(int repoId, String revId) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            ObjectId id = repository.resolve(revId);
            RevWalk revWalk = new RevWalk(repository);
            RevCommit commit = revWalk.parseCommit(id);
            logger.debug("{}", commit);
            logger.debug("{}", commit.getFullMessage());
            BasicCommit ret = CommitUtils.commitToMap(commit);
            ret.setFileTree(commonUtil.parseJsonTree(gitOp.getDiffOfCommit(repository, revWalk, commit)));
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    private List<Diff> getDiffStringformat(int repoId, String path, String since, String until) {
        org.eclipse.jgit.lib.Repository repository = null;
        try {
            repository = commonUtil.getRepository(repoId);
            RevWalk revWalk = new RevWalk(repository);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DiffFormatter df = new DiffFormatter(out);
            df.setRepository(repository);
            df.setContext(10);
            df.setPathFilter(PathFilter.create(path));
            List<Diff> ret = new ArrayList<Diff>();
            List<DiffEntry> entries;
            if (since != null && since.length() > 0) {
                entries = df.scan(repository.resolve(since), repository.resolve(until));
            } else {
                entries = df.scan(new EmptyTreeIterator(), new CanonicalTreeParser(null, revWalk.getObjectReader(), revWalk
                        .parseCommit(repository.resolve(until)).getTree()));
            }
            for (DiffEntry diff : entries) {
                df.format(diff);
                ret.addAll(Diff.parseDiff(out.toString()));
                out.reset();
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null)
                repository.close();
        }
    }

    @Override
    public List<Diff> getDiffOfCommit(int repoId, String path, String since, String until) {
        return this.getDiffStringformat(repoId, path, since, until);
    }

    @Override
    public boolean isEmptyRepository(Repository repository) {
        org.eclipse.jgit.lib.Repository r = null;
        try {
            r = commonUtil.getRepository(repository.getId());
            if (r != null) {
                Ref head = r.getRef(Constants.HEAD);
                return head.getObjectId() == null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (r != null)
                r.close();
        }
        return true;
    }

    @Override
    public ObjectId getBranchObjectId(int repoId, String refName) {
        org.eclipse.jgit.lib.Repository r = null;
        try {
            r = commonUtil.getRepository(repoId);
            return r.resolve(refName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (r != null)
                r.close();
        }
    }

    @Override
    public Repository getRepositoryByRepo(org.eclipse.jgit.lib.Repository repo) {
        return this.getRepositoryById(commonUtil.getRepoIdByPaht(repo.getDirectory().getPath()));
    }

    @Override
    public void updateRepositoryWithPush(org.eclipse.jgit.lib.Repository repo, String refName) {
        Repository repository = this.getRepositoryByRepo(repo);
        repository.setUpdatedBranch(refName);
        repository.setUpdated(new Date());
        repositoryMapper.updateByPrimaryKey(repository);
    }

    @Override
    public boolean isRepositoryExists(int companyId, int projectId, String repositoryName) {

        Repository repository = new Repository();
        repository.setCompanyId(companyId);
        repository.setProjectId(projectId);
        repository.setName(repositoryName);

        RepositoryExample example = new RepositoryExample(repository);
        List<Repository> list = repositoryMapper.selectByExample(example);

        return list.size() > 0;
    }
}
