package main;

import javax.swing.*;
import java.awt.*;

public class GlobalStatisticDialog extends Dialog{
    private GlobalStatistics globalStatistics;

    public GlobalStatisticDialog(GlobalStatistics globalStatistics) {
        this.globalStatistics = globalStatistics;
        setupDialog();
    }
    private void setupDialog() {
        // Create JFrame for global statistics dialog
        JFrame globalStatisticsFrame = DialogHelper.createFrame(1200, 1200, "Global statistics");

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = globalStatisticsFrame.getContentPane();

        //avgLabel.setBounds(100,100,140, 40);

        //Lay out the label and scroll pane from top to bottom.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        contentPane.add(listPane, BorderLayout.CENTER);
        addComponentsToPane(contentPane, globalStatistics);
        DialogHelper.displayFrameInCenter(globalStatisticsFrame);
    }
    public static void addComponentsToPane(Container pane, GlobalStatistics globalStatistics) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        Box avgBox = Box.createHorizontalBox();
        addALabel("Average calculation time", avgBox);
        addATextField(globalStatistics.averageCalculationTime.toString(), avgBox);

        Box minBox = Box.createHorizontalBox();
        addALabel("Shortest calculation time", minBox);
        addATextField(globalStatistics.shortestCalulationTime.toString(), minBox);

        Box maxBox = Box.createHorizontalBox();
        addALabel("Longest calculation time", maxBox);
        addATextField(globalStatistics.longestCalculationTime.toString(), maxBox);
        pane.add(avgBox, BorderLayout.CENTER);
        pane.add(minBox, BorderLayout.CENTER);
        pane.add(maxBox, BorderLayout.CENTER);

    }

    private static void addALabel(String text, Container container) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(label);
    }
    private static void addATextField(String text, Container container) {
        JTextField textField = new JTextField(text);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setEditable(false);
        container.add(textField);
    }
}
