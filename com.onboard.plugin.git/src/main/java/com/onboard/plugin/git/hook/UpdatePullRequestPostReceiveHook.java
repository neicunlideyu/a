package com.onboard.plugin.git.hook;

import java.util.List;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceiveCommand.Type;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.plugin.git.OnBoardPostReceiveHook;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.ReviewService;
import com.onboard.plugin.git.model.PullRequest;

@Service("updatePullRequestPostHookBean")
public class UpdatePullRequestPostReceiveHook implements OnBoardPostReceiveHook {

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected PullRequestService pullRequestService;

    @Autowired
    protected ReviewService reviewService;

    @Override
    public void onPostReceive(ReceivePack rp, ReceiveCommand cmd) {
        if (!cmd.getType().equals(Type.UPDATE)) {
            return;
        }
        PullRequest sample = new PullRequest();
        sample.setRepositoryId(repositoryService.getRepositoryByRepo(rp.getRepository()).getId());
        sample.setStatus(PullRequest.Status.OPEN.value());
        sample.setSource(cmd.getRefName().substring("refs/heads/".length()));
        List<PullRequest> pullrequests = pullRequestService.getPullRequestsByExample(sample, 0, -1);
        if (pullrequests != null) {
            for (PullRequest p : pullrequests) {
                pullRequestService.updatePullRequestAfterCommit(new PullRequest(p), cmd.getNewId());
                // update review
                reviewService.updateReviewAfterPush(p, cmd.getOldId().getName(), cmd.getNewId().getName());
            }
        }
    }

}
