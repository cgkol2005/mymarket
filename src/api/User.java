package api;

import java.io.Serializable;

/**
 * The user class represents a user in the system.
 * It stores username and password
 *
 */
public class User implements Serializable{
    protected String username;
    protected String password;

    /**
     * Default constructor for {@code User}.
     * Initializes a user with no username or password.
     */
    public User(){}

    /**
     *
     * @param username of the user
     * @param password of the user
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

}
