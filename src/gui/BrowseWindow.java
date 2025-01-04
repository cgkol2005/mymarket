package gui;

import api.Admin;
import api.Customer;
import api.Data;
import api.Product;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import static gui.ViewProductWindow.addToCartDialog;

public class BrowseWindow {
    private Data data;

    private JPanel centerPanel;

    private BasicFrame browseWindow;

    private List<JButton> buttonsList;

    private JButton categoryButton;

    private JTree categoryTree;

    private JScrollPane treeScrollPane;

    public BrowseWindow(Data data){
        this.data = data;
        buildUI();
    }

    private void buildUI() {

        browseWindow = new BasicFrame(new BorderLayout(3, 3));

        browseWindow.setName("BrowseWindow");
        data.addActiveFrame(browseWindow);

        //PANELS

        JPanel rightPanel = new JPanel();

        rightPanel.setPreferredSize(new Dimension(280, 0));
        GridLayout gridLayout = new GridLayout(3,1,0,14);
        rightPanel.setLayout(gridLayout);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel searchLabel = new JLabel("Search: ");
       // searchLabel.setPreferredSize(new Dimension(150,70));
        searchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JTextField searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(150,45));
        searchTextField.setFont(new Font("Lucida Console", Font.PLAIN, 25));
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchLabel.add(Box.createHorizontalStrut(400));
        JButton searchButton = new JButton("Search");
        searchButton.setSize(new Dimension(100,50));
        searchButton.setFont(new Font("Courier New", Font.BOLD, 20));
        searchPanel.add(Box.createHorizontalStrut(55));
        searchPanel.add(searchButton);
        rightPanel.add(searchPanel);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = removeAccents(searchTextField.getText()); // Normalize input text
                ArrayList<Product> searchResultsList = (ArrayList<Product>) data.getProductsFromSearch(searchText);

                if (searchResultsList != null) {
                    // Clear all components from the centerPanel
                    centerPanel.removeAll();

                    // Add only the buttons that match the search results
                    for (Product product : searchResultsList) {
                        for (JButton button : buttonsList) {
                            if (button.getText().equals(product.getTitle())) {
                                centerPanel.add(button);
                                button.setVisible(true);
                            }
                        }
                    }

                    // Refresh the layout
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
            }
        });






        browseWindow.add(rightPanel, BorderLayout.EAST);


        JPanel leftPanel = new JPanel(new BorderLayout());

        leftPanel.setPreferredSize(new Dimension(280, 0));
        JButton categoryButton = new JButton("Product Categories");
        leftPanel.add(categoryButton,BorderLayout.NORTH);

        categoryTree = new JTree();
        categoryTree.setVisible(false); // Hidden initially
        categoryTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeScrollPane = new JScrollPane(categoryTree);
        treeScrollPane.setVisible(false);
        leftPanel.add(treeScrollPane, BorderLayout.CENTER);

        browseWindow.add(leftPanel, BorderLayout.WEST);

        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleTreeVisibility();
            }
        });

        categoryTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();
            if (selectedNode == null) return;
            //String[] categories = {"Φρέσκα τρόφιμα","Κατεψυγμένα τρόφιμα","Προϊόντα ψυγείου","Αλλαντικά","Αλκοολούχα ποτά","Μη αλκοολούχα ποτά","Καθαριστικά για το σπίτι",
            //"Απορρυπαντικά ρούχων","Καλλυντικά","Προϊόντα στοματικής υγιεινής","Πάνες","Δημητριακά","Ζυμαρικά","Σνακ","Έλαια","Κονσέρβες","Χαρτικά"};
            if (selectedNode.isLeaf()) {
                String subcategory = selectedNode.getUserObject().toString();
                updateCenterPanel(subcategory);
            }else{
                String category = selectedNode.toString();
                updateCenterPanel(category);
            }

        });

        initializeCategoryTree();

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0, 80));

        buttonsList = new ArrayList<>();

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int i = 0; i < data.getProductsList().size(); i++) {
            JButton productButton = new JButton(data.getProductsList().get(i).getTitle());
            productButton.setActionCommand(data.getProductsList().get(i).getTitle());
            productButton.setFont(new Font("Courier New", Font.PLAIN, 30));
            productButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            productButton.setMinimumSize(new Dimension(720, 90));
            productButton.setMaximumSize(new Dimension(720, 90));
            productButton.setPreferredSize(new Dimension(720, 90));
            productButton.addActionListener(new ButtonClickListener());
            buttonsList.add(productButton);
            if (centerPanel != null) {
                centerPanel.add(productButton);
            }
        }
        JScrollPane scroll = new JScrollPane(centerPanel);
        browseWindow.add(scroll, BorderLayout.CENTER);


        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(0, 80));
        JLabel myMarketLabel = new JLabel("MyMarket");
        myMarketLabel.setFont(new Font("Courier New", Font.ITALIC, 70));
        northPanel.add(myMarketLabel);
        browseWindow.add(northPanel, BorderLayout.NORTH);

        //Let's divide the bottom panel into three different boxes. Use a grid maybe? A box?
        GridLayout bottomPanelGrid = new GridLayout(1, 3, 2, 0); //creates a grid layout with 3 boxes horizontally.
        southPanel.setLayout(bottomPanelGrid); //adds this layout as the manager of the yellow bottom panel

        //creating 3 different panels to represent our little boxes.
        JPanel pinkPanel_BottomLeft = new JPanel();
        JPanel yellowPanel_BottomMid = new JPanel();
        JPanel greenPanel_BottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));




        //assigning to the mainBottom panel each little panel one by one.
        southPanel.add(pinkPanel_BottomLeft);
        southPanel.add(yellowPanel_BottomMid);
        //southPanel.add(greenPanel_BottomRight);

        //finally, adding the mainBottom panel that holds the other 3 to the main Frame
        //browseWindow.add(southPanel,BorderLayout.SOUTH);

        Font buttonFont = new Font("Inconsolata", Font.BOLD, 20);
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        Dimension buttonDimension = new Dimension(200, 70);
        backButton.setPreferredSize(buttonDimension);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        greenPanel_BottomRight.add(backButton);
        backButton.setVerticalAlignment(SwingConstants.CENTER);

        JButton refreshButton = new JButton("Refresh Window");
        refreshButton.setPreferredSize(buttonDimension);
        refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshButton.setFont(buttonFont);
        pinkPanel_BottomLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
        pinkPanel_BottomLeft.add(refreshButton);



        southPanel.add(greenPanel_BottomRight);
        southPanel.add(greenPanel_BottomRight);

        //finally, adding the mainBottom panel that holds the other 3 to the main Frame
        browseWindow.add(southPanel, BorderLayout.SOUTH);
        //southPanel.add(backButton);


        browseWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadFrame();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (data.getCurrentUser() instanceof Admin){
                    data.removeActiveFrame("BrowseWindow");
                    browseWindow.dispose();
                    data.getFrameFromActiveFramesList("AdminWindow").setVisible(true);
                }else{
                    data.removeActiveFrame("BrowseWindow");
                    browseWindow.dispose();
                    data.getFrameFromActiveFramesList("CustomerWindowOptions").setVisible(true);
                }

            }
        });
        browseWindow.setVisible(true);



    }
    public void reloadFrame(){
        browseWindow.dispose();
        new BrowseWindow(data);
    }



    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command_ProductTitle = e.getActionCommand();

            System.out.println("Button Pressed: " + command_ProductTitle);
            if (data.getCurrentUser() instanceof Customer) {
                String[] options = {"View Product", "Add to Cart", "Cancel"};
                int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", command_ProductTitle,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

                if (choice >= 0) {
                    System.out.println("User chose: " + options[choice] + ", choice= " + choice);
                    if (choice == 0) {
                        //view product
                        for (Product product : data.getProductsList()) {
                            if (product.getTitle().equals(command_ProductTitle)) {
                                new ViewProductWindow(product, data);
                                break;
                            }
                        }
                    } else if (choice == 1) {
                        //add product to cart
                        addToCartDialog(data.getProductFromProductsList(command_ProductTitle), data);
                    }
                } else {
                    System.out.println("User closed the window with X, choice=" + choice);
                }
            }else{
                String[] options = {"View Product", "Edit Product", "Cancel"};
                int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", command_ProductTitle,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

                if (choice >= 0) {
                    System.out.println("User chose: " + options[choice] + ", choice= " + choice);
                    if (choice == 0) {
                        //view product
                        for (Product product : data.getProductsList()) {
                            if (product.getTitle().equals(command_ProductTitle)) {
                                new ViewProductWindow(product, data);
                            }
                        }
                    } else if (choice == 1) {
                        //edit product
                        for (Product product : data.getProductsList()) {
                            if (product.getTitle().equals(command_ProductTitle)) {
                                new EditWindow(data, product);
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("User closed the window with X, choice=" + choice);
                }
            }
        }
    }

    public static String removeAccents(String text) {
        String newText = text.toLowerCase();
        // Normalize the string using NFD form (Canonical Decomposition)
        String normalized = Normalizer.normalize(newText, Normalizer.Form.NFD);
        // Remove all combining diacritical marks (characters categorized as \p{M})
        return normalized.replaceAll("\\p{M}", "");
    }

    private void toggleTreeVisibility() {
        boolean isVisible = categoryTree.isVisible();
        categoryTree.setVisible(!isVisible);
        treeScrollPane.setVisible(!isVisible);
        browseWindow.revalidate();
        browseWindow.repaint();
    }

    private void initializeCategoryTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");

        // Add Categories and Subcategories
        DefaultMutableTreeNode freshFoodNode = new DefaultMutableTreeNode("Φρέσκα τρόφιμα");
        freshFoodNode.add(new DefaultMutableTreeNode("Φρούτα"));
        freshFoodNode.add(new DefaultMutableTreeNode("Λαχανικά"));
        freshFoodNode.add(new DefaultMutableTreeNode("Ψάρια"));
        freshFoodNode.add(new DefaultMutableTreeNode("Κρέατα"));

        DefaultMutableTreeNode frozenFoodNode = new DefaultMutableTreeNode("Κατεψυγμένα τρόφιμα");
        frozenFoodNode.add(new DefaultMutableTreeNode("Κατεψυγμένα λαχανικά"));
        frozenFoodNode.add(new DefaultMutableTreeNode("Κατεψυγμένα κρέατα"));
        frozenFoodNode.add(new DefaultMutableTreeNode("Κατεψυγμένες πίτσες"));
        frozenFoodNode.add(new DefaultMutableTreeNode("Κατεψυγμένα γεύματα"));


        DefaultMutableTreeNode fridgeProductsNode = new DefaultMutableTreeNode("Προϊόντα ψυγείου");
        fridgeProductsNode.add(new DefaultMutableTreeNode("Τυριά"));
        fridgeProductsNode.add(new DefaultMutableTreeNode("Γιαούρτια"));
        fridgeProductsNode.add(new DefaultMutableTreeNode("Γάλα"));
        fridgeProductsNode.add(new DefaultMutableTreeNode("Βούτυρο"));


        DefaultMutableTreeNode preparedMeatProducts = new DefaultMutableTreeNode("Αλλαντικά");
        preparedMeatProducts.add(new DefaultMutableTreeNode("Ζαμπόν"));
        preparedMeatProducts.add(new DefaultMutableTreeNode("Σαλάμι"));
        preparedMeatProducts.add(new DefaultMutableTreeNode("Μπέικον"));


        DefaultMutableTreeNode drinksNode = new DefaultMutableTreeNode("Μη αλκοολούχα ποτά");
        drinksNode.add(new DefaultMutableTreeNode("Χυμοί"));
        drinksNode.add(new DefaultMutableTreeNode("Αναψυκτικά"));
        drinksNode.add(new DefaultMutableTreeNode("Ενεργειακά ποτά"));
        drinksNode.add(new DefaultMutableTreeNode("Νερό"));


        DefaultMutableTreeNode householdCleaners = new DefaultMutableTreeNode("Καθαριστικά για το σπίτι");
        householdCleaners.add(new DefaultMutableTreeNode("Καθαριστικά για το πάτωμα"));
        householdCleaners.add(new DefaultMutableTreeNode("Καθαριστικά για τα τζάμια"));
        householdCleaners.add(new DefaultMutableTreeNode("Καθαριστικά κουζίνας"));

        DefaultMutableTreeNode laundryDetergents = new DefaultMutableTreeNode("Απορρυπαντικά ρούχων");
        laundryDetergents.add(new DefaultMutableTreeNode("Σκόνες πλυντηρίου"));
        laundryDetergents.add(new DefaultMutableTreeNode("Υγρά πλυντηρίου"));
        laundryDetergents.add(new DefaultMutableTreeNode("Μαλακτικά"));

        DefaultMutableTreeNode cosmetics = new DefaultMutableTreeNode("Καλλυντικά");
        cosmetics.add(new DefaultMutableTreeNode("Κρέμες προσώπου"));
        cosmetics.add(new DefaultMutableTreeNode("Μακιγιάζ"));
        cosmetics.add(new DefaultMutableTreeNode("Λοσιόν σώματος"));

        DefaultMutableTreeNode oralHygieneProducts = new DefaultMutableTreeNode("Προϊόντα στοματικής υγιεινής");
        oralHygieneProducts.add(new DefaultMutableTreeNode("Οδοντόκρεμες"));
        oralHygieneProducts.add(new DefaultMutableTreeNode("Οδοντόβουρτσες"));
        oralHygieneProducts.add(new DefaultMutableTreeNode("Στοματικά διαλύματα"));

        DefaultMutableTreeNode diapers = new DefaultMutableTreeNode("Πάνες");
        diapers.add(new DefaultMutableTreeNode("Πάνες για μωρά"));
        diapers.add(new DefaultMutableTreeNode("Πάνες ενηλίκων"));

        DefaultMutableTreeNode cereals = new DefaultMutableTreeNode("Δημητριακά");
        cereals.add(new DefaultMutableTreeNode("Νιφάδες καλαμποκιού"));
        cereals.add(new DefaultMutableTreeNode("Μούσλι"));
        cereals.add(new DefaultMutableTreeNode("Βρώμη"));

        DefaultMutableTreeNode pasta = new DefaultMutableTreeNode("Ζυμαρικά");
        pasta.add(new DefaultMutableTreeNode("Μακαρόνια"));
        pasta.add(new DefaultMutableTreeNode("Κριθαράκι"));
        pasta.add(new DefaultMutableTreeNode("Ταλιατέλες"));

        DefaultMutableTreeNode snacks = new DefaultMutableTreeNode("Σνακ");
        snacks.add(new DefaultMutableTreeNode("Πατατάκια"));
        snacks.add(new DefaultMutableTreeNode("Κράκερς"));
        snacks.add(new DefaultMutableTreeNode("Μπάρες δημητριακών"));

        DefaultMutableTreeNode oils = new DefaultMutableTreeNode("Έλαια");
        oils.add(new DefaultMutableTreeNode("Ελαιόλαδο"));
        oils.add(new DefaultMutableTreeNode("Ηλιέλαιο"));
        oils.add(new DefaultMutableTreeNode("Σογιέλαιο"));

        DefaultMutableTreeNode cannedGoods = new DefaultMutableTreeNode("Κονσέρβες");
        cannedGoods.add(new DefaultMutableTreeNode("Κονσέρβες ψαριών"));
        cannedGoods.add(new DefaultMutableTreeNode("Κονσέρβες λαχανικών"));
        cannedGoods.add(new DefaultMutableTreeNode("Κονσέρβες φρούτων"));

        DefaultMutableTreeNode paperProducts = new DefaultMutableTreeNode("Χαρτικά");
        paperProducts.add(new DefaultMutableTreeNode("Χαρτί υγείας"));
        paperProducts.add(new DefaultMutableTreeNode("Χαρτοπετσέτες"));
        paperProducts.add(new DefaultMutableTreeNode("Χαρτομάντηλα"));


        root.add(freshFoodNode);
        root.add(frozenFoodNode);
        root.add(fridgeProductsNode);
        root.add(preparedMeatProducts);
        root.add(drinksNode);
        root.add(householdCleaners);
        root.add(laundryDetergents);
        root.add(cosmetics);
        root.add(oralHygieneProducts);
        root.add(diapers);
        root.add(cereals);
        root.add(pasta);
        root.add(snacks);
        root.add(oils);
        root.add(cannedGoods);
        root.add(paperProducts);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        categoryTree.setModel(treeModel);
    }

    private void updateCenterPanel(String subcategory) {
        centerPanel.removeAll();

        // Filter products based on subcategory
        for (JButton button : buttonsList) {
            Product product = data.getProductFromProductsList(button.getText());
            if (product.getSub_category().equals(subcategory)) {
                centerPanel.add(button);
            }
            if (product.getCategory().equals(subcategory)){
                centerPanel.add(button);
            }
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }


}
