package com.dongbao.service;


import com.dongbao.data.DataCenter;
import com.dongbao.data.SettingData;
import com.dongbao.task.ProblemTask;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

/**
 * 项目问题 定时服务
 *
 * @auto:WangZhiKun
 * @Date:2021-08-23
 */
public class ProblemService {

    /**
     * 设置settingData
     * */
    public static SettingData saveSetting(Boolean pushBeyondRequirements,Boolean pushBeyondBug,Boolean pushTaskSchedule){
        DataCenter.settingData = SettingData.of(pushBeyondRequirements,pushBeyondBug,pushTaskSchedule);
        return DataCenter.settingData;
    }

    /**
     * 打开消息提醒的任务
     * */
    public static String openTimer() {
        //清楚之前的所有任务
        DataCenter.problemTimer.cancel();
        //创建开启定时任务
        DataCenter.problemTimer = new Timer();
        DataCenter.problemTimer.schedule(new ProblemTask(),0,10000);
        DataCenter.status = DataCenter.WORKING;
        return String.format("开启超期需求推送提示成功");
    }

    /**
     * 关闭消息提醒的任务
     * */
    public static String closeTimer() {
        //清除之前的所有任务
        DataCenter.problemTimer.cancel();
        DataCenter.status = DataCenter.CLOSE;
        return String.format("关闭超期需求推送提示成功");
    }

    /**
     * 解析超期需求的json 并 对符合条件的进行弹窗提醒
     * */
    public static void nalysisStaleDated(String resultJson){

        System.out.println("开始解析");
    }



}
