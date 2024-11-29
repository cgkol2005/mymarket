package api;

public class User {
    protected String Username;
    protected String Password;

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
}
