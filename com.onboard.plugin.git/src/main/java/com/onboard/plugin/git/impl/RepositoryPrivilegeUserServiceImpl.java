package com.onboard.plugin.git.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.plugin.git.mapper.RepositoryPrivilegeUserMapper;
import com.onboard.plugin.git.mapper.RepositoryPrivilegeUserExample;
import com.onboard.plugin.git.RepositoryPrivilegeUserService;
import com.onboard.plugin.git.model.RepositoryPrivilegeUser;

/**
 * {@link com.onboard.plugin.git.RepositoryPrivilegeUserService} Service implementation
 * 
 * @generated_by_elevenframework
 * 
 */
@Transactional
@Service("repositoryPrivilegeUserServiceBean")
public class RepositoryPrivilegeUserServiceImpl implements RepositoryPrivilegeUserService {

    @Autowired
    private RepositoryPrivilegeUserMapper repositoryPrivilegeUserMapper;

    @Override
    public RepositoryPrivilegeUser getRepositoryPrivilegeUserById(int id) {
        return repositoryPrivilegeUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RepositoryPrivilegeUser> getRepositoryPrivilegeUsers(int start, int limit) {
        RepositoryPrivilegeUserExample example = new RepositoryPrivilegeUserExample(new RepositoryPrivilegeUser());
        example.setLimit(start, limit);
        return repositoryPrivilegeUserMapper.selectByExample(example);
    }

    @Override
    public List<RepositoryPrivilegeUser> getRepositoryPrivilegeUsersByExample(RepositoryPrivilegeUser item, int start,
            int limit) {
        RepositoryPrivilegeUserExample example = new RepositoryPrivilegeUserExample(item);
        example.setLimit(start, limit);
        return repositoryPrivilegeUserMapper.selectByExample(example);
    }
    
    @Override
    public int countByExample(RepositoryPrivilegeUser item) {
        RepositoryPrivilegeUserExample example = new RepositoryPrivilegeUserExample(item);
        return repositoryPrivilegeUserMapper.countByExample(example);
    }

    @Override
    public RepositoryPrivilegeUser createRepositoryPrivilegeUser(RepositoryPrivilegeUser item) {
        repositoryPrivilegeUserMapper.insert(item);
        return item;
    }

    @Override
    public RepositoryPrivilegeUser updateRepositoryPrivilegeUser(RepositoryPrivilegeUser item) {
        repositoryPrivilegeUserMapper.updateByPrimaryKey(item);
        return item;
    }

    @Override
    public void deleteRepositoryPrivilegeUser(int id) {
        repositoryPrivilegeUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteUserByUserIdByRepositoryPrivilegeId(int userId,
            int privilegeId) {
        RepositoryPrivilegeUser item = new RepositoryPrivilegeUser();
        item.setUserId(userId);
        item.setRepositoryRrivilegeId(privilegeId);
        repositoryPrivilegeUserMapper.deleteByExample(new RepositoryPrivilegeUserExample(item));
    }

}
