package com.onboard.plugin.git.model;

import java.util.List;

import com.onboard.domain.model.User;

public class BranchSetting {
    private String branchName;
    private String branchRefName;

    private List<User> userList;

    public BranchSetting(String branchRefName, String branchName, List<User> userList) {
        this.branchName = branchName;
        this.userList = userList;
        this.branchRefName = branchRefName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getbranchRefName() {
        return branchRefName;
    }

    public void setbranchRefName(String branchRefName) {
        this.branchRefName = branchRefName;
    }
}
