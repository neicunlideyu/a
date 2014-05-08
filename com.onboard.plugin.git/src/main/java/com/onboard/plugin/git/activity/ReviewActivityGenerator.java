package com.onboard.plugin.git.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.Activity;
import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.PullRequestActivityActionType;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.ReviewService;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.Review;
import com.onboard.service.activity.ActivityGenerator;
import com.onboard.service.activity.util.ActivityHelper;

@Service("reviewActivityGeneratorBean")
public class ReviewActivityGenerator implements ActivityGenerator {

    public static final String REVIEW_SUBJECT = "在文件中进行了评论";

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PullRequestService pullRequestService;

    @Override
    public String modelType() {
        return new Review().getType();
    }

    @Override
    public String modelService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Identifiable enrichModel(Identifiable identifiable) {
        return reviewService.getReviewById(identifiable.getId());
    }

    private Activity generateActivityByActionType(String actionType, String subject, Review review) {
        Activity activity = ActivityHelper.generateActivityByActionType(actionType, subject, review);

        activity.setContent(ActivityHelper.cutoffActivityContent(ActivityHelper.soup(review.getContent())));

        PullRequest pr = pullRequestService.getPullRequestById(review.getPullRequestId());
        activity.setTarget(pr.getTitle());

        activity.setProjectId(pr.getProjectId());
        activity.setCompanyId(pr.getCompanyId());

        return activity;
    }

    @Override
    public Activity generateCreateActivity(Identifiable item) {
        return generateActivityByActionType(PullRequestActivityActionType.REVIEW, REVIEW_SUBJECT, (Review) item);
    }

    @Override
    public Activity generateUpdateActivity(Identifiable item, Identifiable modifiedItem) {
        // TODO Auto-generated method stub
        return null;
    }

}
