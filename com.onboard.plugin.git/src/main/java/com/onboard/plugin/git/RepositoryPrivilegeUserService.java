package com.onboard.plugin.git;

import java.util.List;

import com.onboard.plugin.git.model.RepositoryPrivilegeUser;

/**
 * {@link RepositoryPrivilegeUser} Service Interface
 * 
 * @generated_by_elevenframework
 * 
 */
public interface RepositoryPrivilegeUserService {
    /**
     * Get item by id
     * 
     * @param id
     * @return item
     */
    RepositoryPrivilegeUser getRepositoryPrivilegeUserById(int id);

    /**
     * Get item list
     * 
     * @param start
     * @param limit
     * @return the item list
     */
    List<RepositoryPrivilegeUser> getRepositoryPrivilegeUsers(int start, int limit);

    /**
     * Get item list by example
     * 
     * @param item
     * @param start
     * @param limit
     * @return the item list
     */
    List<RepositoryPrivilegeUser> getRepositoryPrivilegeUsersByExample(RepositoryPrivilegeUser item, int start, int limit);

    /**
     * Get item count by example
     * 
     * @param item
     * @return the count
     */
    int countByExample(RepositoryPrivilegeUser item);


    /**
     * Create
     * 
     * @param item
     * @return the created RepositoryPrivilegeUser
     */
    RepositoryPrivilegeUser createRepositoryPrivilegeUser(RepositoryPrivilegeUser item);

    /**
     * Update
     * 
     * @param item
     * @return the updated item
     */
    RepositoryPrivilegeUser updateRepositoryPrivilegeUser(RepositoryPrivilegeUser item);

    /**
     * Delete
     * 
     * @param id
     */
    void deleteRepositoryPrivilegeUser(int id);
    
    /**
     * 根据userId和RepositoryPrivilegeId删除用户
     */
    void deleteUserByUserIdByRepositoryPrivilegeId(int userId, int privilegeId);
}
