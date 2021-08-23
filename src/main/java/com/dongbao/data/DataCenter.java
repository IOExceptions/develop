package com.dongbao.data;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.regex.Pattern;

/**
 * 配置实体
 *
 * @auto:WangZhiKun
 * @Date:2021-08-23
 */
public class DataCenter {
    public static final Integer CLOSE = 0;
    public static final Integer WORKING = 1;

    public static SettingData settingData = new SettingData();
    public static Integer status = CLOSE;

    /* 项目问题超期提醒定时器 */
    public static Timer problemTimer = new Timer();

    /* 超期需求推送开关 */
    public static String url;

    /* 超期需求推送开关 */
    public static Boolean pushBeyondRequirements;
    /* 缺陷分配推送开关 */
    public static Boolean pushBeyondBug;
    /* 任务完成情况推送开关 */
    public static Boolean pushTaskSchedule;

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String getSettingDesc() {
        if (CLOSE.equals(DataCenter.status)) {
            return "推送当前为关闭状态！";
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "";
    }
}
