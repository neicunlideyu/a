package com.onboard.plugin.git.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.Activity;
import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.PullRequestActivityActionType;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.utils.PullRequestActivityHelper;
import com.onboard.plugin.git.utils.PullRequestHelper;
import com.onboard.service.activity.ActivityActionType;
import com.onboard.service.activity.ActivityGenerator;
import com.onboard.service.activity.util.ActivityHelper;

/**
 * 生成pullrequest相关信息的辅助类
 * 
 * @author XR
 */

@Service("pullRequestActivityGeneratorBean")
public class PullRequestActivityGenerator implements ActivityGenerator {

    public static final String OPENED_SUBJECT = "打开了PullRequest";
    public static final String MERGE_SUBJECT = "合并了PullRequest";
    public static final String REOPENED_SUBJECT = "重新打开了PullRequest";
    public static final String DECLINE_SUBJECT = "拒绝了PullRequest";

    public static final String REVIEW_SUBJECT = "在文件中进行了评论";

    public static final String COMMENTED_SUBJECT = "进行了评论";

    public static final String APPROVED_SUBJECT = "同意此次PullRequest";

    public static final String UNAPPROVED_SUBJECT = "不同意此次PullRequest";

    @Autowired
    private PullRequestService pullRequestService;

    @Override
    public String modelType() {
        return new PullRequest().getType();
    }

    @Override
    public Identifiable enrichModel(Identifiable identifiable) {
        return pullRequestService.getPullRequestById(identifiable.getId());
    }

    @Override
    public Activity generateCreateActivity(Identifiable item) {
        PullRequest pr = (PullRequest) item;
        Activity activity = PullRequestActivityHelper.generateActivityByActionType(ActivityActionType.CREATE, OPENED_SUBJECT, pr);
        activity.setContent(ActivityHelper.cutoffActivityContent(ActivityHelper.soup(pr.getDescription())));
        return activity;
    }

    @Override
    public Activity generateUpdateActivity(Identifiable item, Identifiable modifiedItem) {
        PullRequest orginal = (PullRequest) item;
        PullRequest updated = (PullRequest) modifiedItem;
        if (PullRequestHelper.isMergeOperation(orginal, updated)) {
            return PullRequestActivityHelper.generateActivityByActionType(PullRequestActivityActionType.MERGED, MERGE_SUBJECT,
                    (PullRequest) modifiedItem);
        } else if (PullRequestHelper.isReopenOperation(orginal, updated)) {
            return PullRequestActivityHelper.generateActivityByActionType(PullRequestActivityActionType.REOPENED,
                    REOPENED_SUBJECT, (PullRequest) modifiedItem);
        } else if (PullRequestHelper.isDeclineOperation(orginal, updated)) {
            return PullRequestActivityHelper.generateActivityByActionType(PullRequestActivityActionType.DECLINE, DECLINE_SUBJECT,
                    (PullRequest) modifiedItem);
        }
        return null;
    }

    @Override
    public String modelService() {

        return null;
    }

}
