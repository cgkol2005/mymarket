package api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Accounts_Manager {
    private static Scanner scanner = new Scanner(System.in);

    public User Sign_In(List<User> UsersList) {
        while (true) {
            int choice = 5;
            while(choice !=1) {
                try {
                    System.out.println("Press 0 to exit, press 1 to enter credentials");
                    choice = Integer.parseInt(scanner.next());
                    if (choice == 0) {
                        return null;
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
            List<User> Credentials = UsersList;
                for (User user : Credentials){
                    if (user.getUsername().equals(Username) && user.getPassword().equals(Password)){
                        System.out.println("Authentication complete!");
                        if (user instanceof Customer){
                            System.out.print(" You are a Customer of the e-shop.");
                            return user;
                        } else if (user instanceof Admin) {
                            System.out.print(" You are an Admin of the e-shop.");
                            return user;
                        }
                    }
                }
                System.out.println("The credentials you entered do not match any account. Please try again or go back.");
        }
    }



    public User Sign_In(String Username, String Password,List<User> UsersList){

            System.out.println("Username: " + Username);
            System.out.println("Password: " + Password);

            for (User user : UsersList){
                if (user.getUsername().equals(Username) && user.getPassword().equals(Password)){
                    System.out.println("Authentication complete!");
                    return user;
                }
            }
            System.out.println("The credentials you entered do not match any account. Please try again or go back.");
            return null;
    }

    public List<User> Sign_Up(List<User> UsersList) {
        System.out.println("This is the sign-up process.");
        System.out.println("Enter your firstname: ");
        String FirstName = scanner.next();
        System.out.println("Enter your lastname: ");
        String LastName = scanner.next();
        String Username = null;
        List<User> Credentials = UsersList;
        boolean UsernameExists = true;
        while (UsernameExists) {
            System.out.println("Enter your account's username(one word, no spaces): ");
            Username = scanner.next();

                for (User user : Credentials){
                    if (user.getUsername().equals(Username)){
                        System.out.println("The user name you entered already exists. Please enter a unique username.");
                        UsernameExists = true;
                        break;
                    }else{
                        UsernameExists = false;
                    }
                }
        }
        System.out.println("Username successfully entered.");
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
        User NewUser = new Customer(Username, Password, FirstName, LastName);
        System.out.println("Object created. Proof: Here's the object's info: " + NewUser.getUsername() + " " + NewUser.getPassword());
        UsersList.add(NewUser);
        System.out.println("If everything worked correctly the new user has been assigned. Please try to sign-in to confirm!");
        return UsersList;
    }



    public boolean Sign_Up(String Username, String Password, String Firstname, String Lastname, Data data) {
        System.out.println("This is the sign-up process.");

            for (User user : data.getUsersList()){
                if (user.getUsername().equals(Username)){
                    System.out.println("The user name you entered already exists. Please enter a unique username.");
                    return false;
                }
            }



        User NewUser = new Customer(Username, Password, Firstname, Lastname);
        data.AddUser(NewUser);
        System.out.println("Username successfully entered.");
        System.out.println("Password confirmed!");

        System.out.println("If everything worked correctly the new user has been assigned. Please try to sign-in to confirm!");
        return true;
    }
}
