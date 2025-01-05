package gui;

import api.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class ShoppingCartWindow {
    private BasicFrame shoppingCartFrame;
    private Data data;
    private ShoppingCart cart;
    private JLabel totalPriceLabel;
    private double priceOfCart;
    public ShoppingCartWindow(Data data) {
        cart = data.getCurrentCustomerUser().getShoppingCart();
        shoppingCartFrame = new BasicFrame(new BorderLayout());
        this.data = data;
        shoppingCartFrame.setName("ShoppingCartWindow");
        data.addActiveFrame(shoppingCartFrame);

        shoppingCartFrame.createPanels();

        shoppingCartFrame.east.setBackground(Color.white);
        shoppingCartFrame.south.setBackground(Color.white);
        shoppingCartFrame.west.setBackground(Color.white);

        JLabel title = new JLabel("Shopping Cart");
        title.setFont(new Font("Times New Roman", Font.ITALIC, 70));

        priceOfCart = cart.getTotalPrice();
        totalPriceLabel = new JLabel("<html>Total price:<br>" +priceOfCart+"<html>");
        totalPriceLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        shoppingCartFrame.east.setLayout(new BorderLayout());
        shoppingCartFrame.east.add(totalPriceLabel);


        shoppingCartFrame.north.setLayout(new FlowLayout(FlowLayout.CENTER));
        shoppingCartFrame.north.add(title);

        Dimension buttonSize = new Dimension(100,50);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);

        shoppingCartFrame.south.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton completeOrderButton = new JButton("Complete Order");
        completeOrderButton.setPreferredSize(new Dimension(150,50));

        JButton editQuantityButton = new JButton("Edit Quantity");
        editQuantityButton.setPreferredSize(new Dimension(150,50));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(buttonSize);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setPreferredSize(new Dimension(150,50));

        shoppingCartFrame.south.add(completeOrderButton);
        shoppingCartFrame.south.add(editQuantityButton);
        shoppingCartFrame.south.add(deleteButton);
        shoppingCartFrame.south.add(refreshButton);
        shoppingCartFrame.south.add(backButton);


        String[] columnsNames = new String[]{"Product Name", "Quantity","Price"};
        Object[][] products = create2DArrayFromShoppingCart();
        DefaultTableModel model = new DefaultTableModel(products, columnsNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes all cells non-editable
            }
        };
        JTable shoppingCartTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(shoppingCartTable);

        shoppingCartFrame.add(scrollPane,BorderLayout.CENTER);

        editQuantityButton.setEnabled(false);

        shoppingCartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel selecionModel = shoppingCartTable.getSelectionModel();

        selecionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    if (shoppingCartTable.getSelectedRow()!=-1){
                        editQuantityButton.setEnabled(true);
                    }else{
                        editQuantityButton.setEnabled(false);
                    }
                }
            }
        });






        shoppingCartFrame.setVisible(true);

        shoppingCartFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.dataSave();
                shoppingCartFrame.dispose();
                System.exit(0);
            }
        });


        completeOrderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean somethingOutOfStock = false;
                for (Map.Entry<Product, Integer> entry : cart.getShoppingCartMap().entrySet()){
                    Product product = entry.getKey();
                    if (product instanceof WeightedProduct) {
                        if (((WeightedProduct) product).getStock() == 0) {
                            somethingOutOfStock = true;
                            JOptionPane.showMessageDialog(null, "The product: " + product.getTitle() + " has gone out of stock before you buy it!", "We're sorry. :(", JOptionPane.INFORMATION_MESSAGE);
                            cart.removeProduct(product.getTitle());
                            refreshButton.doClick();
                            //reloadFrame();
                        }
                    }else if (product instanceof PackagedProduct){
                        if (((PackagedProduct) product).getStock() == 0){
                            somethingOutOfStock = true;
                            JOptionPane.showMessageDialog(null, "The product: " + product.getTitle() + " has gone out of stock before you buy it!", "We're sorry. :(", JOptionPane.INFORMATION_MESSAGE);
                            cart.removeProduct(product.getTitle());
                            refreshButton.doClick();
                            //reloadFrame();
                        }
                    }
                }
                if (cart.getShoppingCartMap().isEmpty()){
                    JOptionPane.showMessageDialog(null, "You can't complete your order, you cart is still empty!", "you serious?", JOptionPane.ERROR_MESSAGE);
                } else if(!somethingOutOfStock){
                    data.getCurrentCustomerUser().completeOrder(data);
                    JOptionPane.showMessageDialog(null, "Order Completed!", "Order info", JOptionPane.INFORMATION_MESSAGE);
                    shoppingCartFrame.dispose();
                    data.getFrameFromActiveFramesList("CustomerWindowOptions").setVisible(true);
                }
            }
        });



        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadFrame();
            }
        });

        editQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Add", "Remove"};
                int choice = JOptionPane.showOptionDialog(null,"Do you want to add or remove quantity?","Product Edit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,JOptionPane.DEFAULT_OPTION);
                System.out.println(choice);
                String productName = products[shoppingCartTable.getSelectedRow()][0].toString();
                Product product = data.getProductFromProductsList(productName);
                if (choice == 0 ) {
                    if (shoppingCartTable.getSelectedRow() != -1) {
                        ViewProductWindow.addToCartDialog(product, data);
                    }
                }else{
                    removeFromCartDialog(product);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shoppingCartTable.getSelectedRow() != -1){
                    String productName = products[shoppingCartTable.getSelectedRow()][0].toString();
                    Customer user = (Customer) data.getCurrentUser();
                    user.getShoppingCart().removeProduct(productName);
                    JOptionPane.showMessageDialog(null, "Product "+productName+" removed from Cart!", "Product deleted successfully!", JOptionPane.INFORMATION_MESSAGE);
                    reloadFrame();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.getFrameFromActiveFramesList("CustomerWindowOptions").setVisible(true);
                data.dataSave();
                shoppingCartFrame.dispose();
            }
        });
    }

    private Object[][] create2DArrayFromShoppingCart(){
        Customer customer = (Customer) data.getCurrentUser();
        Object[][] table = new Object[customer.getShoppingCart().getProducts_count()][3];

        ShoppingCart cart = customer.getShoppingCart();

        for(int i = 0; i < customer.getShoppingCart().getProducts_count(); i++){

            Product product = cart.getProductBasedOnListIndex(i);

            table[i][0] = product.getTitle();
            table[i][1] = cart.getProductQuantityInShoppingCart(product.getTitle());
            table[i][2] = cart.CalculateTotalPriceOfAProductInCart(product);
        }
        return table;
    }

    private void reloadFrame(){
        shoppingCartFrame.dispose();
        new ShoppingCartWindow(data);
    }

    private void removeFromCartDialog(Product product) {
        Customer customer = (Customer) data.getCurrentUser();

        JFrame sliderFrame = new JFrame("Quantity to be removed from Shopping Cart");
        sliderFrame.setLayout(new BorderLayout());
        sliderFrame.setSize(800, 300);
        sliderFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        sliderFrame.setLocationRelativeTo(null);

        JLabel question = new JLabel("How much do you want to remove from your shopping cart?");
        question.setFont(new Font("Courier New", Font.ITALIC, 17));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setVerticalAlignment(SwingConstants.CENTER);
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setPreferredSize(new Dimension(0, 80));
        questionPanel.add(question, BorderLayout.CENTER);

        sliderFrame.add(questionPanel, BorderLayout.NORTH);


        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.setPreferredSize(new Dimension(100, 50));
        JSlider slider;
        if (product instanceof WeightedProduct) {
            int max = data.getCurrentCustomerUser().getShoppingCart().getProductQuantityInShoppingCart(product.getTitle()) * 100;
            slider = new JSlider(0, max, max / 2);
        } else if (product instanceof PackagedProduct) {
            int max = customer.getShoppingCart().getProductQuantityInShoppingCart(product.getTitle());
            slider = new JSlider(0, max, max / 2);
        } else {
            slider = new JSlider(0, 0, 0);
        }

        slider.setSize(100, 50);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);

        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        if (product instanceof WeightedProduct) {
            slider.setMajorTickSpacing((int) (((WeightedProduct) product).getStock() * 1000) / 10);
        } else if (product instanceof PackagedProduct) {
            slider.setMajorTickSpacing(((PackagedProduct) product).getStock() / 10);
        }


        //JLabel quantity = new JLabel(String.valueOf(slider.getValue())+" grams");
        JLabel quantity = new JLabel();
        quantity.setFont(new Font("MV Boli", Font.BOLD, 18));
        quantity.setVerticalAlignment(SwingConstants.CENTER);
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        sliderPanel.add(quantity, BorderLayout.NORTH);
        sliderPanel.add(slider, BorderLayout.CENTER);

        if (product instanceof WeightedProduct) {
            quantity.setText(String.valueOf(slider.getValue()) + " grams");
        } else {
            quantity.setText(String.valueOf(slider.getValue()) + " pieces");
        }


        sliderFrame.add(sliderPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(0, 38));
        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(100, 30));
        Font buttonFont = new Font("Cascadia mono", Font.ITALIC | Font.BOLD, 13);
        removeButton.setFont(buttonFont);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setFont(buttonFont);

        JButton left = new JButton("<");
        left.setPreferredSize(new Dimension(40, 30));
        left.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton right = new JButton(">");
        right.setPreferredSize(new Dimension(40, 30));
        right.setFont(new Font("Cascadia mono", Font.ITALIC, 11));

        //Buttons for weighted:
        JButton fifty = new JButton("+50");
        fifty.setPreferredSize(new Dimension(55, 30));
        fifty.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton minusFifty = new JButton("-50");
        minusFifty.setPreferredSize(new Dimension(55, 30));
        minusFifty.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton hundred = new JButton("+100");
        hundred.setPreferredSize(new Dimension(60, 30));
        hundred.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton minusHundred = new JButton("-100");
        minusHundred.setPreferredSize(new Dimension(60, 30));
        minusHundred.setFont(new Font("Cascadia mono", Font.ITALIC, 11));

        //Buttons for packaged:
        JButton five = new JButton("+5");
        five.setPreferredSize(new Dimension(46, 30));
        five.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton minusFive = new JButton("-5");
        minusFive.setPreferredSize(new Dimension(46, 30));
        minusFive.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton ten = new JButton("+10");
        ten.setPreferredSize(new Dimension(55, 30));
        ten.setFont(new Font("Cascadia mono", Font.ITALIC, 11));
        JButton minusTen = new JButton("-10");
        minusTen.setPreferredSize(new Dimension(55, 30));
        minusTen.setFont(new Font("Cascadia mono", Font.ITALIC, 11));

        removeButton.setBackground(Color.green);
        cancelButton.setBackground(Color.red);



        if (product instanceof WeightedProduct) {
            buttonPanel.add(minusHundred);
            buttonPanel.add(minusFifty);
        } else {
            buttonPanel.add(minusTen);
            buttonPanel.add(minusFive);
        }
        buttonPanel.add(left);
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(right);
        if (product instanceof WeightedProduct) {
            buttonPanel.add(fifty);
            buttonPanel.add(hundred);
        } else {
            buttonPanel.add(five);
            buttonPanel.add(ten);
        }

        sliderFrame.add(buttonPanel, BorderLayout.SOUTH);


        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (product instanceof WeightedProduct) {
                    quantity.setText(String.valueOf(slider.getValue()) + " grams");
                } else {
                    quantity.setText(String.valueOf(slider.getValue()) + " pieces");
                }
                removeButton.setEnabled(slider.getValue() != 0);
            }
        });

        minusHundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() - 100);
            }
        });

        minusFifty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() - 50);
            }
        });

        hundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() + 100);
            }
        });

        fifty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() + 50);
            }
        });

        minusTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() - 10);
            }
        });

        minusFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() - 5);
            }
        });

        ten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() + 10);
            }
        });

        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() + 5);
            }
        });

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() - 1);
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() + 1);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sliderFrame.dispose();
            }
        });

        sliderFrame.setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = 0;
                if (product instanceof WeightedProduct) {
                    choice = JOptionPane.showConfirmDialog(null, "Are you sure that you want to remove " + slider.getValue() + " grams of " + product.getTitle() + " from your cart?", "Confirmation", JOptionPane.YES_NO_OPTION);
                } else if (product instanceof PackagedProduct) {
                    choice = JOptionPane.showConfirmDialog(null, "Are you sure that you want to remove " + slider.getValue() + " pieces of " + product.getTitle() + " from your cart?", "Confirmation", JOptionPane.YES_NO_OPTION);
                }
                if (choice == JOptionPane.YES_OPTION) {
                    System.out.println("User chose YES");
                    customer.getShoppingCart().removeQuantityFromShoppingCart(product, slider.getValue());
                    JOptionPane.showMessageDialog(null, "Product removed from your shopping Cart!", "Product removed!", JOptionPane.INFORMATION_MESSAGE);
                    sliderFrame.dispose();
                } else {
                    System.out.println("User chose NO");
                }
            }
        });
    }
}