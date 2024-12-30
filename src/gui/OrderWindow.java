package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderWindow {
    private JFrame orderFrame;

    public OrderWindow() {
        orderFrame = new JFrame("Ολοκλήρωση παραγγελίας");
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setSize(500, 400);
        orderFrame.setLayout(null);
        JLabel messageLabel1 = new JLabel("To συνολικό ποσό της παραγγελίας είναι:");
        messageLabel1.setBounds(20, 50, 400, 25);
        orderFrame.add(messageLabel1);
        orderFrame.setLocationRelativeTo(null);
        orderFrame.setVisible(true);
    }

}
