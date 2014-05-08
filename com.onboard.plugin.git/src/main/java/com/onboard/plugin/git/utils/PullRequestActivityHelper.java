package com.onboard.plugin.git.utils;

import com.onboard.domain.model.Activity;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.service.activity.util.ActivityHelper;

public class PullRequestActivityHelper {

    public static Activity generateActivityByActionType(String actionType, String subject, PullRequest pr) {
        Activity activity = ActivityHelper.generateActivityByActionType(actionType, subject, pr);

        activity.setTarget(pr.getTitle());

        activity.setProjectId(pr.getProjectId());
        activity.setCompanyId(pr.getCompanyId());

        return activity;
    }

}
