package api;

public class Customer extends User{
    protected String Firstname;
    protected String Lastname;
    private Order Order;
    private OrderHistory OrderHistory;
    private ShoppingCart ShoppingCart;

    public Customer(String Username, String Password){

        super(Username, Password);

        Order = new Order();
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
