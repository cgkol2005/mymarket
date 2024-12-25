package gui;

import api.Accounts_Manager;
import api.Data;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SignUpWindow {
    private JFrame frame;
    private JTextField firstnameField, lastnameField, usernameField;
    private JPasswordField passwordField;
    private Data data;
    Accounts_Manager AccManager;

    public SignUpWindow(Data data,Accounts_Manager AccManager) {
        this.data = data;
        this.AccManager = AccManager;
        frame = new JFrame("Δημιουργία λογαριασμού");
        frame.setSize(500, 400);
        frame.setLayout(new FlowLayout());

        JLabel firstnameLabel = new JLabel("Όνομα:");
        frame.add(firstnameLabel);
        firstnameField = new JTextField();
        firstnameField.setPreferredSize(new Dimension(100, 20));
        frame.add(firstnameField);

        JLabel lastnameLabel = new JLabel("Επίθετο:");
        frame.add(lastnameLabel);
        lastnameField = new JTextField();
        lastnameField.setPreferredSize(new Dimension(100, 20));
        frame.add(lastnameField);

        JLabel usernameLabel = new JLabel("Όνομα χρήστη:");
        frame.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(100, 20));
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Κωδικός πρόσβασης:");
        frame.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 20));
        frame.add(passwordField);
    }

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
}
