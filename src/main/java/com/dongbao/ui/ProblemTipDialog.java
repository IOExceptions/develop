package com.dongbao.ui;

import com.dongbao.data.DataCenter;
import com.dongbao.task.ProblemTask;

import javax.swing.*;
import java.util.Date;
import java.util.Timer;

public class ProblemTipDialog extends JDialog {


    public ProblemTipDialog() {
        DataCenter.problemTimer = new Timer();
        DataCenter.problemTimer.schedule(new ProblemTask(this), new Date(), 1000);
    }

}
