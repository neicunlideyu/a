package com.onboard.plugin.git.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.onboard.service.notification.NotificationRule;

@Service("declinePullRequestEmailNotificationBean")
public class DeclinePullRequestEmailNotification extends AbstractPullRequestOperationEmailNotificatioin {

    @Autowired
    @Qualifier("declinePullRequestNotificationRuleBean")
    private NotificationRule notificationRule;

    @Override
    public NotificationRule getNotificationRule() {
        return notificationRule;
    }

    @Override
    protected String getOperation() {
        return "取消了";
    }

}
