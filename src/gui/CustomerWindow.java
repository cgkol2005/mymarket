package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerWindow {
    private JFrame customerFrame;

    public CustomerWindow() {
        customerFrame = new JFrame("Πελάτης");
        customerFrame.setSize(500, 400);
        customerFrame.setLayout(new FlowLayout());
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel message = new JLabel("Καλωσήρθατε στο mymarket!!!");
        customerFrame.add(message);
        JLabel message1 = new JLabel("Καλωσήρθατε στο σύστημα για πελάτη!!!");
        customerFrame.add(message1);
        JLabel message2 = new JLabel("      Τι ενέργεια επιθυμείς να κάνεις;  Κάνε επιλογή από τα παρακάτω          ");
        customerFrame.add(message2);

        JButton ShoppingCartButton=new JButton("Καλάθι");
        JButton searchProductButton=new JButton("Αναζήτηση προιόντος");
        JButton OrderHistoryButton=new JButton("Προβολή ιστορικού παραγγελιών");
        JButton OrderButton=new JButton("Ολοκλήρωση παραγγελίας");

        customerFrame.add(ShoppingCartButton);
        customerFrame.add(OrderHistoryButton);
        customerFrame.add(searchProductButton);
        customerFrame.add(OrderButton);
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setVisible(true);
        searchProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchWindow();

            }
        });
        OrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderWindow();

            }
        });
        OrderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoryWindow();

            }
        });
        ShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCartWindow();

            }
        });
    }
}
