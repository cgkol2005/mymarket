package gui;

import api.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Golfos Christos, Charitwn Mavridis
 */
public class SignInWindow {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public SignInWindow(Data data, Accounts_Manager AccManager, JFrame mainFrame) {
        frame = new JFrame("Sign-In");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.PLAIN, 20);

        JPanel fieldsPanel = new JPanel();
        JPanel usernamePanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Adjusted gap
        JPanel passwordPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Adjusted gap
        usernamePanel.setMaximumSize(new Dimension(250,40));
        passwordPanel.setMaximumSize(new Dimension(250,40));
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        Dimension fieldsDimension = new Dimension(200, 30); // Adjusted dimension

        JLabel username = new JLabel("Username:");
        username.setFont(font);
        usernameField = new JTextField();
        usernameField.setFont(font);
        usernameField.setPreferredSize(fieldsDimension);
        usernameField.setMaximumSize(fieldsDimension);
        usernameField.setMinimumSize(fieldsDimension);
        usernamePanel.add(username);
        usernamePanel.add(usernameField);
        fieldsPanel.add(usernamePanel);

        JLabel password = new JLabel("Password:");
        password.setFont(font);
        passwordField = new JPasswordField();
        passwordField.setFont(font);
        passwordField.setPreferredSize(fieldsDimension);
        passwordField.setMaximumSize(fieldsDimension);
        passwordField.setMinimumSize(fieldsDimension);
        passwordPanel.add(password);
        passwordPanel.add(passwordField);
        fieldsPanel.add(passwordPanel);

        fieldsPanel.setBorder(new EmptyBorder(50,0,0,0));

        frame.add(fieldsPanel, BorderLayout.CENTER);



        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered buttons
        Dimension buttonsSize = new Dimension(300,100);
        buttonPanel.setPreferredSize(new Dimension(300,300));

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Courier New", Font.BOLD,22));
        submitButton.setPreferredSize(buttonsSize);
        submitButton.setMaximumSize(buttonsSize);
        submitButton.setMinimumSize(buttonsSize);
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Courier New", Font.BOLD,22));
        backButton.setPreferredSize(buttonsSize);
        backButton.setMaximumSize(buttonsSize);
        backButton.setMinimumSize(buttonsSize);

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Username = usernameField.getText();
                String Password = new String(passwordField.getPassword());

                User user = AccManager.Sign_In(Username, Password, data.getUsersList());

                if (user instanceof Customer) {
                    frame.dispose();
                    new CustomerWindow(data, (Customer) user);
                } else if (user instanceof Admin) {
                    frame.dispose();
                    new AdminWindow(data,(Admin) user);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        frame,
                        "Are you sure you want to exit the application now?",
                        "Quit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Yes", "No"},
                        "No"
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    data.DataSave();
                    System.exit(0);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainFrame.setVisible(true);
            }
        });
        submitButton.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(
                javax.swing.KeyStroke.getKeyStroke("ENTER"), "submitAction"
        );
        submitButton.getActionMap().put("submitAction", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.doClick();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SignInWindow(null, null, null);
    }


}
