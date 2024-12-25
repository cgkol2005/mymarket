package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 *
 *
 * @author Golfos Christos, @author Charitwn Mavridis
 */
public class SignInWindow{
    private JFrame frame;
    private Data data;
    private Accounts_Manager AccManager;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public SignInWindow(Data data, Accounts_Manager AccManager){
        frame = new JFrame("Sign-In");
        this.data = data;
        this.AccManager = AccManager;

        frame.setLayout(new FlowLayout());
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel username = new JLabel("Username");
        frame.add(username);
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(100, 20));
        frame.add(usernameField);

        JLabel password = new JLabel("Password");
        frame.add(password);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 20));
        frame.add(passwordField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Username = usernameField.getText();
                String Password = new String(passwordField.getPassword());

                User user = AccManager.Sign_In(Username, Password, data.getUsersList());

                if(user instanceof Customer){
                    frame.dispose();
                    new CustomerWindow();
                } else if (user instanceof Admin) {
                    frame.dispose();
                    new AdminWindow();
                }else{
                    JOptionPane.showMessageDialog(frame, "Λάθος username ή password!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }










}
