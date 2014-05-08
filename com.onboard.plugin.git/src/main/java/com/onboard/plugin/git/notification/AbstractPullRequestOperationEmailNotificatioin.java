package com.onboard.plugin.git.notification;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.onboard.domain.model.Activity;
import com.onboard.domain.model.type.Subscribable;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.service.notification.email.EmailNotificationByVelocity;

public abstract class AbstractPullRequestOperationEmailNotificatioin extends EmailNotificationByVelocity {

    protected static final String VM_NAME = "pull-request-operate.vm";

    @Autowired
    private RepositoryService repositoryService;

    protected abstract String getOperation();

    @Override
    public String modelType() {
        return new PullRequest().getType();
    }

    @Override
    public Map<String, Object> getVelocityModel(Activity activity, Subscribable item, Map<String, Object> model) {
        PullRequest pr = (PullRequest) item;
        model.put("userName", this.getOwner(activity).getName());
        model.put("pullRequest", pr);
        model.put("operation", getOperation());
        model.put("repo", repositoryService.getRepositoryById(pr.getRepositoryId()));
        return model;
    }

    @Override
    public String getVelocityTemplatePath() {
        return VM_PATH + VM_NAME;
    }

    @Override
    protected String getEmailSubject(Activity activity, Subscribable item) {
        PullRequest pullRequest = (PullRequest) item;
        String repoName = repositoryService.getRepositoryById(pullRequest.getRepositoryId()).getName();
        return String.format("[%s:%s]%s%spull-request %s", activity.getProjectName(), repoName, this.getOwner(activity)
                .getName(), getOperation(), pullRequest.getTitle());
    }

}
