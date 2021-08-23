package com.dongbao;

import com.dongbao.data.DataCenter;
import com.dongbao.data.SettingData;
import com.dongbao.service.ProblemService;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.NotNull;

/**
 * 切入点,在项目初始化的时候加载上一次的属性配置内容到当前组件中
 *
 * @auto:WangZhiKun
 * @Date:2021-08-23
 */
public class StopCodingInitializer extends PreloadingActivity {

    private static final Logger LOG = Logger.getInstance(StopCodingInitializer.class);

    @Override
    public void preload(@NotNull ProgressIndicator indicator) {
        SettingData settings = new SettingData();
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.loadFields(settings);
        DataCenter.settingData = settings;
        if (settings.getPushBeyondRequirements()) {
            ProblemService.openTimer();
        }
    }
}
