package com.onboard.plugin.git;

import java.util.List;

import org.eclipse.jgit.lib.ObjectId;

import com.onboard.plugin.git.model.BasicCommit;
import com.onboard.plugin.git.model.Diff;
import com.onboard.plugin.git.model.PullRequest;

public interface PullRequestService {

    List<PullRequest> getPullRequestListInRepository(int repoId, Integer status);

    PullRequest getPullRequestById(int id);

    List<BasicCommit> getCommitsOfPullRequest(PullRequest pullRequest);

    PullRequest createPullRequest(PullRequest pr);

    List<Diff> getDiffOfPullRequest(int repoId, int pullRequestId);

    PullRequest updatePullRequest(PullRequest pullRequest);

    void fillPullRequestFileTree(PullRequest pullRequest);

    boolean canMerge(PullRequest pullRequest);

    /**
     * 根据PullRequest获取PullRequest列表
     * 
     * @param pr
     * @param start
     * @param limit
     * @return
     */
    List<PullRequest> getPullRequestsByExample(PullRequest pr, int start, int limit);

    /**
     * 在有新的commit后更新PullRequest
     * 
     * @param pr
     * @param newLastCommitId
     */
    void updatePullRequestAfterCommit(PullRequest pullRequest, ObjectId newLastCommitId);

    List<PullRequest> getPullRequestListInRepositoryByStartLimit(int repoId, Integer status, int start, int limit);

    int countPullReqestsByRepoIdStatus(int repoId, int status);
}
