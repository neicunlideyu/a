package com.onboard.plugin.git.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.domain.model.Activity;
import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.PullRequestActivityActionType;
import com.onboard.plugin.git.PushService;
import com.onboard.plugin.git.model.Push;
import com.onboard.service.activity.ActivityGenerator;
import com.onboard.service.activity.util.ActivityHelper;

/**
 * 生成Push时的activity
 * 
 * @author R
 * 
 */
@Service("pushActivityGeneratorBean")
public class PushActivityGenerator implements ActivityGenerator {

    public static final String PUSH_SUBJECT = "提交了代码";

    @Autowired
    private PushService pushService;

    @Override
    public String modelType() {
        return (new Push()).getType();
    }

    @Override
    public String modelService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Identifiable enrichModel(Identifiable identifiable) {
        return pushService.getPushById(identifiable.getId());
    }

    private Activity generateActivityByActionType(String actionType, String subject, Push push) {
        Activity activity = ActivityHelper.generateActivityByActionType(actionType, subject, push);

        activity.setProjectId(push.getProjectId());
        activity.setCompanyId(push.getCompanyId());
        activity.setTarget(push.getBranchName());

        return activity;
    }

    @Override
    public Activity generateCreateActivity(Identifiable item) {
        return generateActivityByActionType(PullRequestActivityActionType.PUSH, PUSH_SUBJECT, (Push) item);
    }

    @Override
    public Activity generateUpdateActivity(Identifiable item, Identifiable modifiedItem) {
        // TODO Auto-generated method stub
        return null;
    }

}
