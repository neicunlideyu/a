package com.onboard.plugin.git;

import java.util.List;

import com.onboard.domain.model.User;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.PullRequestReviewer;

;

/**
 * {@link PullRequestReviewer} Service
 * 
 * @author XR
 * 
 */
public interface PullRequestReviewerService {

    /**
     * 根据PullRequestReviewerId获取PullRequestReviewer
     * 
     * @param id
     * @return
     */
    PullRequestReviewer getPullRequestReviewerById(int id);

    /**
     * 获取所有能对某个pullrequest的用户列表
     * 
     * @param pullRequestId
     * @return
     */
    List<User> getUsersCanReviewByPullRequestId(int pullRequestId);

    /**
     * 为某个pullrequest创建PullRequestReviewer
     * 
     * @param pullRequest
     * @param reviewerIds
     * @return
     */
    void createPullRequestReviewer(PullRequest pullRequest, List<Integer> reviewerIds);

    /**
     * 删除PullRequestReviewer
     * 
     * @param pullRequestReviewer
     * @return
     */
    void deletePullRequestReviewer(PullRequestReviewer pullRequestReviewer);

    /**
     * 删除某个PullRequest的所有PullRequestReviewer
     * 
     * @param pullRequest
     */
    void deletePullRequestReviewerByPullRequest(PullRequest pullRequest);

    /**
     * 更新PullRequestReviewer
     * 
     * @param pullRequestReviewer
     */
    void updatePullRequestReviewer(PullRequestReviewer pullRequestReviewer);

    /**
     * 判断用户是否为PullRequestReviewer
     * 
     * @param pullRequest
     * @param user
     * @return
     */
    boolean isReviewer(PullRequest pullRequest, User user);

    /**
     * 为pullrequest填充
     * 
     * @param pullRequestPullRequestReviewer
     */
    void fillPullRequestReviewers(PullRequest pullRequest);

    /**
     * 获取某个pullrequest的Reviewer列表
     * 
     * @param pullRequestId
     * @return
     */
    List<User> getReviewersByPullRequestId(int pullRequestId);

}
