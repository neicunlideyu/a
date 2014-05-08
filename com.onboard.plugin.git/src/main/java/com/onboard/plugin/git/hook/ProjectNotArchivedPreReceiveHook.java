package com.onboard.plugin.git.hook;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceiveCommand.Result;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.plugin.git.OnBoardPreReceiveHook;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.model.Repository;
import com.onboard.service.collaboration.ProjectService;

@Service("projectNotArchivedPreHookBean")
public class ProjectNotArchivedPreReceiveHook implements OnBoardPreReceiveHook {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProjectService projectService;

    @Override
    public void onPreReceive(ReceivePack rp, ReceiveCommand cmd) {
        Repository repo = repositoryService.getRepositoryByRepo(rp.getRepository());
        if (projectService.getProjectById(repo.getProjectId()).getArchived()) {
            cmd.setResult(Result.REJECTED_OTHER_REASON, "The project is archived, you can not update it!");
        }
    }

}
