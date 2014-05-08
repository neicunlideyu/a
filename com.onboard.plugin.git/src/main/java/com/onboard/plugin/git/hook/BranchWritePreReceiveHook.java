package com.onboard.plugin.git.hook;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceiveCommand.Result;
import org.eclipse.jgit.transport.ReceiveCommand.Type;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.User;
import com.onboard.plugin.git.OnBoardPreReceiveHook;
import com.onboard.plugin.git.RepositoryBranchPrivilegeService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.model.RepositoryBranchPrivilege;
import com.onboard.service.collaboration.ProjectService;
import com.onboard.service.web.SessionService;

@Service("branchWritePreHookBean")
public class BranchWritePreReceiveHook implements OnBoardPreReceiveHook {

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected SessionService sessionService;

    @Autowired
    protected RepositoryBranchPrivilegeService repositoryBranchPrivilegeService;

    @Override
    public void onPreReceive(ReceivePack rp, ReceiveCommand cmd) {
        // We only care when the command is UPDATE or DELETE
        // For CREATE command the GitServlet will help do the filtering
        if (cmd.getType().equals(Type.UPDATE) || cmd.getType().equals(Type.DELETE)) {

            Repository gidRepo = rp.getRepository();
            com.onboard.plugin.git.model.Repository onboardRep = repositoryService.getRepositoryByRepo(gidRepo);

            int projectId = onboardRep.getProjectId();
            int repoId = onboardRep.getId();
            int companyId = projectService.getProjectById(projectId).getCompanyId();
            String refName = cmd.getRefName().substring("refs/heads/".length());
            User user = sessionService.getCurrentUser();

            if (repositoryBranchPrivilegeService.isUserHasBranchPrivilege(companyId, projectId, repoId, user.getId(), refName)) {
                // Here means the user has both update and delete
                // privilege.
                if (cmd.getType().equals(Type.DELETE)) {

                    // If the command is DELETE, we need to update
                    // database to remove the branch privilege settings
                    RepositoryBranchPrivilege sample = new RepositoryBranchPrivilege();
                    sample.setCompanyId(companyId);
                    sample.setProjectId(projectId);
                    sample.setRepositoryId(repoId);
                    sample.setRefName(refName);
                    repositoryBranchPrivilegeService.deleteRepositoryBranchPrivilege(sample);
                }
                // continue the loop
                return;
            }
            // else the User does not have branch access
            if (cmd.getType().equals(Type.UPDATE)) {
                cmd.setResult(Result.REJECTED_OTHER_REASON, "User does not have branch permission!");
            } else if (cmd.getType().equals(Type.DELETE)) {
                cmd.setResult(Result.REJECTED_OTHER_REASON, "User does not have permission to delete this branch!");
            }

        }
    }

}
