package gui;
import api.Data;
import api.Product;

import javax.swing.*;
import java.util.List;

public class StatsWindow {
    private JFrame statsFrame;
    private Data data;

    public StatsWindow(Data data) {
        this.data=data;
        statsFrame=new JFrame("Στατιστικά Προϊόντων");
        statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statsFrame.setSize(700, 500);
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
        popularLabel.setBounds(350, 20, 200, 25);
        statsFrame.add(popularLabel);

        JTextArea popularArea = new JTextArea();
        popularArea.setEditable(false);
        JScrollPane popularScrollPane = new JScrollPane(popularArea);
        popularScrollPane.setBounds(350, 50, 250, 250);
        statsFrame.add(popularScrollPane);
        popularArea.setText(getPopularProducts());

        statsFrame.setLocationRelativeTo(null);
        statsFrame.setVisible(true);
    }

    private String getUnavailableProducts() {
        return data.getUnavailableProducts();
    }

    private String getPopularProducts() {
        StringBuilder popularProductsBuilder = new StringBuilder();
        List<Product> popularProducts = data.getProductPopular();

        if (popularProducts.isEmpty()) {
            popularProductsBuilder.append("Δεν υπάρχουν παραγγελίες.");
        } else {
            for (Product product : popularProducts) {
                popularProductsBuilder.append(product.getTitle())
                        .append(" (Παραγγελίες: ")
                        .append(product.getTimesOrdered())
                        .append(")\n");
            }
        }
        return popularProductsBuilder.toString().trim();
    }
}