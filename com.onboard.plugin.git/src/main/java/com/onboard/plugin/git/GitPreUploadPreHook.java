package com.onboard.plugin.git;

import java.util.Collection;
import java.util.List;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.transport.PreUploadHook;
import org.eclipse.jgit.transport.ServiceMayNotContinueException;
import org.eclipse.jgit.transport.UploadPack;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("gitPreUploadPreHookBean")
public class GitPreUploadPreHook implements PreUploadHook {

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private List<OnBoardPreUploadHook> onBoardPreUploadHooks = Lists.newArrayList();

    public synchronized void addOnBoardPreUploadHook(OnBoardPreUploadHook onBoardPreUploadHook) {
        onBoardPreUploadHooks.add(onBoardPreUploadHook);
    }

    public synchronized void removeOnBoardPreUploadHook(OnBoardPreUploadHook onBoardPreUploadHook) {
        onBoardPreUploadHooks.remove(onBoardPreUploadHook);
    }

    @Override
    public void onBeginNegotiateRound(UploadPack up, Collection<? extends ObjectId> wants, int cntOffered)
            throws ServiceMayNotContinueException {
        for (OnBoardPreUploadHook onBoardPreUploadHook : onBoardPreUploadHooks) {
            onBoardPreUploadHook.onBeginNegotiateRound(userEmail, up, wants, cntOffered);
        }
    }

    @Override
    public void onEndNegotiateRound(UploadPack up, Collection<? extends ObjectId> wants, int cntCommon, int cntNotFound,
            boolean ready) throws ServiceMayNotContinueException {
        for (OnBoardPreUploadHook onBoardPreUploadHook : onBoardPreUploadHooks) {
            onBoardPreUploadHook.onEndNegotiateRound(userEmail, up, wants, cntCommon, cntNotFound, ready);
        }
    }

    @Override
    public void onSendPack(UploadPack up, Collection<? extends ObjectId> wants, Collection<? extends ObjectId> haves)
            throws ServiceMayNotContinueException {
        for (OnBoardPreUploadHook onBoardPreUploadHook : onBoardPreUploadHooks) {
            onBoardPreUploadHook.onSendPack(userEmail, up, wants, haves);
        }
    }

}
