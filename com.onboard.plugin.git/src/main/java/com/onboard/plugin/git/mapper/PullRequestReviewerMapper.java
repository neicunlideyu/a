package com.onboard.plugin.git.mapper;

import com.onboard.plugin.git.mapper.PullRequestReviewerExample;
import com.onboard.plugin.git.model.PullRequestReviewer;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PullRequestReviewerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int countByExample(PullRequestReviewerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int deleteByExample(PullRequestReviewerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int insert(PullRequestReviewer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int insertSelective(PullRequestReviewer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    List<PullRequestReviewer> selectByExample(PullRequestReviewerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    PullRequestReviewer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int updateByExampleSelective(@Param("record") PullRequestReviewer record, @Param("example") PullRequestReviewerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int updateByExample(@Param("record") PullRequestReviewer record, @Param("example") PullRequestReviewerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int updateByPrimaryKeySelective(PullRequestReviewer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request_reviewer
     *
     * @mbggenerated Sun Dec 01 20:38:08 CST 2013
     */
    int updateByPrimaryKey(PullRequestReviewer record);
}
