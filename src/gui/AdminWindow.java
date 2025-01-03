package gui;

import api.Admin;
import api.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminWindow {
    private JFrame adminFrame;
    public AdminWindow(Data data) {
        adminFrame = new JFrame("MyMarketAdmin");
        adminFrame.setSize(1280, 720);
        adminFrame.setLayout(new BorderLayout());
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setName("AdminWindow");
        data.addActiveFrame(adminFrame);
        JLabel helloMessage = new JLabel("Hello "+data.getCurrentUser().getUsername()+"!");
        helloMessage.setPreferredSize(new Dimension(0,80));
        helloMessage.setFont(new Font("Courier New",Font.BOLD,50));
        helloMessage.setHorizontalAlignment(SwingConstants.CENTER);
        helloMessage.setVerticalAlignment(SwingConstants.CENTER);
        adminFrame.add(helloMessage, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280,0));
        //rightPanel.setBackground(Color.red);
        adminFrame.add(rightPanel,BorderLayout.EAST);


        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280,0));
        //leftPanel.setBackground(Color.blue);

        adminFrame.add(leftPanel, BorderLayout.WEST);



        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0,80));
        //southPanel.setBackground(Color.yellow);
        adminFrame.add(southPanel,BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(50,50));
        //centerPanel.setBackground(Color.green);
        adminFrame.add(centerPanel,BorderLayout.CENTER);


        adminFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });

        BoxLayout buttonsBox = new BoxLayout(centerPanel, 1);
        centerPanel.setLayout(buttonsBox);
        Font buttonFont = new Font("Times New Roman", Font.PLAIN,18);
        JButton addProductButton = new JButton("Register Product");
        JButton editProductButton = new JButton("Edit Products");
        JButton statsButton = new JButton("Statistics");
        JButton logOutButton = new JButton("Log-Out");


        Dimension buttonSize = new Dimension(300,100);

        editProductButton.setPreferredSize(buttonSize);
        editProductButton.setMinimumSize(buttonSize);
        editProductButton.setMaximumSize(buttonSize);
        //editProductButton.setBorderPainted(false);  // Disable the border
        //editProductButton.setContentAreaFilled(false); //Disable filling content area

        editProductButton.setFocusPainted(false);

        addProductButton.setPreferredSize(buttonSize);
        addProductButton.setMinimumSize(buttonSize);
        addProductButton.setMaximumSize(buttonSize);

        addProductButton.setFocusPainted(false);





        statsButton.setPreferredSize(buttonSize);
        statsButton.setMinimumSize(buttonSize);
        statsButton.setMaximumSize(buttonSize);

        statsButton.setFocusPainted(false);

        logOutButton.setPreferredSize(buttonSize);
        logOutButton.setMinimumSize(buttonSize);
        logOutButton.setMaximumSize(buttonSize);

        logOutButton.setFocusPainted(false);


        editProductButton.setFont(buttonFont);
        addProductButton.setFont(buttonFont);

        statsButton.setFont(buttonFont);
        logOutButton.setFont(buttonFont);




        editProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createRigidArea(new Dimension(0,50)));
        centerPanel.add(addProductButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(editProductButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(statsButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(logOutButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));

        adminFrame.setVisible(true);

        addProductButton.addActionListener(e -> {
            try {
                new RegisterWindow(data);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.setVisible(false);
                new BrowseWindow(data);

            }
        });


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.removeActiveFrame("CustomerWindowOptions");
                data.getFrameFromActiveFramesList("GUI_Main").setLocationRelativeTo(null);
                data.getFrameFromActiveFramesList("GUI_Main").setVisible(true);
                adminFrame.dispose();
            }
        });

        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatsWindow(data);
            }
        });



    }
}

/*
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
        JLabel helloMessage = new JLabel("Hello "+data.getCurrentCustomerUser().getUsername()+"!");
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
               data.DataSave();
               System.exit(0);
           }
       });
       customerFrame.setVisible(true);
    }

}
 */

/*
adminFrame = new JFrame("Διαχειριστής");
        adminFrame.setSize(500, 400);
        adminFrame.setLayout(new FlowLayout());
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel message = new JLabel("Καλωσήρθατε στο σύστημα για διαχειριστή");
        adminFrame.add(message);
        JLabel message1 = new JLabel("Τι ενέργεια επιθυμείς να κάνεις;");
        adminFrame.add(message1);
        JButton addProductButton=new JButton("Καταχώρηση προιόντος");
        JButton editProductButton=new JButton("Ενημέρωση προιόντος");
        JButton searchProductButton=new JButton("Αναζήτηση προιόντος");
        JButton statsButton=new JButton("Προβολή στατιστικών");

        adminFrame.add(addProductButton);
        adminFrame.add(editProductButton);
        adminFrame.add(searchProductButton);
        adminFrame.add(statsButton);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
 */
