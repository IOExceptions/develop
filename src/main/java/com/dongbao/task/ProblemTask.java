package com.dongbao.task;

import com.dongbao.ui.ProblemTipDialog;
import com.dongbao.util.HttpClientUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class ProblemTask  extends TimerTask{

    public static final String REQUEST_ADDRESS="/crmProjectProblems/queryStaleDated";

    /**
     * 提醒弹窗
     * */
    ProblemTipDialog problemTipDialog;

    public ProblemTask(){

    }

    public ProblemTask(ProblemTipDialog problemTipDialog){
        this.problemTipDialog = problemTipDialog;
    }

    @Override
    public void run() {
        System.out.println("执行调用接口查询！");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("name","wangzhikun");
        try {
            String result = httpClientUtil.doPost(REQUEST_ADDRESS, params);
            System.out.println("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        NotificationGroup notificationGroup = new NotificationGroup("提示", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification("执行调用接口查询！", MessageType.INFO);
        Notifications.Bus.notify(notification);
    }
}
