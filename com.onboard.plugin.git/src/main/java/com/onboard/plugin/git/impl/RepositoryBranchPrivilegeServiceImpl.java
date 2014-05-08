package com.onboard.plugin.git.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.plugin.git.RepositoryBranchPrivilegeService;
import com.onboard.plugin.git.mapper.RepositoryBranchPrivilegeExample;
import com.onboard.plugin.git.mapper.RepositoryBranchPrivilegeMapper;
import com.onboard.plugin.git.model.RepositoryBranchPrivilege;

/**
 * {@link com.onboard.plugin.git.RepositoryBranchPrivilegeService} Service implementation
 * 
 * @generated_by_elevenframework
 * 
 */
@Transactional
@Service("repositoryBranchPrivilegeServiceBean")
public class RepositoryBranchPrivilegeServiceImpl implements RepositoryBranchPrivilegeService {

    @Autowired
    private RepositoryBranchPrivilegeMapper repositoryBranchPrivilegeMapper;

    @Override
    public RepositoryBranchPrivilege getRepositoryBranchPrivilegeById(int id) {
        return repositoryBranchPrivilegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RepositoryBranchPrivilege> getRepositoryBranchPrivileges(int start, int limit) {
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(new RepositoryBranchPrivilege());
        example.setLimit(start, limit);
        return repositoryBranchPrivilegeMapper.selectByExample(example);
    }
    
    @Override 
    public List<RepositoryBranchPrivilege> getRepositoryBranchPrivileges(Integer companyId, Integer projectId, Integer repositoryId, String refName) {
        RepositoryBranchPrivilege sample = new RepositoryBranchPrivilege();
        sample.setCompanyId(companyId);
       sample.setProjectId(projectId);
       sample.setRepositoryId(repositoryId);
       sample.setRefName(refName);
       RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(sample);
       return repositoryBranchPrivilegeMapper.selectByExample(example);
    }

    @Override
    public List<RepositoryBranchPrivilege> getRepositoryBranchPrivilegesByExample(RepositoryBranchPrivilege item, int start,
            int limit) {
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(item);
        example.setLimit(start, limit);
        return repositoryBranchPrivilegeMapper.selectByExample(example);
    }
    
    @Override
    public List<RepositoryBranchPrivilege> getRepositoryBranchPrivilegesByExample(RepositoryBranchPrivilege item) {
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(item);        
        return repositoryBranchPrivilegeMapper.selectByExample(example);
    }
    
    @Override
    public int countByExample(RepositoryBranchPrivilege item) {
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(item);
        return repositoryBranchPrivilegeMapper.countByExample(example);
    }

    @Override
    public RepositoryBranchPrivilege createRepositoryBranchPrivilege(RepositoryBranchPrivilege item) {
        repositoryBranchPrivilegeMapper.insert(item);
        return item;
    }

    @Override
    public RepositoryBranchPrivilege updateRepositoryBranchPrivilege(RepositoryBranchPrivilege item) {
        repositoryBranchPrivilegeMapper.updateByPrimaryKey(item);
        return item;
    }

    @Override
    public void deleteRepositoryBranchPrivilege(int id) {
        repositoryBranchPrivilegeMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public void deleteRepositoryBranchPrivilege(RepositoryBranchPrivilege repositoryBranchPrivilege) {
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(repositoryBranchPrivilege);        
        repositoryBranchPrivilegeMapper.deleteByExample(example);
    }    
    
    @Override
    public boolean isUserHasBranchPrivilege(Integer companyId, Integer projectId,
            Integer repositoryId, Integer userId, String refName) {
        if(!this.isBranchSetPrivilege(companyId, projectId, repositoryId, refName)){
            return true;
        }
        RepositoryBranchPrivilege sample = new RepositoryBranchPrivilege();
        sample.setCompanyId(companyId);
        sample.setProjectId(projectId);
        sample.setRepositoryId(repositoryId);
        sample.setUserId(userId);
        sample.setRefName(refName);
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(sample);
        List<RepositoryBranchPrivilege> list = repositoryBranchPrivilegeMapper.selectByExample(example);
        return list.size() > 0 ? true : false;
    }

    @Override
    public boolean isBranchSetPrivilege(Integer companyId, Integer projectId,
            Integer repositoryId, String refName) {        
        RepositoryBranchPrivilege sample = new RepositoryBranchPrivilege();
        sample.setCompanyId(companyId);
        sample.setProjectId(projectId);
        sample.setRepositoryId(repositoryId);        
        sample.setRefName(refName);
        RepositoryBranchPrivilegeExample example = new RepositoryBranchPrivilegeExample(sample);
        List<RepositoryBranchPrivilege> list = repositoryBranchPrivilegeMapper.selectByExample(example);
        return list.size() > 0 ? true : false;
    }
}
