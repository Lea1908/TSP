package main;

import javax.swing.*;

public class GlobalStatisticDialog extends Dialog{
    private GlobalStatistics globalStatistics;

    public GlobalStatisticDialog(GlobalStatistics globalStatistics) {
        this.globalStatistics = globalStatistics;
        setupDialog();
    }
    private void setupDialog() {
        // Create JFrame for global statistics dialog
        JFrame globalStatisticsFrame = DialogHelper.createFrame(1200, 1200, "Global statistics");

        // Add header
        addHeader(globalStatisticsFrame, "Global statistics");

        // todo display global statistics -> see GlobalStatistic class

        DialogHelper.displayFrameInCenter(globalStatisticsFrame);
    }
}
