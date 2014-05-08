package com.onboard.plugin.git.mapper;

public class PullRequestReviewerObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pull_request_reviewer.id
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pull_request_reviewer.pullRequestId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    private Integer pullRequestId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pull_request_reviewer.reviewerId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    private Integer reviewerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pull_request_reviewer.projectId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    private Integer projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pull_request_reviewer.approved
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    private Boolean approved;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pull_request_reviewer.id
     *
     * @return the value of pull_request_reviewer.id
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pull_request_reviewer.id
     *
     * @param id the value for pull_request_reviewer.id
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pull_request_reviewer.pullRequestId
     *
     * @return the value of pull_request_reviewer.pullRequestId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public Integer getPullRequestId() {
        return pullRequestId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pull_request_reviewer.pullRequestId
     *
     * @param pullRequestId the value for pull_request_reviewer.pullRequestId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public void setPullRequestId(Integer pullRequestId) {
        this.pullRequestId = pullRequestId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pull_request_reviewer.reviewerId
     *
     * @return the value of pull_request_reviewer.reviewerId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public Integer getReviewerId() {
        return reviewerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pull_request_reviewer.reviewerId
     *
     * @param reviewerId the value for pull_request_reviewer.reviewerId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pull_request_reviewer.projectId
     *
     * @return the value of pull_request_reviewer.projectId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pull_request_reviewer.projectId
     *
     * @param projectId the value for pull_request_reviewer.projectId
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pull_request_reviewer.approved
     *
     * @return the value of pull_request_reviewer.approved
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public Boolean getApproved() {
        return approved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pull_request_reviewer.approved
     *
     * @param approved the value for pull_request_reviewer.approved
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public PullRequestReviewerObject(PullRequestReviewerObject parent) {
        this.id = parent.getId();
        this.pullRequestId = parent.getPullRequestId();
        this.reviewerId = parent.getReviewerId();
        this.projectId = parent.getProjectId();
        this.approved = parent.getApproved();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public PullRequestReviewerObject() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    public PullRequestReviewerObject(int id) {
        this.id = id;
    }
}