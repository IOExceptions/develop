package com.dongbao.task;

import com.dongbao.common.Constant;
import com.dongbao.service.ProblemService;
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

    /**
     * 查询超期需求url
     * */
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
            Map<String, String> header=new HashMap<>();
            header.put("Content-Type","application/json");
            header.put("User-Agent","PostmanRuntime/7.28.4");
            header.put("UAccept-Encoding","gzip, deflate, br");
            header.put("UConnection","keep-alive");
            String result = httpClientUtil.doPostJson(Constant.url+REQUEST_ADDRESS, params,header);
            System.out.println("获取成功");
            /* 调用解析超期需求提醒的方法 */
            ProblemService.nalysisStaleDated(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NotificationGroup notificationGroup = new NotificationGroup("提示", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification("执行调用接口查询！", MessageType.INFO);
        Notifications.Bus.notify(notification);
    }
}
