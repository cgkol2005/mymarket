/*
package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class maingui {
    private JFrame mainFrame;
    public maingui() {
        mainFrame = new JFrame("Σύνδεση");
        mainFrame.setSize(500, 400);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel messageLabel1 = new JLabel("Εάν έχεις λογαριασμό, πάτα Sign In.");
        JLabel messageLabel2 = new JLabel("Εάν θέλεις να δημιουργήσεις έναν, πάτα Sign Up.");

        mainFrame.add(messageLabel1);
        mainFrame.add(messageLabel2);

        JButton signinButton = new JButton("Sign In");
        JButton signupButton = new JButton("Sign up");
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new signingui();

            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new signupgui();

            }
        });

        mainFrame.add(signinButton);
        mainFrame.add(signupButton);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public class signingui
    {
        private JFrame frame;
        private JTextField usernameField;
        private JPasswordField passwordField;

        public signingui() {
            frame = new JFrame("Σύνδεση");
            frame.setSize(500, 400);
            frame.setLayout(new FlowLayout());

            JLabel username = new JLabel("USERNAME");
            frame.add(username);
            usernameField = new JTextField();
            usernameField.setPreferredSize(new Dimension(100, 20));
            frame.add(usernameField);

            JLabel password = new JLabel("PASSWORD");
            frame.add(password);
            passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(100, 20));
            frame.add(passwordField);

            JButton submitButton = new JButton("Submit");
            frame.add(submitButton);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    Accounts_Manager manager = new Accounts_Manager();
                    Data data = new Data();
                    List<User> Credentials = data.getUsersList();
                    User user = manager.Sign_In(username,password,Credentials);
                    if (user instanceof Customer) {
                        frame.dispose();
                        new customergui();

                    } else if (user instanceof Admin) {
                        frame.dispose();
                        new admingui();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Λάθος username ή password!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public class signupgui
    {

        private JFrame frame;
        private JTextField firstnameField, lastnameField, usernameField;
        private JPasswordField passwordField;
        public signupgui() {
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



            JButton completeButton = new JButton("Ολοκλήρωση");
            frame.add(completeButton);
            completeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String Username = usernameField.getText();
                    String Password = Arrays.toString(passwordField.getPassword());
                    String Firstname = firstnameField.getText();
                    String Lastname = lastnameField.getText();

                    Data data = new Data();
                    Accounts_Manager manager = new Accounts_Manager();
                    List<User> Users = data.getUsersList();
                    List<User> UsersUpdated = manager.Sign_Up(Username,Password,Firstname,Lastname, Users);
                    if (UsersUpdated == null){
                        JOptionPane.showMessageDialog(frame, "Ο λογαριασμος δεν δημιουργηθηκε επειδη το username yparxei hdh.", "Εγγραφή", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        data.UpdateUserData(UsersUpdated);
                        JOptionPane.showMessageDialog(frame, "Επιτυχής δημιουργία λογαριασμού!", "Εγγραφή", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        mainFrame.setVisible(true);
                    }


                }
            });

            frame.setLocationRelativeTo(null);
            mainFrame.setVisible(false);
            frame.setVisible(true);
        }
    }

    public class admingui {
        private JFrame adminFrame;
        public admingui() {
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

            addProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new addProductWindow();

                }
            });
            editProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new editProductWindow();

                }
            });
            searchProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new searchProductWindow();

                }
            });
            statsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new statsWindow();

                }
            });
            adminFrame.add(addProductButton);
            adminFrame.add(editProductButton);
            adminFrame.add(searchProductButton);
            adminFrame.add(statsButton);
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);
        }
    }
    public class addProductWindow {
        public addProductWindow() {
            JFrame frame=new JFrame("Καταχώρηση Προϊόντος");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label=new JLabel("Παράθυρο Καταχώρησης Προϊόντος");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
    public class editProductWindow {
        public editProductWindow() {
            JFrame frame=new JFrame("Ενημέρωση Προϊόντος");
            frame.setSize(500,400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label=new JLabel("Παράθυρο Ενεμέρωσης Προϊόντος");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
    public class searchProductWindow {
        public searchProductWindow() {
            JFrame frame=new JFrame("Αναζήτηση Προϊόντος");
            frame.setSize(500,400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label=new JLabel("Παράθυρο Αναζήτησης Προϊόντος");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
    public class statsWindow {
        public statsWindow() {
            JFrame frame=new JFrame("Στατιστικά");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label =new JLabel("Παράθυρο Στατιστικών");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public class customergui {
        private JFrame customerFrame;

        public customergui() {
            customerFrame = new JFrame("Πελάτης");
            customerFrame.setSize(500, 400);
            customerFrame.setLayout(new FlowLayout());
            customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel message = new JLabel("Καλωσήρθατε στο mymarket!!!");
            customerFrame.add(message);
            JLabel message1 = new JLabel("Καλωσήρθατε στο σύστημα για πελάτη");
            customerFrame.add(message1);
            JLabel message2 = new JLabel("Τι ενέργεια επιθυμείς να κάνεις;");
            customerFrame.add(message2);

            JButton ShoppingCartButton=new JButton("Καλάθι");
            JButton searchProductButton=new JButton("Αναζήτηση προιόντος");
            JButton OrderHistoryButton=new JButton("Προβολή ιστορικού παραγγελιών");
            JButton OrderButton=new JButton("Ολοκλήρωση παραγγελίας");
            ShoppingCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ShoppingCartWindow();

                }
            });
            OrderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new OrderWindow();

                }
            });
            searchProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new searchProductWindow();

                }
            });
            OrderHistoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new OrderHistoryWindow();

                }
            });
            customerFrame.add(ShoppingCartButton);
            customerFrame.add(OrderHistoryButton);
            customerFrame.add(searchProductButton);
            customerFrame.add(OrderButton);
            customerFrame.setLocationRelativeTo(null);
            customerFrame.setVisible(true);
        }
    }
    public class ShoppingCartWindow {
        public ShoppingCartWindow() {
            JFrame frame=new JFrame("Καλάθι");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label =new JLabel("Παράθυρο Καλαθιού");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
    public class OrderWindow {
        public OrderWindow() {
            JFrame frame=new JFrame("Παραγγελία");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label =new JLabel("Παράθυρο Παραγγελίας");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
        public class OrderHistoryWindow {
        public OrderHistoryWindow() {
            JFrame frame=new JFrame("Ιστορικό");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label =new JLabel("Παράθυρο Ιστορικού παραγγελιών");
            frame.add(label);
            frame.setLayout(new FlowLayout());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
    public static void main(String[] args) {
        new maingui();
    }
}



 */
