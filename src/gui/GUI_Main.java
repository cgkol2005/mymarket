package gui;

import api.Accounts_Manager;
import api.Application_Controller;
import api.Data;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_Main {
    private JFrame mainFrame;
    private JButton signInButton;
    private JButton signUpButton;
    private JButton exitButton;
    private JLabel welcomeLabel; // yellow-underlined because the label is only used in the buildUI and nowhere else. Resolve on the end of the development.
    private Data data;//same as above
    private Accounts_Manager AccManager;//same as above
    Application_Controller AppController;

    public GUI_Main() {
        AppController = new Application_Controller();
        data = new Data();
        AccManager = new Accounts_Manager();
        buildUI();

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                new SignInWindow(data, AccManager,mainFrame);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                new SignUpWindow(data, AccManager,mainFrame);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });


        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });

    }

    private void buildUI() {
        mainFrame = new JFrame("MyMarket");
        mainFrame.setSize(1280, 720);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);


        welcomeLabel = new JLabel("Welcome to MyMarket e-shop!");
        Font labelFont = new Font("Courier New", Font.PLAIN, 40);
        welcomeLabel.setFont(labelFont);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(welcomeLabel, BorderLayout.CENTER);
        int topPadding = 100;
        labelPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, 0, 0));

        mainFrame.add(labelPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(300, 150);

        // Create sign in button with text label
        signInButton = new JButton("Sign In");
        signInButton.setFont(buttonFont);
        signInButton.setPreferredSize(buttonSize);

        JLabel signInLabel = new JLabel("To sign in to your account:");
        signInLabel.setFont(new Font("Arial", Font.PLAIN, 23));

        JPanel signInPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        signInPanel.add(signInLabel);
        signInPanel.add(signInButton);
        signInPanel.setMaximumSize(new Dimension(600, 150)); // Ensure uniform width
        signInPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create sign up button with text label
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(buttonFont);
        signUpButton.setPreferredSize(buttonSize);

        JLabel signUpLabel = new JLabel("To create a new account:");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 23));

        JPanel signUpPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        signUpPanel.add(signUpLabel);
        signUpPanel.add(signUpButton);
        signUpPanel.setMaximumSize(new Dimension(600, 150)); // Ensure uniform width
        signUpPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create exit button with text label
        exitButton = new JButton("Quit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(buttonSize);

        JLabel exitLabel = new JLabel("To exit the application:");
        exitLabel.setFont(new Font("Arial", Font.PLAIN, 23));

        JPanel exitPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        exitPanel.add(exitLabel);
        exitPanel.add(exitButton);
        exitPanel.setMaximumSize(new Dimension(600, 150)); // Ensure uniform width
        exitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add button panels to the main button panel
        buttonPanel.add(signInPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(signUpPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exitPanel);

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(buttonPanel);
        box.add(Box.createVerticalGlue());

        mainFrame.add(box, BorderLayout.CENTER);


        mainFrame.setVisible(true);
    }



    public static void main(String[] args) {
        GUI_Main main = new GUI_Main();
    }
}
