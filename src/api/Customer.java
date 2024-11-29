package api;

public class Customer extends User{
    protected String Firstname;
    protected String Lastname;
    private Order Order;
    private OrderHistory OrderHistory;
    private ShoppingCart ShoppingCart;

    public Customer(String Username, String Password, String Firstname, String Lastname){

        super(Username, Password);

        this.Firstname = Firstname;
        this.Lastname = Lastname;

        Order = new Order();
        OrderHistory = new OrderHistory();

        ShoppingCart = new ShoppingCart();
    }
    public String getFirstname() {
        return Firstname;
    }
    public String getLastname() {
        return Lastname;
    }
}
