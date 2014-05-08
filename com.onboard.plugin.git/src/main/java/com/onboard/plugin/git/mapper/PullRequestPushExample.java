package com.onboard.plugin.git.mapper;

import com.onboard.domain.mapper.common.BaseCriteria;
import com.onboard.domain.mapper.common.BaseExample;
import java.util.ArrayList;
import java.util.List;

public class PullRequestPushExample implements BaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    protected int start = 0;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    protected int limit = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public PullRequestPushExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
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
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public List<BaseCriteria> getOredBaseCriteria() {
        List<com.onboard.domain.mapper.common.BaseCriteria> list = new ArrayList<com.onboard.domain.mapper.common.BaseCriteria>();
        list.addAll(oredCriteria);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void setStart(int start) {
        this.start=start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public int getStart() {
        return start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void setLimit(int limit) {
        this.limit=limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public int getLimit() {
        return limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public void setLimit(int start, int limit) {
        this.start = start;
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
     */
    public PullRequestPushExample(PullRequestPushObject sample) {
        oredCriteria = new ArrayList<Criteria>();
        Criteria criteria = this.or();
        if (sample.getPullRequestId() != null) {
            criteria.andPullRequestIdEqualTo(sample.getPullRequestId());
        }
        if (sample.getPushId() != null) {
            criteria.andPushIdEqualTo(sample.getPushId());
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pull_request_push
     *
     * @mbggenerated Wed Apr 16 21:57:21 CST 2014
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

        public Criteria andPullRequestIdIsNull() {
            addCriterion("pullRequestId is null");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdIsNotNull() {
            addCriterion("pullRequestId is not null");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdEqualTo(Integer value) {
            addCriterion("pullRequestId =", value, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdNotEqualTo(Integer value) {
            addCriterion("pullRequestId <>", value, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdGreaterThan(Integer value) {
            addCriterion("pullRequestId >", value, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pullRequestId >=", value, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdLessThan(Integer value) {
            addCriterion("pullRequestId <", value, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdLessThanOrEqualTo(Integer value) {
            addCriterion("pullRequestId <=", value, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdIn(List<Integer> values) {
            addCriterion("pullRequestId in", values, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdNotIn(List<Integer> values) {
            addCriterion("pullRequestId not in", values, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdBetween(Integer value1, Integer value2) {
            addCriterion("pullRequestId between", value1, value2, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPullRequestIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pullRequestId not between", value1, value2, "pullRequestId");
            return (Criteria) this;
        }

        public Criteria andPushIdIsNull() {
            addCriterion("pushId is null");
            return (Criteria) this;
        }

        public Criteria andPushIdIsNotNull() {
            addCriterion("pushId is not null");
            return (Criteria) this;
        }

        public Criteria andPushIdEqualTo(Integer value) {
            addCriterion("pushId =", value, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdNotEqualTo(Integer value) {
            addCriterion("pushId <>", value, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdGreaterThan(Integer value) {
            addCriterion("pushId >", value, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pushId >=", value, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdLessThan(Integer value) {
            addCriterion("pushId <", value, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdLessThanOrEqualTo(Integer value) {
            addCriterion("pushId <=", value, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdIn(List<Integer> values) {
            addCriterion("pushId in", values, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdNotIn(List<Integer> values) {
            addCriterion("pushId not in", values, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdBetween(Integer value1, Integer value2) {
            addCriterion("pushId between", value1, value2, "pushId");
            return (Criteria) this;
        }

        public Criteria andPushIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pushId not between", value1, value2, "pushId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pull_request_push
     *
     * @mbggenerated do_not_delete_during_merge Wed Apr 16 21:57:21 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}