package api;

import java.io.Serializable;
/**
 * The user class represents a user in the system.
 * It stores username and password
 *
 */
public class User implements Serializable{
    protected String Username;
    protected String Password;
    /**
     * Default constructor for {@code User}.
     * Initializes a user with no username or password.
     */
    public User(){}

    /**
     *
     * @param Username of the user
     * @param Password of the user
     */
    public User(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }

    /**
     * Simulates a product search.
     */
    public void searchProduct(){}

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return Password;
    }
    /**
     * @return the username of the user
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param username the new username to set
     */
    public void setUsername(String username) {
        Username = username;
    }

    /**
     * @param password the new password to set
     */
    public void setPassword(String password) {
        Password = password;
    }
}
