package gui;

import api.Customer;
import api.Data;
import api.Product;
import api.ShoppingCart;

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

public class ShoppingCartWindow {
    private BasicFrame shoppingCartFrame;
    private Data data;
    public ShoppingCartWindow(Data data) {
        shoppingCartFrame = new BasicFrame(new BorderLayout());
        this.data = data;
        shoppingCartFrame.setName("ShoppingCartWindow");
        data.addActiveFrame(shoppingCartFrame);

        shoppingCartFrame.createPanels();

        JLabel title = new JLabel("Shopping Cart");
        title.setFont(new Font("Times New Roman", Font.ITALIC, 70));

        shoppingCartFrame.north.setLayout(new FlowLayout(FlowLayout.CENTER));
        shoppingCartFrame.north.add(title);

        Dimension buttonSize = new Dimension(100,50);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);

        shoppingCartFrame.south.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton editQuantityButton = new JButton("Edit Quantity");
        editQuantityButton.setPreferredSize(new Dimension(150,50));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(buttonSize);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setPreferredSize(new Dimension(150,50));

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



        shoppingCartFrame.setVisible(true);

        shoppingCartFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                shoppingCartFrame.dispose();
                System.exit(0);
            }
        });

        shoppingCartTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    if (shoppingCartTable.getSelectedRow() == -1){
                        editQuantityButton.setEnabled(false);
                    }else editQuantityButton.setEnabled(true);
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
                if (shoppingCartTable.getSelectedRow() != -1){
                    String productName = products[shoppingCartTable.getSelectedRow()][0].toString();
                    Product product = data.getProductFromProductsList(productName);
                    ViewProductWindow.addToCartDialog(product, data);
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
                data.DataSave();
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
}