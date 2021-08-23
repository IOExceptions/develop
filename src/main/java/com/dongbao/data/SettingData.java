package com.dongbao.data;

import com.intellij.ide.util.PropertyName;
import org.eclipse.xtend.lib.annotations.Data;

/**
 * 配置中心 用于保存在 PropertiesComponent 中,还原DataCenter的属性配置
 *
 * @auto:WangZhiKun
 * @Date:2021-08-23
 */
@Data
public class SettingData {

    @PropertyName("StopCoding:SettingData:pushBeyondRequirements")
    private Boolean pushBeyondRequirements = true;

    @PropertyName("StopCoding:SettingData:pushBeyondBug")
    private Boolean pushBeyondBug = true;

    @PropertyName("StopCoding:SettingData:pushTaskSchedule")
    private Boolean pushTaskSchedule = true;

    public void setPushBeyondRequirements(Boolean pushBeyondRequirements){
        this.pushBeyondRequirements=pushBeyondRequirements;
    }

    public Boolean getPushBeyondRequirements(){
        return this.pushBeyondRequirements;
    }

    public void setPushBeyondBug(Boolean pushBeyondBug){
        this.pushBeyondBug=pushBeyondBug;
    }

    public Boolean getPushBeyondBug(){
        return this.pushBeyondBug;
    }

    public void setPushTaskSchedule(Boolean pushTaskSchedule){
        this.pushTaskSchedule=pushTaskSchedule;
    }

    public Boolean getPushTaskSchedule(){
        return this.pushTaskSchedule;
    }



    public static SettingData of(Boolean pushBeyondRequirements,Boolean pushBeyondBug,Boolean pushTaskSchedule) {
        SettingData settings = new SettingData();
        settings.setPushBeyondRequirements(pushBeyondRequirements);
        settings.setPushBeyondBug(pushBeyondBug);
        settings.setPushTaskSchedule(pushTaskSchedule);
        return settings;
    }



}
