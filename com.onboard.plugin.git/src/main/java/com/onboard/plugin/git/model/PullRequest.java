package com.onboard.plugin.git.model;

import java.util.List;

import com.onboard.domain.model.Comment;
import com.onboard.domain.model.User;
import com.onboard.domain.model.type.Commentable;
import com.onboard.domain.model.type.Subscribable;
import com.onboard.plugin.git.mapper.PullRequestObject;

/**
 * Domain model: PullRequest
 * 
 * @generated_by_elevenframework
 * 
 */
public class PullRequest extends PullRequestObject implements Commentable, Subscribable {

    private String fileTree;

    private List<User> subscribers;

    private List<Comment> comments;

    public enum Status {
        OPEN(0), MERGED(1), DECLINED(2);

        private int value = 0;

        private Status(int value) {
            this.value = value;
        }

        public static Status valueOf(int value) {
            switch (value) {
            case 0:
                return OPEN;
            case 1:
                return MERGED;
            case 2:
                return DECLINED;
            default:
            }
            return null;
        }

        public int value() {
            return this.value;
        }
    }

    private List<PullRequestReviewer> pullRequestReviewers;

    public PullRequest() {
        super();
    }

    public PullRequest(int id) {
        super(id);
    }

    public PullRequest(PullRequestObject obj) {
        super(obj);
    }

    public List<PullRequestReviewer> getPullRequestReviewers() {
        return pullRequestReviewers;
    }

    public void setPullRequestReviewers(List<PullRequestReviewer> pullRequestReviewers) {
        this.pullRequestReviewers = pullRequestReviewers;
    }

    public String getFileTree() {
        return fileTree;
    }

    public void setFileTree(String fileTree) {
        this.fileTree = fileTree;
    }

    @Override
    public String getType() {
        return "pull-request";
    }

    @Override
    public String getSubscribableType() {
        return "pull-request";
    }

    @Override
    public Integer getSubscribableId() {
        return this.getId();
    }

    @Override
    public String getSubscribableSubject() {
        return this.getTitle();
    }

    @Override
    public List<User> getSubscribers() {
        return this.subscribers;
    }

    @Override
    public void setSubscribers(List<User> users) {
        this.subscribers = users;
    }

    @Override
    public List<Comment> getComments() {
        return this.comments;
    }

    @Override
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String getCommentSubject() {
        return this.getTitle();
    }

}
