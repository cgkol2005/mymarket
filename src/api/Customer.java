package api;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    protected String Firstname;
    protected String Lastname;
    private Order Order;
    private OrderHistory OrderHistory;
    private ShoppingCart ShoppingCart;

    public Customer(String Username, String Password,String Firstname, String Lastname){

        super(Username, Password);
        this.Firstname = Firstname;
        this.Lastname = Lastname;

        //Order = new Order();
        OrderHistory = new OrderHistory();

        ShoppingCart = new ShoppingCart();
    }
    public void setFirstname(String Firstname){
        this.Firstname = Firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getFirstname() {
        return Firstname;
    }
    public String getLastname() {
        return Lastname;
    }
}
