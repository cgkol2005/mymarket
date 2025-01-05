package gui;

import api.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerWindowOptions {
    private JFrame customerFrame;

    public CustomerWindowOptions(Data data) {
        customerFrame = new JFrame("MyMarketCustomer");
        customerFrame.setSize(1280, 720);
        customerFrame.setLayout(new BorderLayout());
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setName("CustomerWindowOptions");
        data.addActiveFrame(customerFrame);
        JLabel helloMessage = new JLabel("Hello "+data.getCurrentUser().getUsername()+"!");
        helloMessage.setPreferredSize(new Dimension(0,80));
        helloMessage.setFont(new Font("Courier New",Font.BOLD,50));
        helloMessage.setHorizontalAlignment(SwingConstants.CENTER);
        helloMessage.setVerticalAlignment(SwingConstants.CENTER);
        customerFrame.add(helloMessage, BorderLayout.NORTH);



        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280,0));
        //rightPanel.setBackground(Color.red);
        customerFrame.add(rightPanel,BorderLayout.EAST);


        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280,0));
        //leftPanel.setBackground(Color.blue);

        customerFrame.add(leftPanel, BorderLayout.WEST);



        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0,80));
        //southPanel.setBackground(Color.yellow);
        customerFrame.add(southPanel,BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(50,50));
        //centerPanel.setBackground(Color.green);
        customerFrame.add(centerPanel,BorderLayout.CENTER);

        BoxLayout buttonsBox = new BoxLayout(centerPanel, 1);
        centerPanel.setLayout(buttonsBox);
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

        centerPanel.add(Box.createRigidArea(new Dimension(0,50)));
        centerPanel.add(browserButton );
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(shoppingCartButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(orderHistoryButton );
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(logOutButton);

        browserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFrame.setVisible(false);
                new BrowseWindow(data);
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFrame.setVisible(false);
                new ShoppingCartWindow(data);
            }
        });

        orderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFrame.setVisible(false);
                new OrderHistoryWindow(data);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.removeActiveFrame("CustomerWindowOptions");
                data.getFrameFromActiveFramesList("GUI_Main").setLocationRelativeTo(null);
                data.getFrameFromActiveFramesList("GUI_Main").setVisible(true);
                customerFrame.dispose();
            }
        });

        //when closing the window:

        customerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customerFrame.dispose();
                data.dataSave();
                System.exit(0);
            }
        });
        customerFrame.setVisible(true);
    }

}
