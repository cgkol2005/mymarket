package api;

import java.util.List;

/**
 * The Accounts_manager class do method for user authentication and account creation sign in,sign up and verification
 */
public class AccountsManager {
    /**
     * Authenticates a user with name and password
     *
     * @param username   The username entered by the user.
     * @param password   The password entered by the user.
     * @param usersList  The list of existing users to check against.
     * @return return user if true else return null the account not match
     */
    public User signIn(String username, String password, List<User> usersList){

            System.out.println("username: " + username);
            System.out.println("password: " + password);

                for (User user : usersList) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
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
     * @param username username for the new account.
     * @param password  password for the new account.
     * @param firstname  first name of the new user.
     * @param lastname   last name of the new user.
     * @param data    {@link Data} for the list of users
     * @return {@code true} if the account is successfully created, or {@code false} if the username already exists.
     */
    public boolean signUp(String username, String password, String firstname, String lastname, Data data) {
        System.out.println("This is the sign-up process.");

            for (User user : data.getUsersList()){
                if (user.getUsername().equals(username)){
                    System.out.println("The user name you entered already exists. Please enter a unique username.");
                    return false;
                }
            }

        User newUser = new Customer(username, password, firstname, lastname);
        data.addUser(newUser);
        System.out.println("username successfully entered.");
        System.out.println("password confirmed!");

        System.out.println("If everything worked correctly the new user has been assigned. Please try to sign-in to confirm!");
        return true;
    }
}
