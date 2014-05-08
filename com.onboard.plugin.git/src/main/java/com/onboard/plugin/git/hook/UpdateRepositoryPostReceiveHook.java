package com.onboard.plugin.git.hook;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.plugin.git.OnBoardPostReceiveHook;
import com.onboard.plugin.git.RepositoryService;

@Service("updateRepositoryPostHookBean")
public class UpdateRepositoryPostReceiveHook implements OnBoardPostReceiveHook {

    @Autowired
    protected RepositoryService repositoryService;

    @Override
    public void onPostReceive(ReceivePack rp, ReceiveCommand cmd) {
        repositoryService.updateRepositoryWithPush(rp.getRepository(), cmd.getRefName().substring("refs/heads/".length()));
    }

}
