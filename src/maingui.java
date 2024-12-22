import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class maingui {
    private JFrame mainFrame;
    public maingui() {
        mainFrame = new JFrame("Σύνδεση");
        mainFrame.setSize(500, 400);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel messageLabel1 = new JLabel("<html>Εάν έχεις λογαριασμό, πάτα Sign In.<br>Εάν θέλεις να δημιουργήσεις έναν, πάτα Sign Up.</html>");
        mainFrame.add(messageLabel1);

        JButton signinButton = new JButton("Sign In");
        JButton signupButton = new JButton("Sign up");
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signingui();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    if (username.equals("admin1") && password.equals("password1")) {
                        frame.dispose();
                        new admingui();
                    } else if (username.equals("user1") && password.equals("password1")) {
                        frame.dispose();
                        new customergui();
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
                    JOptionPane.showMessageDialog(frame, "Επιτυχής δημιουργία λογαριασμού!", "Εγγραφή", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    mainFrame.setVisible(true);
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
            customerFrame.setLocationRelativeTo(null);
            customerFrame.setVisible(true);
        }
    }
    public static void main(String[] args) {
        new maingui();
    }
}
