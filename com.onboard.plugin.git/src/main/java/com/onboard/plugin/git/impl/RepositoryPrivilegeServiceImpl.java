package com.onboard.plugin.git.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.domain.model.User;
import com.onboard.plugin.git.mapper.RepositoryPrivilegeMapper;
import com.onboard.plugin.git.mapper.RepositoryPrivilegeExample;
import com.onboard.plugin.git.mapper.RepositoryPrivilegeUserExample;
import com.onboard.plugin.git.mapper.RepositoryPrivilegeUserMapper;
import com.onboard.plugin.git.RepositoryPrivilegeOwnerType;
import com.onboard.plugin.git.RepositoryPrivilegeService;
import com.onboard.plugin.git.model.RepositoryPrivilege;
import com.onboard.plugin.git.model.RepositoryPrivilegeUser;
import com.onboard.service.account.UserService;
import com.onboard.service.security.RoleService;

/**
 * {@link com.onboard.plugin.git.RepositoryPrivilegeService} Service
 * implementation
 *
 * @generated_by_elevenframework
 */
@Transactional
@Service("repositoryPrivilegeServiceBean")
public class RepositoryPrivilegeServiceImpl implements
        RepositoryPrivilegeService {

    @Autowired
    private RepositoryPrivilegeMapper repositoryPrivilegeMapper;

    @Autowired
    private RepositoryPrivilegeUserMapper repositoryPrivilegeUserMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public RepositoryPrivilege getRepositoryPrivilegeById(int id) {
        return repositoryPrivilegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RepositoryPrivilege> getRepositoryPrivileges(int start,
                                                             int limit) {
        RepositoryPrivilegeExample example = new RepositoryPrivilegeExample(
                new RepositoryPrivilege());
        example.setLimit(start, limit);
        return repositoryPrivilegeMapper.selectByExample(example);
    }

    @Override
    public List<RepositoryPrivilege> getRepositoryPrivilegesByExample(
            RepositoryPrivilege item, int start, int limit) {
        RepositoryPrivilegeExample example = new RepositoryPrivilegeExample(
                item);
        example.setLimit(start, limit);
        return repositoryPrivilegeMapper.selectByExample(example);
    }

    @Override
    public int countByExample(RepositoryPrivilege item) {
        RepositoryPrivilegeExample example = new RepositoryPrivilegeExample(
                item);
        return repositoryPrivilegeMapper.countByExample(example);
    }

    @Override
    public RepositoryPrivilege createRepositoryPrivilege(
            RepositoryPrivilege item) {
        repositoryPrivilegeMapper.insert(item);
        return item;
    }

    @Override
    public RepositoryPrivilege updateRepositoryPrivilege(
            RepositoryPrivilege item) {
        repositoryPrivilegeMapper.updateByPrimaryKey(item);
        return item;
    }

    @Override
    public void deleteRepositoryPrivilege(int id) {
        repositoryPrivilegeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RepositoryPrivilege getOrCreateRepositoryPrivilege(int companyId,
                                                              int projectId, int repositoryId, String action) {
        RepositoryPrivilege example = new RepositoryPrivilege();
        example.setCompanyId(companyId);
        example.setProjectId(projectId);
        example.setRepositoryId(repositoryId);
        example.setAction(action);
        List<RepositoryPrivilege> repositoryPrivileges = this
                .getRepositoryPrivilegesByExample(example, 0, -1);
        if (repositoryPrivileges == null || repositoryPrivileges.size() == 0) {
            example.setDefaultOwner(RepositoryPrivilegeOwnerType.PROJECT_MEMBER);
            return this.createRepositoryPrivilege(example);
        }
        return repositoryPrivileges.get(0);
    }

    @Override
    public List<User> getDeafultPrivilegedUsers(int companyId, int projectId,
                                                int repositoryId, String action) {
        RepositoryPrivilege repositoryPrivilege = this
                .getOrCreateRepositoryPrivilege(companyId, projectId,
                        repositoryId, action);
        return this.getUsersByDefaultOwner(companyId, projectId, repositoryPrivilege.getDefaultOwner());
    }

    private List<User> getUsersByDefaultOwner(Integer companyId, Integer projectId, Integer defaultOwner) {
        if (defaultOwner.equals(
                RepositoryPrivilegeOwnerType.COMPANY_OWNER)) {
            List<User> users = new ArrayList<User>();
            users.add(roleService.getCompanyOwnerByCompanyId(companyId));
            return users;
        } else if (defaultOwner.equals(
                RepositoryPrivilegeOwnerType.COMPANY_ADMIN)) {
            return roleService.getCompanyAdminsByCompanyIdInSpecificProject(
                    companyId, projectId);
        } else if (defaultOwner.equals(
                RepositoryPrivilegeOwnerType.COMPANY_MEMBER)) {
            return roleService.getCompanyMembersByCompanyId(companyId);
        } else if (defaultOwner.equals(
                RepositoryPrivilegeOwnerType.PROJECT_ADMIN)) {
            return roleService.getProjectAdminsByProjectId(projectId);
        } else if (defaultOwner.equals(
                RepositoryPrivilegeOwnerType.PROJECT_MEMBER)) {
            return roleService.getProjectMembersByProjectId(projectId);
        }
        return null;
    }

    @Override
    public List<User> getOtherPrivilegedUsers(int companyId, int projectId,
                                              int repositoryId, String action) {
        List<User> users = new ArrayList<User>();

        RepositoryPrivilege repositoryPrivilege = this
                .getOrCreateRepositoryPrivilege(companyId, projectId,
                        repositoryId, action);
        RepositoryPrivilegeUser example = new RepositoryPrivilegeUser();
        example.setRepositoryRrivilegeId(repositoryPrivilege.getId());
        List<RepositoryPrivilegeUser> repositoryPrivilegeUsers = repositoryPrivilegeUserMapper
                .selectByExample(new RepositoryPrivilegeUserExample(example));

        for (RepositoryPrivilegeUser repositoryPrivilegeUser : repositoryPrivilegeUsers) {
            users.add(userService.getUserById(repositoryPrivilegeUser
                    .getUserId()));
        }

        return users;
    }

    @Override
    public List<User> getAllPrivilegedUsers(int companyId, int projectId,
                                            int repositoryId, String action) {
        List<User> users = this.getDeafultPrivilegedUsers(companyId, projectId,
                repositoryId, action);
        users.addAll(this.getOtherPrivilegedUsers(companyId, projectId,
                repositoryId, action));
        return users;
    }

    @Override
    public boolean hasPrivilege(Integer projectId, Integer companyId,
                                Integer repositoryId, String action, Integer userId) {
        RepositoryPrivilege privilege = this.getOrCreateRepositoryPrivilege(
                companyId, projectId, repositoryId, action);
        // 判断是否拥有默认权限
        boolean hasPrivilege = false;
        Integer defaultOwner = privilege.getDefaultOwner();
        // 根据默认权限类型判断是否拥有权限
        if (defaultOwner.equals(RepositoryPrivilegeOwnerType.COMPANY_OWNER)) {
            hasPrivilege = roleService.companyOwner(userId, companyId);
        } else if (defaultOwner
                .equals(RepositoryPrivilegeOwnerType.COMPANY_ADMIN)) {
            hasPrivilege = roleService.companyAdminInSpecificProject(userId,
                    companyId, projectId);
        } else if (defaultOwner
                .equals(RepositoryPrivilegeOwnerType.PROJECT_ADMIN)) {
            hasPrivilege = roleService.projectAdmin(userId, companyId,
                    projectId);
        } else if (defaultOwner
                .equals(RepositoryPrivilegeOwnerType.PROJECT_MEMBER)) {
            hasPrivilege = roleService.projectMember(userId, companyId,
                    projectId);
        } else if (defaultOwner
                .equals(RepositoryPrivilegeOwnerType.COMPANY_MEMBER)) {
            hasPrivilege = roleService.companyMember(userId, companyId);
        }
        if (hasPrivilege) {
            return true;
        }

        // 判断UserPrivilege表中是否拥有权限
        RepositoryPrivilegeUser example = new RepositoryPrivilegeUser();
        example.setUserId(userId);
        example.setRepositoryRrivilegeId(privilege.getId());
        List<RepositoryPrivilegeUser> ups = repositoryPrivilegeUserMapper
                .selectByExample(new RepositoryPrivilegeUserExample(example));
        return ups.size() > 0;
    }

    @Override
    public RepositoryPrivilege updateDefaultOwner(Integer projectId, Integer companyId,
                                                  Integer repositoryId, String action, Integer defaultOwner) {
        RepositoryPrivilege privilege = new RepositoryPrivilege(this.getOrCreateRepositoryPrivilege(
                companyId, projectId, repositoryId, action));
        if (privilege.getDefaultOwner() < defaultOwner) {
            List<User> users = this.getUsersByDefaultOwner(companyId, projectId, defaultOwner);
            List<Integer> userIds = new ArrayList<Integer>();
            for (User user : users) {
                userIds.add(user.getId());
            }
            RepositoryPrivilegeUser rp = new RepositoryPrivilegeUser();
            rp.setRepositoryRrivilegeId(privilege.getId());
            RepositoryPrivilegeUserExample example = new RepositoryPrivilegeUserExample(rp);
            example.getOredCriteria().get(0).andUserIdIn(userIds);
            repositoryPrivilegeUserMapper.deleteByExample(example);
        }
        privilege.setDefaultOwner(defaultOwner);
        return this.updateRepositoryPrivilege(privilege);
    }

}
