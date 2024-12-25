package gui;

import api.Accounts_Manager;
import api.Data;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI_Main {
    private JFrame mainFrame;
    private Data data;
    private Accounts_Manager AccManager;

    GUI_Main(){
        data = new Data();
        AccManager = new Accounts_Manager();
        mainFrame = new JFrame("MyMarket");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(400,400);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign up");

        mainFrame.add(signInButton);
        mainFrame.add(signUpButton);

        mainFrame.setVisible(true);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new SignInWindow(data, AccManager);
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                SignUpWindow SignUpWin = new SignUpWindow(data, AccManager);
                List<User> NewUser = SignUpWin.SignUp(mainFrame);
                data.UpdateUserData(NewUser);
                data.DataSave(); //giati den ftanei pote edw??

            }
        });


    }


    public static void main(String[] args) {
        GUI_Main main = new GUI_Main();
    }
}
