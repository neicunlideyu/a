package com.onboard.plugin.git.model;

import java.util.List;

import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.mapper.PushObject;

/**
 * Domain model: Push
 * 
 * @generated_by_elevenframework
 * 
 */
public class Push extends PushObject implements Identifiable {

    private List<BasicCommit> commits;

    public Push() {
        super();
    }

    public Push(int id) {
        super(id);
    }

    public Push(PushObject obj) {
        super(obj);
    }

    @Override
    public String getType() {
        return "pullrequest-push";
    }

    @Override
    public Boolean getDeleted() {
        return false;
    }

    public List<BasicCommit> getCommits() {
        return commits;
    }

    public void setCommits(List<BasicCommit> commits) {
        this.commits = commits;
    }

}
