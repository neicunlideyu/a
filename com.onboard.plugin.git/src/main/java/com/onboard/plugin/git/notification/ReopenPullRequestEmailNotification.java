package com.onboard.plugin.git.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.onboard.service.notification.NotificationRule;

@Service("reopenPullRequestEmailNotificationBean")
public class ReopenPullRequestEmailNotification extends AbstractPullRequestOperationEmailNotificatioin {

    @Autowired
    @Qualifier("reopenPullRequestNotificationRuleBean")
    private NotificationRule notificationRule;

    @Override
    public NotificationRule getNotificationRule() {
        return this.notificationRule;
    }

    @Override
    protected String getOperation() {
        return "再次打开了";
    }

}
