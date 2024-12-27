package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow {
    private JFrame searchFrame;

    public SearchWindow() {
        searchFrame = new JFrame("Αναζήτηση Προϊόντος");
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setSize(500, 400);
        searchFrame.setLayout(null);

        JLabel label = new JLabel("Πληκτρολογήστε το όνομα του προϊόντος:");
        label.setBounds(20, 20, 300, 25);
        searchFrame.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(20, 50, 250, 25);
        searchFrame.add(textField);

        JButton searchButton = new JButton("Αναζήτηση");
        searchButton.setBounds(280, 50, 100, 25);
        searchFrame.add(searchButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(20, 100, 360, 25);
        searchFrame.add(resultLabel);

        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = textField.getText();
                if (productName.isEmpty()) {
                    resultLabel.setText("Παρακαλώ εισάγετε ένα όνομα προϊόντος.");
                } else {
                    resultLabel.setText("Αναζητείται το προϊόν: " + productName);
                }
            }
        });
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    }
}
