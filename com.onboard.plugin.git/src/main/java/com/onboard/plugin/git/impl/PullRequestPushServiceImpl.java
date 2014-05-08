package com.onboard.plugin.git.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.plugin.git.mapper.PullRequestPushMapper;
import com.onboard.plugin.git.mapper.PullRequestPushExample;
import com.onboard.plugin.git.PullRequestPushService;
import com.onboard.plugin.git.model.PullRequestPush;

/**
 * {@link com.onboard.plugin.git.PullRequestPushService} Service implementation
 * 
 * @generated_by_elevenframework
 * 
 */
@Transactional
@Service("pullRequestPushServiceBean")
public class PullRequestPushServiceImpl implements PullRequestPushService {

    @Autowired
    private PullRequestPushMapper pullRequestPushMapper;

    @Override
    public PullRequestPush getPullRequestPushById(int id) {
        return pullRequestPushMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PullRequestPush> getPullRequestPushes(int start, int limit) {
        PullRequestPushExample example = new PullRequestPushExample(new PullRequestPush());
        example.setLimit(start, limit);
        return pullRequestPushMapper.selectByExample(example);
    }

    @Override
    public List<PullRequestPush> getPullRequestPushesByExample(PullRequestPush item, int start,
            int limit) {
        PullRequestPushExample example = new PullRequestPushExample(item);
        example.setLimit(start, limit);
        return pullRequestPushMapper.selectByExample(example);
    }
    
    @Override
    public int countByExample(PullRequestPush item) {
        PullRequestPushExample example = new PullRequestPushExample(item);
        return pullRequestPushMapper.countByExample(example);
    }

    @Override
    public PullRequestPush createPullRequestPush(PullRequestPush item) {
        pullRequestPushMapper.insert(item);
        return item;
    }

    @Override
    public PullRequestPush updatePullRequestPush(PullRequestPush item) {
        pullRequestPushMapper.updateByPrimaryKey(item);
        return item;
    }

    @Override
    public void deletePullRequestPush(int id) {
        pullRequestPushMapper.deleteByPrimaryKey(id);
    }

}
