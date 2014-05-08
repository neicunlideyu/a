package com.onboard.plugin.git;

import java.util.List;

import com.onboard.plugin.git.model.PullRequestPush;

/**
 * {@link PullRequestPush} Service Interface
 * 
 * @generated_by_elevenframework
 * 
 */
public interface PullRequestPushService {
    /**
     * Get item by id
     * 
     * @param id
     * @return item
     */
    PullRequestPush getPullRequestPushById(int id);

    /**
     * Get item list
     * 
     * @param start
     * @param limit
     * @return the item list
     */
    List<PullRequestPush> getPullRequestPushes(int start, int limit);

    /**
     * Get item list by example
     * 
     * @param item
     * @param start
     * @param limit
     * @return the item list
     */
    List<PullRequestPush> getPullRequestPushesByExample(PullRequestPush item, int start, int limit);

    /**
     * Get item count by example
     * 
     * @param item
     * @return the count
     */
    int countByExample(PullRequestPush item);


    /**
     * Create
     * 
     * @param item
     * @return the created PullRequestPush
     */
    PullRequestPush createPullRequestPush(PullRequestPush item);

    /**
     * Update
     * 
     * @param item
     * @return the updated item
     */
    PullRequestPush updatePullRequestPush(PullRequestPush item);

    /**
     * Delete
     * 
     * @param id
     */
    void deletePullRequestPush(int id);
}
