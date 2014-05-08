package com.onboard.plugin.git.mapper;

public class RepositoryBranchPrivilegeObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repo_branch_privilege.id
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repo_branch_privilege.companyId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    private Integer companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repo_branch_privilege.projectId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    private Integer projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repo_branch_privilege.repositoryId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    private Integer repositoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repo_branch_privilege.userId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repo_branch_privilege.refName
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    private String refName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repo_branch_privilege.id
     *
     * @return the value of repo_branch_privilege.id
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repo_branch_privilege.id
     *
     * @param id the value for repo_branch_privilege.id
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repo_branch_privilege.companyId
     *
     * @return the value of repo_branch_privilege.companyId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repo_branch_privilege.companyId
     *
     * @param companyId the value for repo_branch_privilege.companyId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repo_branch_privilege.projectId
     *
     * @return the value of repo_branch_privilege.projectId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repo_branch_privilege.projectId
     *
     * @param projectId the value for repo_branch_privilege.projectId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repo_branch_privilege.repositoryId
     *
     * @return the value of repo_branch_privilege.repositoryId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public Integer getRepositoryId() {
        return repositoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repo_branch_privilege.repositoryId
     *
     * @param repositoryId the value for repo_branch_privilege.repositoryId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repo_branch_privilege.userId
     *
     * @return the value of repo_branch_privilege.userId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repo_branch_privilege.userId
     *
     * @param userId the value for repo_branch_privilege.userId
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repo_branch_privilege.refName
     *
     * @return the value of repo_branch_privilege.refName
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public String getRefName() {
        return refName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repo_branch_privilege.refName
     *
     * @param refName the value for repo_branch_privilege.refName
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public void setRefName(String refName) {
        this.refName = refName == null ? null : refName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repo_branch_privilege
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public RepositoryBranchPrivilegeObject(RepositoryBranchPrivilegeObject parent) {
        this.id = parent.getId();
        this.companyId = parent.getCompanyId();
        this.projectId = parent.getProjectId();
        this.repositoryId = parent.getRepositoryId();
        this.userId = parent.getUserId();
        this.refName = parent.getRefName();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repo_branch_privilege
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public RepositoryBranchPrivilegeObject() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repo_branch_privilege
     *
     * @mbggenerated Sun Dec 08 00:08:30 CST 2013
     */
    public RepositoryBranchPrivilegeObject(int id) {
        this.id = id;
    }
}