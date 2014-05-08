package com.onboard.plugin.git;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceivePack;

/**
 * git处理写请求后执行相关操作的钩子
 * 
 * @author XR
 * 
 */
public interface OnBoardPostReceiveHook {

    /**
     * 执行请求后执行
     * 
     * @param userEmail
     * @param rp
     * @param commands
     */
    void onPostReceive(ReceivePack rp, ReceiveCommand cmd);

}
