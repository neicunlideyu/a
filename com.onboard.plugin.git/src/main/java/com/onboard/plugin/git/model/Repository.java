package com.onboard.plugin.git.model;

import com.onboard.plugin.git.mapper.RepositoryObject;


/**
 * Domain model: Repository
 * 
 * @generated_by_elevenframework
 * 
 */
public class Repository extends RepositoryObject {
    
    BasicCommit lastCommit;

    public Repository() {
        super();
    }

    public Repository(int id) {
        super(id);
    }

    public Repository(RepositoryObject obj) {
        super(obj);
    }

    public String getCloneUrl() {
        return String.format("/%d/%s.git", this.getProjectId(), this.getSlug());
    }

    public BasicCommit getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(BasicCommit lastCommit) {
        this.lastCommit = lastCommit;
    }

}
