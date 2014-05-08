package com.onboard.plugin.git;

import java.util.List;

import org.eclipse.jgit.lib.ObjectId;

import com.onboard.plugin.git.model.BasicCommit;
import com.onboard.plugin.git.model.Branch;
import com.onboard.plugin.git.model.Diff;
import com.onboard.plugin.git.model.Item;
import com.onboard.plugin.git.model.Repository;
import com.onboard.plugin.git.model.Tag;

public interface RepositoryService {

    boolean isEmptyRepository(Repository repository);

    Repository createRepository(Repository repository);

    Repository getRepositoryById(int id);

    Repository getRepositoryByRepo(org.eclipse.jgit.lib.Repository repo);

    List<Repository> getRepositoriesByProjectId(int projectId);

    List<BasicCommit> getCommitsOfBranch(int repoId, String branchRef);

    List<Branch> getBranchesOfRepository(int repoId);

    List<Tag> getTagsOfRepository(int repoId);

    int getPathTypeInRepository(int repoId, String path, String revStr);

    String getFileContentInRepository(int repoId, String path, String revStr);

    List<Item> getFileListInRepository(int repoId, String path, String revStr);

    BasicCommit getCommitByRevisionId(int repoId, String revId);

    List<Diff> getDiffOfCommit(int repoId, String path, String since, String until);

    ObjectId getBranchObjectId(int repoId, String refName);

    List<BasicCommit> getCommitsBetweenBranch(int repoId, String since, String until);

    /**
     * 在push后更新repository数据库
     * 
     * @param repo
     * @param refName
     */
    void updateRepositoryWithPush(org.eclipse.jgit.lib.Repository repo, String refName);

    /**
     * 
     * @param repositoryName
     *            Check whether the repository is already exists when creating new repository
     * @return
     */
    boolean isRepositoryExists(int companyId, int projectId, String repositoryName);

    List<BasicCommit> getCommitsOfBranchByStartLimit(int repoId, String branchRef, int start, int limit);

    int countCommitsOfBranch(int repoId, String branchRef);
}
