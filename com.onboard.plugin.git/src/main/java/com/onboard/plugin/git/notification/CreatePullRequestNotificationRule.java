package com.onboard.plugin.git.notification;

import org.springframework.stereotype.Service;

import com.onboard.domain.model.Activity;
import com.onboard.domain.model.type.Subscribable;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.service.activity.ActivityActionType;
import com.onboard.service.notification.SimpleNotificationRule;

@Service("createPullRequestNotificationRuleBean")
public class CreatePullRequestNotificationRule extends SimpleNotificationRule {

    @Override
    public String modelType() {
        return new PullRequest().getType();
    }

    @Override
    public boolean ifNotify(Activity activity, Subscribable subscribable) {
        return activity.getAction().equals(ActivityActionType.CREATE);
    }

}
