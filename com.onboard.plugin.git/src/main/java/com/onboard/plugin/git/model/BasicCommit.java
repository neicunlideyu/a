package com.onboard.plugin.git.model;

import java.util.Date;

/**
 * 存储Commit基本信息，包括id，作者，提交Message，时间
 * 
 * @author Ruici
 * 
 */
/**
 * @author xuchen
 *
 */
public class BasicCommit {

    private String id;
    private String firstParentId;
    private String secondParentId;
    private String userName;
    private String userEmail;
    private String shortMessage;
    private String fullMessage;
    private Date timestamp;
    private String fileTree;
    private String userImageUrl;
    
    public BasicCommit(String id, String firstParentId, String secondParentId,
            String userName, String userEmail, String shortMessage,
            String fullMessage, Date timestamp) {
        super();
        this.id = id;
        this.firstParentId = firstParentId;
        this.secondParentId = secondParentId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.shortMessage = shortMessage;
        this.fullMessage = fullMessage;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFirstParentId() {
        return firstParentId;
    }

    public void setFirstParentId(String firstParentId) {
        this.firstParentId = firstParentId;
    }

    public String getSecondParentId() {
        return secondParentId;
    }

    public void setSecondParentId(String secondParentId) {
        this.secondParentId = secondParentId;
    }

    public String getFileTree() {
        return fileTree;
    }

    public void setFileTree(String fileTree) {
        this.fileTree = fileTree;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
    
    
}
