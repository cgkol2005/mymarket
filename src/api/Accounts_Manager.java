package api;

import java.util.List;

public class Accounts_Manager {

    public User Sign_In(String Username, String Password,List<User> UsersList){

            System.out.println("Username: " + Username);
            System.out.println("Password: " + Password);

                for (User user : UsersList) {
                    if (user.getUsername().equals(Username) && user.getPassword().equals(Password)) {
                        System.out.println("Authentication complete!");
                        return user;
                    }
                }

            System.out.println("The credentials you entered do not match any account. Please try again or go back.");
            return null;
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
