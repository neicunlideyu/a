package com.onboard.plugin.git.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.domain.model.User;
import com.onboard.plugin.git.PullRequestReviewerService;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.mapper.PullRequestReviewerExample;
import com.onboard.plugin.git.mapper.PullRequestReviewerMapper;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.PullRequestReviewer;
import com.onboard.service.account.UserService;
import com.onboard.service.collaboration.SubscriberService;

@Transactional
@Service("pullRequestReviewerServiceBean")
public class PullRequestReviewerServiceImpl implements PullRequestReviewerService {

    @Autowired
    private PullRequestReviewerMapper pullRequestReviewerMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PullRequestService pullRequestService;

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public PullRequestReviewer getPullRequestReviewerById(int id) {
        return pullRequestReviewerMapper.selectByPrimaryKey(id);
    }

    private boolean canUserReview(User user, PullRequest pullRequest) {
        if (user.getId().equals(pullRequest.getCreatorId())) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> getUsersCanReviewByPullRequestId(int pullRequestId) {
        PullRequest pr = pullRequestService.getPullRequestById(pullRequestId);
        List<User> users = userService.getUserByProjectId(pr.getProjectId());
        List<User> usersCanReview = new ArrayList<User>();
        for (User user : users) {
            if (canUserReview(user, pr)) {
                usersCanReview.add(user);
            }
        }
        return usersCanReview;
    }

    @Override
    public void createPullRequestReviewer(PullRequest pullRequest, List<Integer> reviewerIds) {
        PullRequestReviewer pullRequestReviewer = new PullRequestReviewer();
        pullRequestReviewer.setProjectId(pullRequest.getProjectId());
        pullRequestReviewer.setPullRequestId(pullRequest.getId());
        if (reviewerIds != null && reviewerIds.size() > 0) {
            for (Integer reviewerId : reviewerIds) {
                pullRequest.getSubscribers().add(new User(reviewerId));
                pullRequestReviewer.setReviewerId(reviewerId);
                pullRequestReviewerMapper.insert(pullRequestReviewer);
            }
            subscriberService.addSubscribers(pullRequest);
        }
    }

    @Override
    public void deletePullRequestReviewer(PullRequestReviewer pullRequestReviewer) {
        pullRequestReviewerMapper.deleteByPrimaryKey(pullRequestReviewer.getId());
    }

    @Override
    public void updatePullRequestReviewer(PullRequestReviewer pullRequestReviewer) {
        PullRequestReviewer sample = new PullRequestReviewer(pullRequestReviewer);
        sample.setApproved(null);
        pullRequestReviewerMapper.updateByExampleSelective(pullRequestReviewer, new PullRequestReviewerExample(sample));
    }

    @Override
    public void deletePullRequestReviewerByPullRequest(PullRequest pullRequest) {
        fillPullRequestReviewers(pullRequest);
        for (PullRequestReviewer pullRequestReviewer : pullRequest.getPullRequestReviewers()) {
            this.deletePullRequestReviewer(pullRequestReviewer);
        }
    }

    @Override
    public boolean isReviewer(PullRequest pullRequest, User user) {
        for (PullRequestReviewer p : pullRequest.getPullRequestReviewers()) {
            if (p.getReviewerId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void fillPullRequestReviewers(PullRequest pullRequest) {
        PullRequestReviewer pullRequestReviewer = new PullRequestReviewer();
        pullRequestReviewer.setPullRequestId(pullRequest.getId());
        List<PullRequestReviewer> pullRequestReviewerList = pullRequestReviewerMapper
                .selectByExample(new PullRequestReviewerExample(pullRequestReviewer));
        pullRequest.setPullRequestReviewers(pullRequestReviewerList);
    }

    @Override
    public List<User> getReviewersByPullRequestId(int pullRequestId) {
        PullRequestReviewer pullRequestReviewer = new PullRequestReviewer();
        pullRequestReviewer.setPullRequestId(pullRequestId);
        List<PullRequestReviewer> pullRequestReviewerList = pullRequestReviewerMapper
                .selectByExample(new PullRequestReviewerExample(pullRequestReviewer));
        List<User> users = new ArrayList<User>();
        for (PullRequestReviewer prr : pullRequestReviewerList) {
            users.add(userService.getUserById(prr.getReviewerId()));
        }
        return users;
    }

}
