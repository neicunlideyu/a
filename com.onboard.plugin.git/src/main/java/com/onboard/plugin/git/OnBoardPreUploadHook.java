package com.onboard.plugin.git;

import java.util.Collection;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.transport.ServiceMayNotContinueException;
import org.eclipse.jgit.transport.UploadPack;

/**
 * 处理读请求的钩子
 * 
 * @author XR
 * 
 */
public interface OnBoardPreUploadHook {

    /**
     * 处理请求前
     * 
     * @param up
     * @param wants
     * @param cntOffered
     */
    void onBeginNegotiateRound(String userEmail, UploadPack up, Collection<? extends ObjectId> wants, int cntOffered)
            throws ServiceMayNotContinueException;

    /**
     * 处理请求后
     * 
     * @param userEmail
     * @param up
     * @param wants
     * @param cntCommon
     * @param cntNotFound
     * @param ready
     */
    void onEndNegotiateRound(String userEmail, UploadPack up, Collection<? extends ObjectId> wants, int cntCommon,
            int cntNotFound, boolean ready) throws ServiceMayNotContinueException;

    /**
     * 处理请求后，发送给用户前
     * 
     * @param userEmail
     * @param up
     * @param wants
     * @param haves
     */
    void onSendPack(String userEmail, UploadPack up, Collection<? extends ObjectId> wants, Collection<? extends ObjectId> haves)
            throws ServiceMayNotContinueException;
}
