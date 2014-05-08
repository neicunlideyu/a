package com.onboard.plugin.git;

import java.io.IOException;
import java.util.List;

import com.onboard.domain.model.Activity;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.PullRequestActivity;

/**
 * {@link PullRequestActivity} Service Interface
 * 
 * @generated_by_elevenframework
 * 
 */
public interface PullRequestActivityService {

    /**
     * 获取pullRequestId下的所有activity
     * 
     * @param pullRequestId
     * @param start
     * @param limit
     * @return
     * @throws IOException
     */
    List<Activity> getActivitiesByPullRequestId(Integer pullrequestId);

    /**
     * 当approve时创建activity
     * 
     * @param pullRequest
     * @param user
     * @return
     */
    Activity createActivityWithApprove(PullRequest pullRequest);

    /**
     * 当unapprove时创建activity
     * 
     * @param pullRequest
     * @param user
     * @return
     */
    Activity createActivityWithUnApprove(PullRequest pullRequest);

}
