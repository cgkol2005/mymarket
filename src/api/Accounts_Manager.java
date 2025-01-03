package api;

import java.util.List;

/**
 * The Accounts_manager class do method for user authentication and account creation sign in,sign up and verification
 */
public class Accounts_Manager {

    /**
     * Authenticates a user with name and password
     *
     * @param Username   The username entered by the user.
     * @param Password   The password entered by the user.
     * @param UsersList  The list of existing users to check against.
     * @return return user if true else return null the account not match
     */
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
    /**
     *  Create a new account if the username is unique.
     *
     * @param Username username for the new account.
     * @param Password  password for the new account.
     * @param Firstname  first name of the new user.
     * @param Lastname   last name of the new user.
     * @param data    {@link Data} for the list of users
     * @return {@code true} if the account is successfully created, or {@code false} if the username already exists.
     */

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
