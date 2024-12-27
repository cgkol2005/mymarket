package gui;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartWindow {
    private JFrame ShoppingCartFrame;

    public ShoppingCartWindow() {
        ShoppingCartFrame = new JFrame("Καλάθι αγορών");
        ShoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ShoppingCartFrame.setSize(500, 400);
        ShoppingCartFrame.setLayout(new FlowLayout());
        JLabel messageLabel1 = new JLabel("Τα πρροιόντα ειναι τα εξής:");
        ShoppingCartFrame.add(messageLabel1);
        ShoppingCartFrame.setLocationRelativeTo(null);
        ShoppingCartFrame.setVisible(true);
    }
}
