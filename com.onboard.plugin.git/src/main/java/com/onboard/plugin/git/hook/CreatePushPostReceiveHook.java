package com.onboard.plugin.git.hook;

import java.util.List;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceiveCommand.Type;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.User;
import com.onboard.plugin.git.OnBoardPostReceiveHook;
import com.onboard.plugin.git.PullRequestPushService;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.PushService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.PullRequestPush;
import com.onboard.plugin.git.model.Push;
import com.onboard.service.web.SessionService;

@Service("createPushPostReceiveHookBean")
public class CreatePushPostReceiveHook implements OnBoardPostReceiveHook {

    @Autowired
    protected PushService pushService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected PullRequestService pullRequestService;

    @Autowired
    protected PullRequestPushService pullRequestPushService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void onPostReceive(ReceivePack rp, ReceiveCommand cmd) {
        if (!cmd.getType().equals(Type.UPDATE)) {
            return;
        }
        com.onboard.plugin.git.model.Repository repository = repositoryService.getRepositoryByRepo(rp.getRepository());
        User user = sessionService.getCurrentUser();

        // add push
        Push push = new Push();
        push.setBranchName(cmd.getRefName().substring("refs/heads/".length()));
        push.setCompanyId(repository.getCompanyId());
        push.setCreatorId(user.getId());
        push.setCreatorName(user.getName());
        push.setProjectId(repository.getProjectId());
        push.setRepositoryId(repository.getId());
        push.setSince(cmd.getOldId().getName());
        push.setUntil(cmd.getNewId().getName());
        push = pushService.createPush(push);

        // add pullrequedst-push
        PullRequest sample = new PullRequest();
        sample.setRepositoryId(repository.getId());
        sample.setStatus(PullRequest.Status.OPEN.value());
        sample.setSource(cmd.getRefName().substring("refs/heads/".length()));
        List<PullRequest> pullrequests = pullRequestService.getPullRequestsByExample(sample, 0, -1);
        if (pullrequests != null) {
            for (PullRequest p : pullrequests) {
                PullRequestPush pullRequestPush = new PullRequestPush();
                pullRequestPush.setPullRequestId(p.getId());
                pullRequestPush.setPushId(push.getId());
                pullRequestPushService.createPullRequestPush(pullRequestPush);
            }
        }
    }

}
