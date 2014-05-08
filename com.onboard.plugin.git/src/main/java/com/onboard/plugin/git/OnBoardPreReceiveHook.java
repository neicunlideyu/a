package com.onboard.plugin.git;

import org.eclipse.jgit.transport.ReceiveCommand;
import org.eclipse.jgit.transport.ReceivePack;

/**
 * git处理写请求前执行相关操作的钩子
 * 
 * @author XR
 * 
 */
public interface OnBoardPreReceiveHook {

    /**
     * 执行请求前执行
     * 
     * @param userEmail
     * @param rp
     * @param commands
     */
    void onPreReceive(ReceivePack rp, ReceiveCommand cmd);

}
