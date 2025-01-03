package gui;

import api.Data;
import api.Product;
import api.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartWindow {
    private BasicFrame shoppingCartFrame;
    private Data data;
    public ShoppingCartWindow(Data data) {
        shoppingCartFrame = new BasicFrame(new BorderLayout());
        this.data = data;

        shoppingCartFrame.createPanels();

        JLabel title = new JLabel("Shopping Cart");
        title.setFont(new Font("Times New Roman", Font.ITALIC, 70));

        shoppingCartFrame.north.setLayout(new FlowLayout(FlowLayout.CENTER));
        shoppingCartFrame.north.add(title);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100,50));

        shoppingCartFrame.south.setLayout(new FlowLayout(FlowLayout.CENTER));
        shoppingCartFrame.south.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.getFrameFromActiveFramesList("CustomerWindowOptions").setVisible(true);
                shoppingCartFrame.dispose();
            }
        });

        String[] columnsNames = new String[]{"Product Name", "Quantity","Price"};
        Object[][] products = create2DArrayFromShoppingCart();
        JTable shoppingCartTable = new JTable(products,columnsNames);
        JScrollPane scrollPane = new JScrollPane(shoppingCartTable);

        shoppingCartFrame.add(scrollPane,BorderLayout.CENTER);

        shoppingCartFrame.setVisible(true);
    }

    private Object[][] create2DArrayFromShoppingCart(){
        Object[][] table = new Object[data.getCurrentCustomerUser().getShoppingCart().getProducts_count()][3];

        ShoppingCart cart = data.getCurrentCustomerUser().getShoppingCart();

        for(int i = 0; i < data.getCurrentCustomerUser().getShoppingCart().getProducts_count(); i++){

            Product product = cart.getProductBasedOnListIndex(i);

            table[i][0] = product.getTitle();
            table[i][1] = cart.getProductQuantityInShoppingCart(product.getTitle());
            table[i][2] = cart.CalculateTotalPriceOfAProductInCart(product);
        }
        return table;
    }


}
