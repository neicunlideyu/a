package com.onboard.plugin.git.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.PushService;
import com.onboard.plugin.git.mapper.PushExample;
import com.onboard.plugin.git.mapper.PushMapper;
import com.onboard.plugin.git.model.BasicCommit;
import com.onboard.plugin.git.model.Push;
import com.onboard.plugin.git.utils.CommitUtils;
import com.onboard.plugin.git.utils.CommonUtil;
import com.onboard.plugin.git.utils.GitOperation;
import com.onboard.service.collaboration.identifiable.DefaultIdentifiableService;

/**
 * {@link com.onboard.plugin.git.PushService} Service implementation
 * 
 * @generated_by_elevenframework
 * 
 */
@Transactional
@Service("pushServiceBean")
public class PushServiceImpl extends DefaultIdentifiableService implements PushService {

    private static final String DEFAULT_PUSH_URL_TEMPLATE = "/%s/projects/%s/repos/%s/commits?until=refs/heads/%s";

    @Autowired
    private PushMapper pushMapper;

    @Autowired
    private GitOperation gitOp;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public void fillCommitsForPush(Push push) {
        org.eclipse.jgit.lib.Repository repository;
        try {
            repository = commonUtil.getRepository(push.getRepositoryId());
            Iterable<RevCommit> commits = gitOp.getCommits(repository, push.getSince(), push.getUntil());
            List<BasicCommit> ret = new ArrayList<BasicCommit>();
            for (RevCommit commit : commits) {
                ret.add(CommitUtils.commitToMap(commit));
            }
            push.setCommits(ret);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void fillCommitsForPushs(List<Push> pushs) {
        for (Push push : pushs) {
            fillCommitsForPush(push);
        }
    }

    @Override
    public Push getPushById(int id) {
        Push push = pushMapper.selectByPrimaryKey(id);
        return push;
    }

    @Override
    public List<Push> getPushes(int start, int limit) {
        PushExample example = new PushExample(new Push());
        example.setLimit(start, limit);
        List<Push> pushs = pushMapper.selectByExample(example);
        fillCommitsForPushs(pushs);
        return pushs;
    }

    @Override
    public List<Push> getPushesByExample(Push item, int start, int limit) {
        PushExample example = new PushExample(item);
        example.setLimit(start, limit);
        List<Push> pushs = pushMapper.selectByExample(example);
        fillCommitsForPushs(pushs);
        return pushs;
    }

    @Override
    public int countByExample(Push item) {
        PushExample example = new PushExample(item);
        return pushMapper.countByExample(example);
    }

    @Override
    public Push createPush(Push item) {
        pushMapper.insert(item);
        return item;
    }

    @Override
    public Push updatePush(Push item) {
        pushMapper.updateByPrimaryKey(item);
        return item;
    }

    @Override
    public void deletePush(int id) {
        pushMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Identifiable getIdentifiableById(Integer id) {
        return getPushById(id);
    }

    public Identifiable getIdentifiableWithDetailById(Integer id) {
        Push push = getPushById(id);
        fillCommitsForPush(push);
        return push;
    }

    @Override
    public String modelType() {
        return (new Push()).getType();
    }

    @Override
    public String getIdentifiableURI(Identifiable identifiable) {
        Push push = (Push) identifiable;
        return String.format(DEFAULT_PUSH_URL_TEMPLATE, push.getCompanyId(), push.getProjectId(), push.getRepositoryId(),
                push.getBranchName());
    }
}
