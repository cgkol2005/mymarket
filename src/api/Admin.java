package api;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String Username, String Password){
        super(Username, Password);
    }
    public void RegisterProduct(){}
    public void EditProducts(){}
    public void Statistics(){}
}
