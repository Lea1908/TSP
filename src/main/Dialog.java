package main;

import javax.swing.*;
import java.awt.*;

public class Dialog {
    void addHeader(JFrame frame, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        frame.add(label);
    }
}
