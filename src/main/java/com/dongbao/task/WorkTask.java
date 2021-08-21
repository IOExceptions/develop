package com.dongbao.task;


import com.dongbao.data.DataCenter;
import com.dongbao.ui.TipsDialog;

import java.time.LocalDateTime;
import java.util.TimerTask;

public class WorkTask extends TimerTask {
    @Override
    public void run() {
        DataCenter.status = DataCenter.RESTING;
        DataCenter.nextWorkTime = LocalDateTime.now().plusMinutes(DataCenter.settingData.getRestTime());
        TipsDialog tipsDialog = new TipsDialog();
        tipsDialog.setVisible(true);
    }
}
