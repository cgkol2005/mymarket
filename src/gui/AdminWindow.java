package gui;

import api.Admin;
import api.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminWindow {
    private JFrame adminFrame;
    private Admin admin;
    public AdminWindow(Data data,Admin admin) {
        adminFrame = new JFrame("Διαχειριστής");
        adminFrame.setSize(500, 400);
        adminFrame.setLayout(new FlowLayout());
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel message = new JLabel("Καλωσήρθατε στο σύστημα για διαχειριστή");
        adminFrame.add(message);
        JLabel message1 = new JLabel("Τι ενέργεια επιθυμείς να κάνεις;");
        adminFrame.add(message1);
        JButton addProductButton=new JButton("Καταχώρηση προιόντος");
        JButton editProductButton=new JButton("Ενημέρωση προιόντος");
        JButton searchProductButton=new JButton("Αναζήτηση προιόντος");
        JButton statsButton=new JButton("Προβολή στατιστικών");


        adminFrame.add(addProductButton);
        adminFrame.add(editProductButton);
        adminFrame.add(searchProductButton);
        adminFrame.add(statsButton);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);

        adminFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });
        addProductButton.addActionListener(e -> {
            try {
                new RegisterWindow(data, admin);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

    }
}
