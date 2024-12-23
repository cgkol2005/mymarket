package api;

import java.io.Serializable;

public class User implements Serializable{
    protected String Username;
    protected String Password;
    public User(){}

    public User(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }


    public void searchProduct(){}

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
