package com.onboard.plugin.git;

import java.util.List;

import com.onboard.plugin.git.model.RepositoryBranchPrivilege;

/**
 * {@link RepositoryBranchPrivilege} Service Interface
 * 
 * @generated_by_elevenframework
 * 
 */
public interface RepositoryBranchPrivilegeService {
    /**
     * Get item by id
     * 
     * @param id
     * @return item
     */
    RepositoryBranchPrivilege getRepositoryBranchPrivilegeById(int id);

    /**
     * Get item list
     * 
     * @param start
     * @param limit
     * @return the item list
     */
    List<RepositoryBranchPrivilege> getRepositoryBranchPrivileges(int start, int limit);

    /**
     * Get item list by example
     * 
     * @param item
     * @param start
     * @param limit
     * @return the item list
     */
    List<RepositoryBranchPrivilege> getRepositoryBranchPrivilegesByExample(RepositoryBranchPrivilege item, int start, int limit);

    /**
     * Get item count by example
     * 
     * @param item
     * @return the count
     */
    int countByExample(RepositoryBranchPrivilege item);


    /**
     * Create
     * 
     * @param item
     * @return the created RepositoryBranchPrivilege
     */
    RepositoryBranchPrivilege createRepositoryBranchPrivilege(RepositoryBranchPrivilege item);

    /**
     * Update
     * 
     * @param item
     * @return the updated item
     */
    RepositoryBranchPrivilege updateRepositoryBranchPrivilege(RepositoryBranchPrivilege item);

    /**
     * Delete
     * 
     * @param id
     */
    void deleteRepositoryBranchPrivilege(int id);
    
    void deleteRepositoryBranchPrivilege(RepositoryBranchPrivilege repositoryBranchPrivilege);
    
    boolean isUserHasBranchPrivilege(Integer companyId, Integer projectId, Integer repositoryId, Integer userId, String refName);

    List<RepositoryBranchPrivilege> getRepositoryBranchPrivilegesByExample(RepositoryBranchPrivilege item);

    List<RepositoryBranchPrivilege> getRepositoryBranchPrivileges(Integer companyId, Integer projectId, Integer repositoryId, String refName);
    
    boolean isBranchSetPrivilege(Integer companyId, Integer projectId, Integer repositoryId, String refName);
}
