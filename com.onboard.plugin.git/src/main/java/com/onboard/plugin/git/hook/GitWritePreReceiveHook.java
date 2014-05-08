package com.onboard.plugin.git.hook;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceiveCommand.Result;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.Project;
import com.onboard.domain.model.User;
import com.onboard.plugin.git.OnBoardPreReceiveHook;
import com.onboard.plugin.git.RepositoryPrivilegeActionType;
import com.onboard.plugin.git.RepositoryPrivilegeService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.model.Repository;
import com.onboard.service.collaboration.ProjectService;
import com.onboard.service.web.SessionService;

@Service("gitWritePreHookBean")
public class GitWritePreReceiveHook implements OnBoardPreReceiveHook {

    @Autowired
    private RepositoryPrivilegeService repositoryPrivilegeService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProjectService projectService;

    @Override
    public void onPreReceive(ReceivePack rp, ReceiveCommand cmd) {
        Repository repo = repositoryService.getRepositoryByRepo(rp.getRepository());
        User user = sessionService.getCurrentUser();
        Project project = projectService.getProjectById(repo.getProjectId());
        if (!repositoryPrivilegeService.hasPrivilege(repo.getProjectId(), project.getCompanyId(), repo.getId(),
                RepositoryPrivilegeActionType.WRITE, user.getId())) {
            cmd.setResult(Result.REJECTED_OTHER_REASON, "You don't have the write permission in the repository!");
        }
    }

}
