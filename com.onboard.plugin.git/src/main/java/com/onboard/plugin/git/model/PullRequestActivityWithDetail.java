package com.onboard.plugin.git.model;

import com.onboard.domain.model.Comment;
import com.onboard.plugin.git.mapper.PullRequestActivityObject;

public class PullRequestActivityWithDetail extends PullRequestActivity {

    public PullRequestActivityWithDetail() {
        super();
    }

    public PullRequestActivityWithDetail(int id) {
        super(id);
    }

    public PullRequestActivityWithDetail(PullRequestActivityObject obj) {
        super(obj);
    }

    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

}
