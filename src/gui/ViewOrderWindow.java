package gui;

import api.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOrderWindow {
    private JFrame frame;
    private Data data;
    private Order order;
    ShoppingCart orderCart;

    public ViewOrderWindow(Data data2,String date){
        this.data = data2;
        order = data.getCurrentCustomerUser().getOrderBasedOnOrderDate(date);
        frame = new JFrame("Order: "+date+", details.");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(1000,720));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel orderDate = new JLabel("Order: "+date);
        orderDate.setHorizontalAlignment(SwingConstants.CENTER);
        orderDate.setVerticalAlignment(SwingConstants.CENTER);
        orderDate.setFont(new Font("Courier New", Font.BOLD, 35));

        frame.add(orderDate, BorderLayout.NORTH);

        String orderPrice = String.valueOf(order.getOrderPrice());
        JLabel orderPriceLabel = new JLabel("<html>Order's price: <br>"+orderPrice+"<html>");
        orderPriceLabel.setFont(new Font("Courier New", Font.BOLD, 29));

        JLabel message = new JLabel("<html>These are all<br>the products<br>you bought: <html>");
        message.setFont(new Font("Courier New", Font.BOLD, 29));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280,0));
        leftPanel.add(message);
        frame.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280,0));

        rightPanel.add(orderPriceLabel);
       frame.add(rightPanel, BorderLayout.EAST);

        orderCart = order.getOrderShoppingCart();


        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0,120));
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(southPanel,BorderLayout.SOUTH);

        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(100,60));
        closeButton.setFont(new Font("Times New Roman", Font.ITALIC, 20));

        southPanel.add(closeButton);


        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(50,50));
        frame.add(centerPanel,BorderLayout.CENTER);


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

        frame.add(scrollPane);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);

    }

    private Object[][] create2DArrayFromShoppingCart(){
        int productsCount = orderCart.getProducts_count();
        Object[][] table = new Object[productsCount][3];

        for(int i = 0; i < productsCount; i++){

            Product product = orderCart.getProductBasedOnListIndex(i);

            table[i][0] = product.getTitle();
            table[i][1] = orderCart.getProductQuantityInShoppingCart(product.getTitle());
            table[i][2] = orderCart.CalculateTotalPriceOfAProductInCart(product);
        }
        return table;
    }

}
