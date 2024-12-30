package gui;
import api.Admin;
import api.Data;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow {
    private JFrame registerFrame;
    private JTextField nameField, descriptionField, categoryField, subcategoryField, priceField,stockField;

    public RegisterWindow(Data data,Admin admin) {
        registerFrame = new JFrame("Καταχώρηση προιόντος");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(500, 400);
        registerFrame.setLayout(null);
        registerFrame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Title");
        nameLabel.setBounds(50, 30, 150, 25);
        registerFrame.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(200, 30, 200, 25);
        registerFrame.add(nameField);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(50, 70, 150, 25);
        registerFrame.add(descriptionLabel);
        descriptionField=new JTextField();
        descriptionField.setBounds(200, 70, 200, 25);
        registerFrame.add(descriptionField);

        JLabel categoryLabel=new JLabel("Category");
        categoryLabel.setBounds(50, 110, 150, 25);
        registerFrame.add(categoryLabel);
        categoryField=new JTextField();
        categoryField.setBounds(200, 110, 200, 25);
        registerFrame.add(categoryField);

        JLabel subCategoryLabel = new JLabel("Subcategory");
        subCategoryLabel.setBounds(50, 150, 150, 25);
        registerFrame.add(subCategoryLabel);

        subcategoryField = new JTextField();
        subcategoryField.setBounds(200, 150, 200, 25);
        registerFrame.add(subcategoryField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(50, 190, 150, 25);
        registerFrame.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(200, 190, 200, 25);
        registerFrame.add(priceField);

        JLabel availabilityLabel = new JLabel("Stock");
        availabilityLabel.setBounds(50, 230, 150, 25);
        registerFrame.add(availabilityLabel);

        stockField = new JTextField();
        stockField.setBounds(200, 230, 200, 25);
        registerFrame.add(stockField);
        JButton submitButton=new JButton("Submit");
        submitButton.setBounds(100, 300, 100, 30);
        registerFrame.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String category = categoryField.getText();
                    String subCategory = subcategoryField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    String stockin = stockField.getText();

                    Object stock;
                    if (stockin.contains(".")) {
                        stock = Double.parseDouble(stockin);
                    } else {
                        stock = Integer.parseInt(stockin);
                    }

                    admin.RegisterProduct(name, description, category, subCategory, price, stock,data);

                    JOptionPane.showMessageDialog(registerFrame,
                            "Product successfully registered!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    registerFrame.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(registerFrame,
                            "Invalid input! Please ensure price and stock are valid numbers.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registerFrame,
                            "Error: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton=new JButton("Back");
        backButton.setBounds(250, 300, 100, 30);
        registerFrame.add(backButton);
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.dispose();
            }
        });

        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(true);

    }
}