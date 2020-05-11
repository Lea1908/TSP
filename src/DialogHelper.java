import javax.swing.*;
import java.awt.*;

public class DialogHelper {
    public static JFrame createFrame(int width, int height, String title) {
        // Create JFrame for save dialog
        JFrame frame = new JFrame(title);
        frame.setSize(width,height);
        frame.getContentPane().setLayout(new FlowLayout());
        return frame;
    }
    public static void displayFrameInCenter(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning" , JOptionPane.INFORMATION_MESSAGE);
    }
}
