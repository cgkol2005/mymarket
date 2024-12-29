package gui;

import api.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerWindow {
    private JFrame customerFrame;

    public CustomerWindow(Data data) {
        customerFrame = new JFrame("Πελάτης");
        customerFrame.setSize(500, 400);
        customerFrame.setLayout(new FlowLayout());
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel message = new JLabel("Καλωσήρθατε στο mymarket!!!");
        customerFrame.add(message);
        JLabel message1 = new JLabel("Καλωσήρθατε στο σύστημα για πελάτη");
        customerFrame.add(message1);
        JLabel message2 = new JLabel("Τι ενέργεια επιθυμείς να κάνεις;");
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

        customerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });
    }
}
