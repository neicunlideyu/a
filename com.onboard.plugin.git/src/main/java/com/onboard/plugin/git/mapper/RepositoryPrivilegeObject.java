package com.onboard.plugin.git.mapper;

public class RepositoryPrivilegeObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repository_privilege.id
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repository_privilege.companyId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    private Integer companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repository_privilege.projectId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    private Integer projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repository_privilege.repositoryId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    private Integer repositoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repository_privilege.defaultOwner
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    private Integer defaultOwner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repository_privilege.action
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    private String action;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repository_privilege.id
     *
     * @return the value of repository_privilege.id
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repository_privilege.id
     *
     * @param id the value for repository_privilege.id
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repository_privilege.companyId
     *
     * @return the value of repository_privilege.companyId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repository_privilege.companyId
     *
     * @param companyId the value for repository_privilege.companyId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repository_privilege.projectId
     *
     * @return the value of repository_privilege.projectId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repository_privilege.projectId
     *
     * @param projectId the value for repository_privilege.projectId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repository_privilege.repositoryId
     *
     * @return the value of repository_privilege.repositoryId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Integer getRepositoryId() {
        return repositoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repository_privilege.repositoryId
     *
     * @param repositoryId the value for repository_privilege.repositoryId
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repository_privilege.defaultOwner
     *
     * @return the value of repository_privilege.defaultOwner
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Integer getDefaultOwner() {
        return defaultOwner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repository_privilege.defaultOwner
     *
     * @param defaultOwner the value for repository_privilege.defaultOwner
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setDefaultOwner(Integer defaultOwner) {
        this.defaultOwner = defaultOwner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repository_privilege.action
     *
     * @return the value of repository_privilege.action
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repository_privilege.action
     *
     * @param action the value for repository_privilege.action
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public RepositoryPrivilegeObject(RepositoryPrivilegeObject parent) {
        this.id = parent.getId();
        this.companyId = parent.getCompanyId();
        this.projectId = parent.getProjectId();
        this.repositoryId = parent.getRepositoryId();
        this.defaultOwner = parent.getDefaultOwner();
        this.action = parent.getAction();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public RepositoryPrivilegeObject() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public RepositoryPrivilegeObject(int id) {
        this.id = id;
    }
}