package com.onboard.plugin.git.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.domain.mapper.model.ActivityExample;
import com.onboard.domain.model.Activity;
import com.onboard.plugin.git.PullRequestActivityActionType;
import com.onboard.plugin.git.PullRequestActivityService;
import com.onboard.plugin.git.PullRequestPushService;
import com.onboard.plugin.git.ReviewService;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.PullRequestPush;
import com.onboard.plugin.git.model.Push;
import com.onboard.plugin.git.model.Review;
import com.onboard.plugin.git.utils.PullRequestActivityHelper;
import com.onboard.service.activity.ActivityService;
import com.onboard.service.collaboration.identifiable.IdentifiableManager;

/**
 * {@link com.teamforge.service.git.PullRequestActivityService} Service implementation
 * 
 * @generated_by_elevenframework
 */
@Transactional
@Service("pullRequestActivityServiceBean")
public class PullRequestActivityServiceImpl implements PullRequestActivityService {

    public static final Logger logger = LoggerFactory.getLogger(PullRequestActivityServiceImpl.class);

    public static final String APPROVED_SUBJECT = "同意此次PullRequest";

    public static final String UNAPPROVED_SUBJECT = "不同意此次PullRequest";

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PullRequestPushService pullRequestPushService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private IdentifiableManager identifiableManager;

    private List<Integer> getPushIdsForSpecailPullRequest(Integer pullrequestId) {
        PullRequestPush pullRequestPush = new PullRequestPush();
        pullRequestPush.setPullRequestId(pullrequestId);
        List<PullRequestPush> pullRequestPushs = pullRequestPushService.getPullRequestPushesByExample(pullRequestPush, 0, -1);
        List<Integer> ids = new ArrayList<Integer>();
        for (PullRequestPush p : pullRequestPushs) {
            ids.add(p.getPushId());
        }
        return ids;
    }

    private List<Integer> getReviewIdsForSpecailPullRequest(Integer pullrequestId) {
        Review review = new Review();
        review.setPullRequestId(pullrequestId);
        List<Review> reviews = reviewService.getReviewsByExample(review, 0, -1);
        List<Integer> ids = new ArrayList<Integer>();
        for (Review r : reviews) {
            ids.add(r.getId());
        }
        return ids;
    }

    @Override
    public List<Activity> getActivitiesByPullRequestId(Integer pullrequestId) {
        /**
         * TODO : TEMP FIX
         */
        List<Integer> pushIds = getPushIdsForSpecailPullRequest(pullrequestId);
        List<Integer> reviewIds = getReviewIdsForSpecailPullRequest(pullrequestId);
        Activity activity = new Activity();
        activity.setAttachType(new PullRequest().getType());
        activity.setAttachId(pullrequestId);
        ActivityExample example = new ActivityExample(activity);
        if (!pushIds.isEmpty()) {
            example.or().andAttachTypeEqualTo(new Push().getType()).andAttachIdIn(pushIds);
        }
        if (!reviewIds.isEmpty()) {
            example.or().andAttachTypeEqualTo(new Review().getType()).andAttachIdIn(reviewIds);
        }
        example.setOrderByClause("id desc");
        List<Activity> activities = activityService.getByActivityExample(example);
        // 填充activity
        for (Activity a : activities) {
            a.setAttachObject(identifiableManager.getIdentifiableWithDetailByTypeAndId(a.getAttachType(), a.getAttachId()));
        }
        return activities;
    }

    @Override
    public Activity createActivityWithApprove(PullRequest pullRequest) {
        Activity activity = PullRequestActivityHelper.generateActivityByActionType(PullRequestActivityActionType.APPROVED,
                APPROVED_SUBJECT, pullRequest);
        activity = activityService.create(activity);
        return activity;
    }

    @Override
    public Activity createActivityWithUnApprove(PullRequest pullRequest) {
        Activity activity = PullRequestActivityHelper.generateActivityByActionType(PullRequestActivityActionType.UNAPPROVED,
                UNAPPROVED_SUBJECT, pullRequest);
        activity = activityService.create(activity);
        return activity;
    }
}
