package com.onboard.plugin.git;

public class PullRequestActivityActionType {

    // create PullRequest时生成
    public static final String OPENED = "opened";

    // reviewer执行approve操作生成
    public static final String APPROVED = "approved";

    // reviewer执行unapprove操作生成
    public static final String UNAPPROVED = "unapproved";

    // update PullRequest set status = 0
    public static final String REOPENED = "reopened";

    // update PullRequest set status = 2
    public static final String DECLINE = "decline";

    // update PullRequest set status = 1
    public static final String MERGED = "merged";

    // 评论代码时生成
    public static final String REVIEW = "review";

    // 直接评论时生成
    public static final String PUSH = "push";

}
