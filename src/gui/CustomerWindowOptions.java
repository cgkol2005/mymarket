package gui;

import api.Customer;
import api.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerWindowOptions {
    private JFrame customerFrame;

    public CustomerWindowOptions(Data data, Customer user, JFrame mainFrame) {
        customerFrame = new JFrame("MyMarketCustomer");
        customerFrame.setSize(1280, 720);
        customerFrame.setLayout(new BorderLayout());
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setLocationRelativeTo(null);
        JLabel message = new JLabel("Hello "+user.getUsername()+"!");
        message.setPreferredSize(new Dimension(0,80));
        message.setFont(new Font("Courier New",Font.BOLD,50));
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        customerFrame.add(message, BorderLayout.NORTH);



        JPanel redRight = new JPanel();
        redRight.setPreferredSize(new Dimension(280,0));
        //redRight.setBackground(Color.red);
        customerFrame.add(redRight,BorderLayout.EAST);


        JPanel blueLeft = new JPanel();
        blueLeft.setPreferredSize(new Dimension(280,0));
        //blueLeft.setBackground(Color.blue);

        customerFrame.add(blueLeft, BorderLayout.WEST);



        JPanel yellowSouth = new JPanel();
        yellowSouth.setPreferredSize(new Dimension(0,80));
        //yellowSouth.setBackground(Color.yellow);
        customerFrame.add(yellowSouth,BorderLayout.SOUTH);

        JPanel greenCenter = new JPanel();
        greenCenter.setPreferredSize(new Dimension(50,50));
        //greenCenter.setBackground(Color.green);
        customerFrame.add(greenCenter,BorderLayout.CENTER);

        BoxLayout buttonsBox = new BoxLayout(greenCenter, 1);
        greenCenter.setLayout(buttonsBox);
        Font buttonFont = new Font("Times New Roman", Font.PLAIN,18);
        JButton browserButton = new JButton("Browse The Shop");
        JButton shoppingCartButton=new JButton("Shopping Cart");
        JButton orderHistoryButton = new JButton("Order History");
        JButton logOutButton = new JButton("Log-Out");


        Dimension buttonSize = new Dimension(300,100);

        shoppingCartButton.setPreferredSize(buttonSize);
        shoppingCartButton.setMinimumSize(buttonSize);
        shoppingCartButton.setMaximumSize(buttonSize);
        //shoppingCartButton.setBorderPainted(false);  // Disable the border
        //shoppingCartButton.setContentAreaFilled(false); //Disable filling content area

        shoppingCartButton.setFocusPainted(false);

        browserButton.setPreferredSize(buttonSize);
        browserButton.setMinimumSize(buttonSize);
        browserButton.setMaximumSize(buttonSize);

        browserButton.setFocusPainted(false);

        orderHistoryButton .setPreferredSize(buttonSize);
        orderHistoryButton .setMinimumSize(buttonSize);
        orderHistoryButton .setMaximumSize(buttonSize);

        orderHistoryButton.setFocusPainted(false);

        logOutButton.setPreferredSize(buttonSize);
        logOutButton.setMinimumSize(buttonSize);
        logOutButton.setMaximumSize(buttonSize);

        logOutButton.setFocusPainted(false);


        shoppingCartButton.setFont(buttonFont);
        browserButton.setFont(buttonFont);
        orderHistoryButton.setFont(buttonFont);
        logOutButton.setFont(buttonFont);




        shoppingCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        browserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        greenCenter.add(Box.createRigidArea(new Dimension(0,50)));
        greenCenter.add(browserButton );
        greenCenter.add(Box.createRigidArea(new Dimension(0,10)));
        greenCenter.add(shoppingCartButton);
        greenCenter.add(Box.createRigidArea(new Dimension(0,10)));
        greenCenter.add(orderHistoryButton );
        greenCenter.add(Box.createRigidArea(new Dimension(0,10)));
        greenCenter.add(logOutButton);

        browserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFrame.setVisible(false);
                new BrowseWindow(data, customerFrame);
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        orderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFrame.dispose();
                data.DataSave();
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });

        //when closing the window:
        {
       customerFrame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               customerFrame.dispose();
               data.DataSave();
               System.exit(0);
           }
       });}
       customerFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerWindowOptions(null,new Customer("Username","Password","Xaris","Mavridis"),null);
    }
}
