package com.dongbao;

import com.dongbao.ui.SettingDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ApplicationComponent;

/**
 * 点击菜单 打开配置参数弹窗
 *
 * @auto:WangZhiKun
 * @Date:2021-08-23
 */
public class StopCodingSettingAction extends AnAction implements ApplicationComponent {

    @Override
    public void actionPerformed(AnActionEvent e) {
        SettingDialog settingDialog = new SettingDialog();
        settingDialog.setVisible(true);
    }
}
