package com.onboard.plugin.git;

import java.util.List;

import com.onboard.domain.model.User;
import com.onboard.plugin.git.model.RepositoryPrivilege;

/**
 * {@link RepositoryPrivilege} Service Interface
 *
 * @generated_by_elevenframework
 */
public interface RepositoryPrivilegeService {
    /**
     * Get item by id
     *
     * @param id
     * @return item
     */
    RepositoryPrivilege getRepositoryPrivilegeById(int id);

    /**
     * Get item list
     *
     * @param start
     * @param limit
     * @return the item list
     */
    List<RepositoryPrivilege> getRepositoryPrivileges(int start, int limit);

    /**
     * Get item list by example
     *
     * @param item
     * @param start
     * @param limit
     * @return the item list
     */
    List<RepositoryPrivilege> getRepositoryPrivilegesByExample(RepositoryPrivilege item, int start, int limit);

    /**
     * Get item count by example
     *
     * @param item
     * @return the count
     */
    int countByExample(RepositoryPrivilege item);


    /**
     * Create
     *
     * @param item
     * @return the created RepositoryPrivilege
     */
    RepositoryPrivilege createRepositoryPrivilege(RepositoryPrivilege item);

    /**
     * Update
     *
     * @param item
     * @return the updated item
     */
    RepositoryPrivilege updateRepositoryPrivilege(RepositoryPrivilege item);

    /**
     * Delete
     *
     * @param id
     */
    void deleteRepositoryPrivilege(int id);

    /**
     * 获取或创建RepositoryPrivilege
     *
     * @param companyId
     * @param projectId
     * @param repositoryId
     * @return
     */
    RepositoryPrivilege getOrCreateRepositoryPrivilege(int companyId, int projectId,
                                                       int repositoryId, String action);

    /**
     * 获取默认权限拥有者
     *
     * @param companyId
     * @param projectId
     * @param repositoryId
     * @return
     */
    List<User> getDeafultPrivilegedUsers(int companyId, int projectId, int repositoryId, String action);

    /**
     * 获取额外添加的权限拥有者
     *
     * @param companyId
     * @param projectId
     * @param repositoryId
     * @return
     */
    List<User> getOtherPrivilegedUsers(int companyId, int projectId, int repositoryId, String action);

    /**
     * 获取所有权限拥有者
     *
     * @param companyId
     * @param projectId
     * @param repositoryId
     * @return
     */
    List<User> getAllPrivilegedUsers(int companyId, int projectId, int repositoryId, String action);

    /**
     * 判断该用户是否对某仓库有某操作的权限
     *
     * @param userId
     * @param projectId
     * @param companyId
     * @param objectId
     * @return
     */
    boolean hasPrivilege(Integer projectId, Integer companyId, Integer repositoryId, String action, Integer userId);

    /**
     * 更新defaultOwner
     *
     * @param projectId
     * @param companyId
     * @param repositoryId
     * @param action
     * @param defaultOwner
     * @return
     */
    RepositoryPrivilege updateDefaultOwner(Integer projectId, Integer companyId, Integer repositoryId, String action, Integer defaultOwner);

}
