package com.onboard.plugin.git.notification;

import org.springframework.stereotype.Service;

import com.onboard.domain.model.Activity;
import com.onboard.domain.model.type.Subscribable;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.utils.PullRequestHelper;
import com.onboard.service.notification.NotificationRule;

@Service("declinePullRequestNotificationRuleBean")
public class DeclinePullRequestNotificationRule implements NotificationRule {

    @Override
    public String modelType() {
        return new PullRequest().getType();
    }

    @Override
    public boolean ifNotify(Activity activity, Subscribable subscribable) {
        return false;
    }

    @Override
    public boolean ifNotify(Activity activity, Subscribable original, Subscribable updated) {
        return PullRequestHelper.isDeclineOperation((PullRequest) original, (PullRequest) updated);
    }

}
