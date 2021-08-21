package com.dongbao.ui;

import com.dongbao.data.DataCenter;
import com.dongbao.data.SettingData;
import com.dongbao.service.TimerService;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class SettingDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton openRbtn;
    private JTextField workTimeTF;
    private JTextField restTimeTF;
    private JTextField textField1;
    /* 超期需求推送开关 */
    private JRadioButton beyondRequirementsButton;
    /* 缺陷分配推送开关 */
    private JRadioButton beyondBugButton;
    /* 任务完成情况推送开关 */
    private JRadioButton taskScheduleButton;

    public SettingDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("DHG插件配置");
        setLocation(400, 200);//距离屏幕左上角的其实位置
        setSize(500, 300);//对话框的长宽

        //绑定确定按钮事件
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        //绑定取消按钮事件
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        //绑定关闭按钮事件
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //绑定启用选择按钮时间
        openRbtn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                openRbtn.setText(openRbtn.isSelected() ? "Running" : "Stopped");
            }
        });
        /* 监听超期需求推送开关 */
        beyondRequirementsButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                DataCenter.pushBeyondRequirements = beyondRequirementsButton.isSelected();
                if(beyondRequirementsButton.isSelected()){
                    beyondRequirementsButton.setText("打开");
                }else{
                    beyondRequirementsButton.setText("关闭");
                }
            }
        });
        /* 监听缺陷分配推送开关 */
        beyondBugButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                DataCenter.pushBeyondBug = beyondBugButton.isSelected();
                if(beyondBugButton.isSelected()){
                    beyondBugButton.setText("打开");
                }else{
                    beyondBugButton.setText("关闭");
                }
            }
        });
        /* 监听任务完成情况推送开关 */
        taskScheduleButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                DataCenter.pushTaskSchedule = taskScheduleButton.isSelected();
                if(taskScheduleButton.isSelected()){
                    taskScheduleButton.setText("打开");
                }else{
                    taskScheduleButton.setText("关闭");
                }
            }
        });

        //初始化渲染设置界面
        SettingData settings = new SettingData();
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.loadFields(settings);
        DataCenter.settingData = settings;
        openRbtn.setSelected(DataCenter.settingData.isOpen());
        workTimeTF.setText(DataCenter.settingData.getWorkTime() + "");
        restTimeTF.setText(DataCenter.settingData.getRestTime() + "");
        openRbtn.setText(openRbtn.isSelected() ? "Running" : "Stopped");
    }

    /**
     * 点击确定
     */
    private void onOK() {
        //保持设置
        SettingData settings = TimerService.saveSetting(openRbtn.isSelected(), restTimeTF.getText(), workTimeTF.getText());
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.saveFields(settings);

        String notifyStr;
        if (openRbtn.isSelected()) {
            //开启定时
            notifyStr = TimerService.openTimer();
        } else {
            //关闭定时
            notifyStr = TimerService.closeTimer();
        }
        /* 设置弹窗提醒设置成功 */
        NotificationGroup notificationGroup = new NotificationGroup("CRM消息提醒推送开启成功", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification("CRM消息提醒推送开启成功", MessageType.INFO);
        Notifications.Bus.notify(notification);

        JOptionPane.showMessageDialog(null, notifyStr, "提醒",JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    public static void main(String[] args) {
        SettingDialog dialog = new SettingDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
