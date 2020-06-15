package main;

import tsp.model.CityEntity;

import javax.swing.*;
import java.awt.*;
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
        // addHeader(localStatisticsFrame, "Local statistics");

        // todo display local statistics
        int resultCounter = 0;
        String[] columnNames = {"Tour", "Length"};
        Object[][] data = new Object[results.size()][2];

        for (Result result : results) {
            String bestTour = "";
            var counter = 0;
            for (CityEntity cityEntity : result.best_tour) {
                var delimiter = counter < (result.best_tour.length - 1) ? " => " : "";
                bestTour += (cityEntity.getName() + delimiter);
                counter++;
            }
            data[resultCounter][0] = bestTour;
            data[resultCounter][1] = result.getOpt_tour_len();
        }
        JTable table = new JTable(data, columnNames);
        localStatisticsFrame.setLayout(new BorderLayout());
        localStatisticsFrame.add(table.getTableHeader(), BorderLayout.PAGE_START);
        localStatisticsFrame.add(table, BorderLayout.CENTER);

        DialogHelper.displayFrameInCenter(localStatisticsFrame);
    }
}
