package gui;
import api.Admin;
import api.Data;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterWindow {
    private JFrame registerFrame;
    private JTextField nameField, descriptionField, priceField,stockField;
    private JComboBox category, subcategory;
    private JRadioButton weighted;
    private JRadioButton packaged;
    private boolean isWeighted;

    public RegisterWindow(Data data) {
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


        String[] categories = {
                "Φρέσκα τρόφιμα",
                "Κατεψυγμένα τρόφιμα",
                "Προϊόντα ψυγείου",
                "Αλλαντικά",
                "Αλκοολούχα ποτά",
                "Μη αλκοολούχα ποτά",
                "Καθαριστικά για το σπίτι",
                "Απορρυπαντικά ρούχων",
                "Καλλυντικά",
                "Προϊόντα στοματικής υγιεινής",
                "Πάνες",
                "Δημητριακά",
                "Ζυμαρικά",
                "Σνακ",
                "Έλαια",
                "Κονσέρβες",
                "Χαρτικά"
        };

        JComboBox<String> category = new JComboBox<>(categories);
        JComboBox<String> subcategory = new JComboBox<>();

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(50, 110, 150, 25);
        registerFrame.add(categoryLabel);

        category.setBounds(200, 110, 200, 25);
        registerFrame.add(category);

        JLabel subCategoryLabel = new JLabel("Subcategory");
        subCategoryLabel.setBounds(50, 150, 150, 25);
        registerFrame.add(subCategoryLabel);

        subcategory.setBounds(200, 150, 200, 25);
        registerFrame.add(subcategory);

        category.addActionListener(e -> {
            String selectedCategory = (String) category.getSelectedItem();
            subcategory.removeAllItems(); // Clear existing subcategories

            switch (selectedCategory) {
                case "Φρέσκα τρόφιμα":
                    String[] freshFoodSubcategories = {"Φρούτα", "Λαχανικά", "Ψάρια", "Κρέατα"};
                    for (String item : freshFoodSubcategories) subcategory.addItem(item);
                    break;

                case "Κατεψυγμένα τρόφιμα":
                    String[] frozenFoodSubcategories = {"Κατεψυγμένα λαχανικά", "Κατεψυγμένα κρέατα", "Κατεψυγμένες πίτσες", "Κατεψυγμένα γεύματα"};
                    for (String item : frozenFoodSubcategories) subcategory.addItem(item);
                    break;

                case "Προϊόντα ψυγείου":
                    String[] refrigeratedProductsSubcategories = {"Τυριά", "Γιαούρτια", "Γάλα", "Βούτυρο"};
                    for (String item : refrigeratedProductsSubcategories) subcategory.addItem(item);
                    break;

                case "Αλλαντικά":
                    String[] coldCutsSubcategories = {"Ζαμπόν", "Σαλάμι", "Μπέικον"};
                    for (String item : coldCutsSubcategories) subcategory.addItem(item);
                    break;

                case "Αλκοολούχα ποτά":
                    String[] alcoholicDrinksSubcategories = {"Μπύρα", "Κρασί", "Ούζο", "Τσίπουρο"};
                    for (String item : alcoholicDrinksSubcategories) subcategory.addItem(item);
                    break;

                case "Μη αλκοολούχα ποτά":
                    String[] nonAlcoholicDrinksSubcategories = {"Χυμοί", "Αναψυκτικά", "Νερό", "Ενεργειακά ποτά"};
                    for (String item : nonAlcoholicDrinksSubcategories) subcategory.addItem(item);
                    break;

                case "Καθαριστικά για το σπίτι":
                    String[] cleaningProductsSubcategories = {"Καθαριστικά για το πάτωμα", "Καθαριστικά για τα τζάμια", "Καθαριστικά κουζίνας"};
                    for (String item : cleaningProductsSubcategories) subcategory.addItem(item);
                    break;

                case "Απορρυπαντικά ρούχων":
                    String[] laundryDetergentsSubcategories = {"Σκόνες πλυντηρίου", "Υγρά πλυντηρίου", "Μαλακτικά"};
                    for (String item : laundryDetergentsSubcategories) subcategory.addItem(item);
                    break;

                case "Καλλυντικά":
                    String[] cosmeticsSubcategories = {"Κρέμες προσώπου", "Μακιγιάζ", "Λοσιόν σώματος"};
                    for (String item : cosmeticsSubcategories) subcategory.addItem(item);
                    break;

                case "Προϊόντα στοματικής υγιεινής":
                    String[] oralHygieneSubcategories = {"Οδοντόκρεμες", "Οδοντόβουρτσες", "Στοματικά διαλύματα"};
                    for (String item : oralHygieneSubcategories) subcategory.addItem(item);
                    break;

                case "Πάνες":
                    String[] diapersSubcategories = {"Πάνες για μωρά", "Πάνες ενηλίκων"};
                    for (String item : diapersSubcategories) subcategory.addItem(item);
                    break;

                case "Δημητριακά":
                    String[] cerealsSubcategories = {"Νιφάδες καλαμποκιού", "Μούσλι", "Βρώμη"};
                    for (String item : cerealsSubcategories) subcategory.addItem(item);
                    break;

                case "Ζυμαρικά":
                    String[] pastaSubcategories = {"Μακαρόνια", "Κριθαράκι", "Ταλιατέλες"};
                    for (String item : pastaSubcategories) subcategory.addItem(item);
                    break;

                case "Σνακ":
                    String[] snacksSubcategories = {"Πατατάκια", "Κράκερς", "Μπάρες δημητριακών"};
                    for (String item : snacksSubcategories) subcategory.addItem(item);
                    break;

                case "Έλαια":
                    String[] oilsSubcategories = {"Ελαιόλαδο", "Ηλιέλαιο", "Σογιέλαιο"};
                    for (String item : oilsSubcategories) subcategory.addItem(item);
                    break;

                case "Κονσέρβες":
                    String[] cannedProductsSubcategories = {"Κονσέρβες ψαριών", "Κονσέρβες λαχανικών", "Κονσέρβες φρούτων"};
                    for (String item : cannedProductsSubcategories) subcategory.addItem(item);
                    break;

                case "Χαρτικά":
                    String[] paperProductsSubcategories = {"Χαρτί υγείας", "Χαρτοπετσέτες", "Χαρτομάντηλα"};
                    for (String item : paperProductsSubcategories) subcategory.addItem(item);
                    break;

                default:
                    // No subcategories for this category
                    break;
            }
        });



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

        weighted = new JRadioButton("Weighted Product");
        packaged = new JRadioButton("Packaged Product");

        weighted.setBounds(50,270,150,30);
        registerFrame.add(weighted);

        packaged.setBounds(300,270,150,30);
        registerFrame.add(packaged);

        ButtonGroup group = new ButtonGroup();

        group.add(weighted);
        group.add(packaged);

        weighted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isWeighted = true;
                System.out.println("isWeighted = true;");
            }
        });

        packaged.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isWeighted = false;
                System.out.println("isWeighted = false;");
            }
        });



        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String categorytext = category.getSelectedItem().toString();
                    String subCategory = subcategory.getSelectedItem().toString();
                    double price = Double.parseDouble(priceField.getText());
                    String stockin = stockField.getText();
                    if (!weighted.isSelected() && !packaged.isSelected()){
                        JOptionPane.showMessageDialog(null, "You need to select if the product is weighted or packaged!","Error!",JOptionPane.ERROR_MESSAGE);
                    }else {

                        Object stock;
                        if (stockin.contains(".")) {
                            stock = Double.parseDouble(stockin);
                        } else {
                            stock = Integer.parseInt(stockin);
                        }
                        if (price <= 0) {
                            JOptionPane.showMessageDialog(registerFrame,
                                    "Price must be greater than 0.",
                                    "Invalid Price",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if ((stock instanceof Integer && (Integer) stock <= 0) || (stock instanceof Double && (Double) stock <= 0)) {
                            JOptionPane.showMessageDialog(registerFrame,
                                    "Stock must be greater than 0.",
                                    "Invalid Stock",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        if (data.productExists(name)) {
                            JOptionPane.showMessageDialog(registerFrame,
                                    "A product with the same title already exists!",
                                    "Product Exists",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        Admin admin = (Admin) data.getCurrentUser();
                        if (stock instanceof Double && !isWeighted) {
                            JOptionPane.showMessageDialog(null, "You can't enter a decimal value on a packaged product!", "Error", JOptionPane.ERROR_MESSAGE);

                        } else {
                            admin.registerProduct(name, description, categorytext, subCategory, price, stock, data, isWeighted);
                            JOptionPane.showMessageDialog(registerFrame,
                                    "Product successfully registered!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    registerFrame.dispose();

                        }
                    }




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

        registerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.dataSave();
                registerFrame.dispose();
                System.exit(0);
            }
        });

    }

}