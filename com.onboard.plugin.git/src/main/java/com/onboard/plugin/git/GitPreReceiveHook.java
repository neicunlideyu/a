package com.onboard.plugin.git;

import java.util.Collection;
import java.util.List;

import org.eclipse.jgit.transport.PreReceiveHook;
import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceivePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.onboard.service.account.UserService;
import com.onboard.service.web.SessionService;

@Service("gitPreReceiveHookBean")
public class GitPreReceiveHook implements PreReceiveHook {

    private String userEmail;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private List<OnBoardPreReceiveHook> onBoardPreReceiveHooks = Lists.newArrayList();

    public synchronized void addOnBoardPreReceiveHook(OnBoardPreReceiveHook onBoardPreReceiveHook) {
        onBoardPreReceiveHooks.add(onBoardPreReceiveHook);
    }

    public synchronized void removeOnBoardPreReceiveHook(OnBoardPreReceiveHook onBoardPreReceiveHook) {
        onBoardPreReceiveHooks.remove(onBoardPreReceiveHook);
    }

    @Override
    public void onPreReceive(ReceivePack rp, Collection<ReceiveCommand> commands) {
        sessionService.setCurrentUser(userService.getUserByEmail(userEmail));
        for (OnBoardPreReceiveHook onBoardPreReceiveHook : onBoardPreReceiveHooks) {
            for (ReceiveCommand cmd : commands) {
                // 判断该命令是否已被拒绝
                if (cmd.getResult().equals(ReceiveCommand.Result.NOT_ATTEMPTED)) {
                    onBoardPreReceiveHook.onPreReceive(rp, cmd);
                }
            }
        }
    }

}
