package com.onboard.plugin.git;

import java.util.Collection;
import java.util.List;

import org.eclipse.jgit.transport.PostReceiveHook;
import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("gitPostReceiveHookBean")
public class GitPostReceiveHook implements PostReceiveHook {

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private List<OnBoardPostReceiveHook> onBoardPostReceiveHooks = Lists.newArrayList();

    public synchronized void addOnBoardPostReceiveHook(OnBoardPostReceiveHook onBoardPostReceiveHook) {
        onBoardPostReceiveHooks.add(onBoardPostReceiveHook);
    }

    public synchronized void removeOnBoardPostReceiveHook(OnBoardPostReceiveHook onBoardPostReceiveHook) {
        onBoardPostReceiveHooks.remove(onBoardPostReceiveHook);
    }

    @Override
    public void onPostReceive(ReceivePack rp, Collection<ReceiveCommand> commands) {
        for (OnBoardPostReceiveHook onBoardPostReceiveHook : onBoardPostReceiveHooks) {
            for (ReceiveCommand cmd : commands) {
                onBoardPostReceiveHook.onPostReceive(rp, cmd);
            }
        }
    }

}
