package com.onboard.plugin.git.mapper;

import com.onboard.plugin.git.mapper.PullRequestExample;
import com.onboard.plugin.git.model.PullRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PullRequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int countByExample(PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int deleteByExample(PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int insert(PullRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int insertSelective(PullRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    List<PullRequest> selectByExampleWithBLOBs(PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    List<PullRequest> selectByExample(PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    PullRequest selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int updateByExampleSelective(@Param("record") PullRequest record, @Param("example") PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int updateByExampleWithBLOBs(@Param("record") PullRequest record, @Param("example") PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int updateByExample(@Param("record") PullRequest record, @Param("example") PullRequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int updateByPrimaryKeySelective(PullRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int updateByPrimaryKeyWithBLOBs(PullRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pull_request
     *
     * @mbggenerated Tue Mar 18 23:28:24 CST 2014
     */
    int updateByPrimaryKey(PullRequest record);
}