package com.onboard.plugin.git.hook;

import java.util.Collection;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.transport.ServiceMayNotContinueException;
import org.eclipse.jgit.transport.UploadPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.Project;
import com.onboard.domain.model.User;
import com.onboard.plugin.git.OnBoardPreUploadHook;
import com.onboard.plugin.git.RepositoryPrivilegeActionType;
import com.onboard.plugin.git.RepositoryPrivilegeService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.model.Repository;
import com.onboard.service.account.UserService;
import com.onboard.service.collaboration.ProjectService;

@Service("gitReadPreHookBean")
public class GitReadPreUploadHook implements OnBoardPreUploadHook {

    @Autowired
    private RepositoryPrivilegeService repositoryPrivilegeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProjectService projectService;

    @Override
    public void onBeginNegotiateRound(String userEmail, UploadPack up, Collection<? extends ObjectId> wants, int cntOffered)
            throws ServiceMayNotContinueException {
        Repository repo = repositoryService.getRepositoryByRepo(up.getRepository());
        User user = userService.getUserByEmail(userEmail);
        Project project = projectService.getProjectById(repo.getProjectId());
        if (!repositoryPrivilegeService.hasPrivilege(repo.getProjectId(), project.getCompanyId(), repo.getId(),
                RepositoryPrivilegeActionType.READ, user.getId())) {
            throw new ServiceMayNotContinueException("You don't have permissioni to the repository!");
        }
    }

    @Override
    public void onEndNegotiateRound(String userEmail, UploadPack up, Collection<? extends ObjectId> wants, int cntCommon,
            int cntNotFound, boolean ready) throws ServiceMayNotContinueException {
        

    }

    @Override
    public void onSendPack(String userEmail, UploadPack up, Collection<? extends ObjectId> wants,
            Collection<? extends ObjectId> haves) throws ServiceMayNotContinueException {
        

    }

}
