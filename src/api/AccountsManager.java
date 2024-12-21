package api;

import java.io.*;
import java.util.Scanner;

public class AccountsManager {
    private static Scanner scanner = new Scanner(System.in);
    public static void Sign_In() {
        while (true) {
            int choice = 5;
            while(choice !=1) {
                try {
                    System.out.println("Press 0 to exit, press 1 to enter credentials");
                    choice = Integer.parseInt(scanner.next());
                    if (choice == 0) {
                        return;
                    } else if (choice != 1) {
                        System.out.println("Not an option, try again.");
                    }
                }catch(NumberFormatException NFE){
                    System.out.println(NFE + ": Not an option, try again.");
                }
            }

            scanner.nextLine();
            System.out.println("Enter your Username: ");
            String Username = scanner.nextLine();

            System.out.println("Enter your Password: ");
            String Password = scanner.nextLine();
            System.out.println("Username: " + Username);
            System.out.println("Password: " + Password);

            User authentication = null;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.ser"))) {
                while (true) {
                    try {
                        authentication = (User) in.readObject();
                        if (authentication.getUsername().equals(Username) && authentication.getPassword().equals(Password)) {
                            System.out.println("Authentication successful!");
                            if (authentication instanceof Admin) {
                                System.out.println("You are an Admin of the e-shop");
                            } else if (authentication instanceof Customer) {
                                System.out.println("You are a Customer of the e-shop");
                            }
                            return;
                        }
                    } catch (EOFException eof) {
                        System.err.println(eof);
                        System.out.print("file ended, the credentials you entered do not match any account. Try again: ");
                        break;
                    }
                }
            } catch (ClassNotFoundException | IOException E) {
                System.err.println(E);
                System.out.print("There was a problem reading from the credentials file!");
            }

        }
    }

    public static void Sign_Up() {
        System.out.println("This is the sign-up process.");
        System.out.println("Enter your firstname: ");
        String FirstName = scanner.next();
        System.out.println("Enter your lastname: ");
        String LastName = scanner.next();
        String Username = null;

        User user = null;
        while (true) {
            System.out.println("Enter your account's username(one word, no spaces): ");
            Username = scanner.next();
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.ser"))) {
                try {
                    while (true) {
                        user = (User) in.readObject();
                        if (user.getUsername().equals(Username)) {
                            System.out.println("The Username you entered has been taken. Please enter a unique Username.");
                            break;
                        }
                    }
                } catch (EOFException eof) {
                    System.err.println(eof);
                    System.out.println(" ,username successfully created.");
                    break;
                }
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e + "An error occurred with initialization of the Stream Input");
                System.out.println("Aborting . . .");
                ;
            }
        }

        String Password;
        while(true){
            System.out.println("Enter your account's password");
            Password = scanner.next();
            System.out.println("Confirm your password once again: ");
            String Password2 = scanner.next();
            if (Password.equals(Password2)){
                break;
            }else System.out.println("The passwords doesn't match! Please repeat the password process..");
        }
        System.out.println("Password confirmed!");
        User User = new Customer(Username, Password, FirstName, LastName);
        System.out.println("Object created. Proof: Here's the object's info: " + User.getUsername() + " " + User.getPassword());

        try (FileOutputStream fos = new FileOutputStream("credentials.ser", true);
             AppendableObjectOutputStream out = new AppendableObjectOutputStream(fos)) {
            out.writeObject(User);
        } catch (IOException e) {
            System.err.println(e);
            System.out.println("There was a problem. Couldn't write the object, or credentials.ser file not properly used.");
            Program_Close();
        }

        System.out.println("If everything worked correctly the new user has been assigned. Please try to sign-in to confirm!");
        LAUNCHER.print_credentials();
    }

    private static void Program_Close(){
        System.out.println("Program closing. . .");
        scanner.close();
        System.exit(0);
    }
}
