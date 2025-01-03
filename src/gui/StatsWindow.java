package gui;
import api.Data;

import javax.swing.*;

public class StatsWindow {
    private JFrame statsFrame;
    private Data data;

    public StatsWindow(Data data) {
        this.data=data;
        statsFrame=new JFrame("Στατιστικά Προϊόντων");
        statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statsFrame.setSize(500, 400);
        statsFrame.setLayout(null);

        JLabel unavailableLabel = new JLabel("Μη διαθέσιμα προϊόντα:");
        unavailableLabel.setBounds(20, 20, 200, 25);
        statsFrame.add(unavailableLabel);

        JTextArea unavailableArea = new JTextArea();
        unavailableArea.setBounds(20, 50, 250, 250);
        unavailableArea.setEditable(false);
        statsFrame.add(unavailableArea);
        unavailableArea.setText(getUnavailableProducts());

        JLabel popularLabel = new JLabel("Πιο δημοφιλή προϊόντα:");
        popularLabel.setBounds(300, 20, 200, 25);
        statsFrame.add(popularLabel);

        JTextArea popularArea = new JTextArea();
        popularArea.setBounds(300, 50, 250, 250);
        popularArea.setEditable(false);
        statsFrame.add(popularArea);

        popularArea.setText(getPopularProducts());

        statsFrame.setLocationRelativeTo(null);
        statsFrame.setVisible(true);
    }

    private String getUnavailableProducts() {
        return data.getUnavailableProducts();
    }

    private String getPopularProducts() {
        return null;
    }
}