package gui;

import javax.swing.*;
import java.awt.*;

public class HistoryWindow {
    private JFrame historyFrame;

    public HistoryWindow() {
        historyFrame = new JFrame("Ιστορικό παραγγελιών");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historyFrame.setSize(500, 400);
        historyFrame.setLayout(new FlowLayout());
        JLabel messageLabel1 = new JLabel("To ιστορικό των παραγγελιών σου είναι το εξης:");
        historyFrame.add(messageLabel1);
        historyFrame.setLocationRelativeTo(null);
        historyFrame.setVisible(true);
    }
}