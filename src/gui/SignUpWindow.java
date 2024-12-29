package gui;

import api.Accounts_Manager;
import api.Data;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SignUpWindow {
    private JFrame frame;
    private JTextField firstnameField, lastnameField, usernameField;
    private JPasswordField passwordField;

    public SignUpWindow(Data data, Accounts_Manager AccManager, JFrame mainFrame) {
        frame = new JFrame("Sign-Up");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);


        JLabel title = new JLabel("Account Creation");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Courier New", Font.ITALIC,40));
        frame.add(title,BorderLayout.NORTH);
        title.setBorder(new EmptyBorder(30,0,0,0));

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between elements
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(250, 100);

        addField(fieldsPanel, gbc, "Όνομα:", firstnameField = new JTextField(), 0, fieldSize);
        addField(fieldsPanel, gbc, "Επίθετο:", lastnameField = new JTextField(), 1, fieldSize);
        addField(fieldsPanel, gbc, "Όνομα χρήστη:", usernameField = new JTextField(), 2, fieldSize);
        addField(fieldsPanel, gbc, "Κωδικός πρόσβασης:", passwordField = new JPasswordField(), 3, fieldSize);

        frame.add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton createButton = new JButton("Create");
        JButton backButton = new JButton("Back");

        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String firstname = firstnameField.getText();
                String lastname = lastnameField.getText();
               boolean AccCreated = AccManager.Sign_Up(username,password,firstname,lastname,data);
               if (AccCreated){
                   JOptionPane.showMessageDialog(frame, "Account successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                   frame.dispose();
                   mainFrame.setVisible(true);
               }else{
                   JOptionPane.showMessageDialog(frame, "Account not created! You need a unique username to create an account. Please enter a valid username.", "Error", JOptionPane.ERROR_MESSAGE);

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
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        frame,
                        "The Sign Up process will be aborted.Are you sure you want exit the application now?",
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
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int y, Dimension size) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(130, 65));
        Font font = new Font("Arial", Font.BOLD,27);
        label.setFont(font);
        textField.setFont(new Font("Cascade",Font.PLAIN, 28));

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        textField.setPreferredSize(size);
        textField.setMaximumSize(size);
        textField.setMinimumSize(size);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        panel.add(textField, gbc);
    }

    public static void main(String[] args) {
        new SignUpWindow(null, null, null);
    }


/*
    public List<User> SignUp(JFrame mainFrame){
        frame.setVisible(true);
        JButton completeButton = new JButton("Ολοκλήρωση");
        frame.add(completeButton);

        List<User> Users = data.getUsersList();

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Username = usernameField.getText();
                String Password = new String(passwordField.getPassword());
                String Firstname = firstnameField.getText();
                String Lastname = lastnameField.getText();

                List<User> UsersUpdated = AccManager.Sign_Up(Username,Password,Firstname,Lastname, Users);
                data.UpdateUserData(UsersUpdated);
                if (UsersUpdated == null){
                    JOptionPane.showMessageDialog(frame, "Ο λογαριασμος δεν δημιουργηθηκε επειδη το username yparxei hdh.", "Εγγραφή", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, "Επιτυχής δημιουργία λογαριασμού!", "Εγγραφή", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    mainFrame.setVisible(true);
                    data.DataSave();
                }

            }
        });
        return data.getUsersList();
    }

 */
}
