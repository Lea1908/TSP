package main;

import javax.swing.*;
import java.util.List;

public class LocalStatisticDialog extends Dialog {
    private List<Result> results;
    public LocalStatisticDialog() {}
    public LocalStatisticDialog(List<Result> results) {
        this.results = results;
        setUpDialog();
    }
    public void setUpDialog() {
        // Create JFrame for local statistics dialog
        JFrame localStatisticsFrame = DialogHelper.createFrame(600, 600, "Local statistics");

        // Add header
        addHeader(localStatisticsFrame, "Local statistics");

        // todo display local statistics
        for (Result result : results) {

        }

        DialogHelper.displayFrameInCenter(localStatisticsFrame);
    }
}
