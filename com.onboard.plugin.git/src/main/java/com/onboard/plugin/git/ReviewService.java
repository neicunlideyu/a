package com.onboard.plugin.git;

import java.util.List;

import com.onboard.plugin.git.model.Diff;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.Review;

/**
 * {@link Review} Service Interface
 * 
 * @generated_by_elevenframework
 * 
 */
public interface ReviewService {
    /**
     * Get item by id
     * 
     * @param id
     * @return item
     */
    Review getReviewById(int id);

    /**
     * Get item list
     * 
     * @param start
     * @param limit
     * @return the item list
     */
    List<Review> getReviews(int start, int limit);

    /**
     * Get item list by example
     * 
     * @param item
     * @param start
     * @param limit
     * @return the item list
     */
    List<Review> getReviewsByExample(Review item, int start, int limit);

    /**
     * Get item count by example
     * 
     * @param item
     * @return the count
     */
    int countByExample(Review item);

    /**
     * Create
     * 
     * @param item
     * @return the created Review
     */
    Review createReview(Review item);

    /**
     * Update
     * 
     * @param item
     * @return the updated item
     */
    Review updateReview(Review item);

    /**
     * Delete
     * 
     * @param id
     */
    void deleteReview(int id);

    /**
     * 通过pullRequestId获取reviews
     * 
     * @param pullRequestId
     * @return
     */
    List<Review> getReviewsByPullRequestId(int pullRequestId);

    /**
     * 获取带有评论的diff
     * 
     * @param review
     * @return
     */
    Diff getDiffWithReview(Review review);

    /**
     * push后更新review信息
     * 
     * @param pullRequest
     * @param since
     * @param until
     */
    void updateReviewAfterPush(PullRequest pullRequest, String since, String until);
}
