package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminWindow {
    private JFrame adminFrame;
    public AdminWindow() {
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
    }
}
