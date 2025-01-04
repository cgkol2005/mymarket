package gui;
import api.*;

import javax.swing.*;
public class EditWindow {
    private JFrame editframe;
    private Data data;
    private Product product;

    public EditWindow(Data data, Product product)
    {
        this.product = product;
        this.data=data;

        openEditProductWindow(product.getTitle());


    }


    private void openEditProductWindow(String productTitle) {
        Product product=data.getProductFromProductsList(productTitle);

        JFrame updateFrame = new JFrame("Ενημέρωση:"+productTitle);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setSize(500, 400);
        updateFrame.setLayout(null);
        updateFrame.setLocationRelativeTo(null);

        JLabel infoLabel=new JLabel("Επεξεργασία για το προϊόν: "+productTitle);
        infoLabel.setBounds(20, 20, 300, 25);
        updateFrame.add(infoLabel);

        JLabel titleLabel = new JLabel("Νέος Τίτλος:");
        titleLabel.setBounds(20, 60, 150, 25);
        updateFrame.add(titleLabel);

        JTextField titleField = new JTextField(product.getTitle());
        titleField.setBounds(180, 60, 200, 25);
        updateFrame.add(titleField);

        JLabel descriptionLabel = new JLabel("Νέα περιγραφή:");
        descriptionLabel.setBounds(20, 100, 150, 25);
        updateFrame.add(descriptionLabel);

        JTextField descriptionField = new JTextField(product.getDescription());
        descriptionField.setBounds(180, 100, 200, 25);
        updateFrame.add(descriptionField);

        JLabel categoryLabel = new JLabel("Νέα κατηγορία:");
        categoryLabel.setBounds(20, 140, 150, 25);
        updateFrame.add(categoryLabel);

        JTextField categoryField = new JTextField(product.getCategory());
        categoryField.setBounds(180, 140, 200, 25);
        updateFrame.add(categoryField);

        JLabel subcategoryLabel = new JLabel("Νέα υποκατηγορία:");
        subcategoryLabel.setBounds(20, 180, 150, 25);
        updateFrame.add(subcategoryLabel);

        JTextField subcategoryField = new JTextField(product.getSub_category());
        subcategoryField.setBounds(180, 180, 200, 25);
        updateFrame.add(subcategoryField);

        JLabel priceLabel = new JLabel("Νέα τιμή:");
        priceLabel.setBounds(20, 220, 150, 25);
        updateFrame.add(priceLabel);

        JTextField priceField = new JTextField(String.valueOf(product.getPrice()));
        priceField.setBounds(180, 220, 200, 25);
        updateFrame.add(priceField);

        JLabel stockLabel = new JLabel("Νέο απόθεμα:");
        stockLabel.setBounds(20, 260, 150, 25);
        updateFrame.add(stockLabel);

        JTextField stockField = new JTextField(product instanceof PackagedProduct ? String.valueOf(((PackagedProduct) product).getStock()) :
                String.valueOf(((WeightedProduct) product).getStock()));
        stockField.setBounds(180, 260, 200, 25);
        updateFrame.add(stockField);

        JButton saveButton = new JButton("Αποθήκευση");
        saveButton.setBounds(180, 300, 120, 25);
        updateFrame.add(saveButton);


        saveButton.addActionListener(e -> {
            try {
                Product product2 = data.getProductFromProductsList(productTitle);
                String newTitle = titleField.getText();
                String newDescription=descriptionField.getText();
                String newCategory=categoryField.getText();
                String newSubcategory=subcategoryField.getText();
                double newPrice=Double.parseDouble(priceField.getText());
                if (product2 instanceof PackagedProduct){
                    int newStock = Integer.parseInt(stockField.getText());
                    Admin admin = (Admin) data.getCurrentUser();
                    admin.EditProducts(productTitle,newTitle, newDescription, newCategory, newSubcategory, newPrice, newStock, data);
                    JOptionPane.showMessageDialog(updateFrame, "Οι αλλαγές αποθηκεύτηκαν!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                    updateFrame.dispose();
                }else {
                    double newStock = Double.parseDouble(stockField.getText());
                    Admin admin = (Admin) data.getCurrentUser();
                    admin.EditProducts(productTitle,newTitle, newDescription, newCategory, newSubcategory, newPrice, newStock, data);
                    JOptionPane.showMessageDialog(updateFrame, "Οι αλλαγές αποθηκεύτηκαν!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                    updateFrame.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(updateFrame, "Λάθος μορφή τιμής ή αποθέματος!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateFrame.setVisible(true);
    }



    private Object parseStock(String stockText) {
        try {
            if (stockText.contains(".")) {
                return Double.parseDouble(stockText);
            } else {
                return Integer.parseInt(stockText);
            }
        } catch (NumberFormatException ex) {
            return null;
        }
    }


}
