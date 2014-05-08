package com.onboard.plugin.git.mapper;

import com.onboard.domain.mapper.common.BaseCriteria;
import com.onboard.domain.mapper.common.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class RepositoryPrivilegeExample implements BaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected int start = 0;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected int limit = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public RepositoryPrivilegeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public List<BaseCriteria> getOredBaseCriteria() {
        List<com.onboard.domain.mapper.common.BaseCriteria> list = new ArrayList<com.onboard.domain.mapper.common.BaseCriteria>();
        list.addAll(oredCriteria);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setStart(int start) {
        this.start=start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public int getStart() {
        return start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setLimit(int limit) {
        this.limit=limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public int getLimit() {
        return limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public void setLimit(int start, int limit) {
        this.start = start;
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    public RepositoryPrivilegeExample(RepositoryPrivilegeObject sample) {
        oredCriteria = new ArrayList<Criteria>();
        Criteria criteria = this.or();
        if (sample.getCompanyId() != null) {
            criteria.andCompanyIdEqualTo(sample.getCompanyId());
        }
        if (sample.getProjectId() != null) {
            criteria.andProjectIdEqualTo(sample.getProjectId());
        }
        if (sample.getRepositoryId() != null) {
            criteria.andRepositoryIdEqualTo(sample.getRepositoryId());
        }
        if (sample.getDefaultOwner() != null) {
            criteria.andDefaultOwnerEqualTo(sample.getDefaultOwner());
        }
        if (sample.getAction() != null) {
            criteria.andActionEqualTo(sample.getAction());
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table repository_privilege
     *
     * @mbggenerated Fri Dec 06 16:07:16 CST 2013
     */
    protected abstract static class GeneratedCriteria extends BaseCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("companyId is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("companyId is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("companyId =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("companyId <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("companyId >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("companyId >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("companyId <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("companyId <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("companyId in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("companyId not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("companyId between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("companyId not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("projectId is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("projectId is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("projectId =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("projectId <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("projectId >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectId >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("projectId <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("projectId <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("projectId in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("projectId not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("projectId between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("projectId not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdIsNull() {
            addCriterion("repositoryId is null");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdIsNotNull() {
            addCriterion("repositoryId is not null");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdEqualTo(Integer value) {
            addCriterion("repositoryId =", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdNotEqualTo(Integer value) {
            addCriterion("repositoryId <>", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdGreaterThan(Integer value) {
            addCriterion("repositoryId >", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("repositoryId >=", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdLessThan(Integer value) {
            addCriterion("repositoryId <", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("repositoryId <=", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdIn(List<Integer> values) {
            addCriterion("repositoryId in", values, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdNotIn(List<Integer> values) {
            addCriterion("repositoryId not in", values, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdBetween(Integer value1, Integer value2) {
            addCriterion("repositoryId between", value1, value2, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("repositoryId not between", value1, value2, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerIsNull() {
            addCriterion("defaultOwner is null");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerIsNotNull() {
            addCriterion("defaultOwner is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerEqualTo(Integer value) {
            addCriterion("defaultOwner =", value, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerNotEqualTo(Integer value) {
            addCriterion("defaultOwner <>", value, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerGreaterThan(Integer value) {
            addCriterion("defaultOwner >", value, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("defaultOwner >=", value, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerLessThan(Integer value) {
            addCriterion("defaultOwner <", value, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerLessThanOrEqualTo(Integer value) {
            addCriterion("defaultOwner <=", value, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerIn(List<Integer> values) {
            addCriterion("defaultOwner in", values, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerNotIn(List<Integer> values) {
            addCriterion("defaultOwner not in", values, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerBetween(Integer value1, Integer value2) {
            addCriterion("defaultOwner between", value1, value2, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andDefaultOwnerNotBetween(Integer value1, Integer value2) {
            addCriterion("defaultOwner not between", value1, value2, "defaultOwner");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("action <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("action like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("action not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("action not between", value1, value2, "action");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table repository_privilege
     *
     * @mbggenerated do_not_delete_during_merge Fri Dec 06 16:07:16 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}