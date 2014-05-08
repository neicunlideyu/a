package com.onboard.plugin.git.utils;

import com.onboard.plugin.git.model.PullRequest;

public class PullRequestHelper {

    public static boolean isMergeOperation(PullRequest item, PullRequest modifiedItem) {
        return item.getStatus().equals(PullRequest.Status.OPEN.value())
                && modifiedItem.getStatus().equals(PullRequest.Status.MERGED.value());
    }

    public static boolean isDeclineOperation(PullRequest item, PullRequest modifiedItem) {
        return item.getStatus().equals(PullRequest.Status.OPEN.value())
                && modifiedItem.getStatus().equals(PullRequest.Status.DECLINED.value());
    }

    public static boolean isReopenOperation(PullRequest item, PullRequest modifiedItem) {
        return item.getStatus().equals(PullRequest.Status.DECLINED.value())
                && modifiedItem.getStatus().equals(PullRequest.Status.OPEN.value());
    }

}
