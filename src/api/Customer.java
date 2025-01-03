package api;

import java.io.Serializable;

/**
 * Represents a customer in the program
 * A customer has personal details, a shopping cart, an order history,complete orders.
 */
public class Customer extends User implements Serializable {
    /** The first name of the customer. */
    protected String Firstname;
    /** The last name of the customer. */
    protected String Lastname;
    /** The current order of the customer. */
    private Order order;
    /** The order history of the customer. */
    private OrderHistory OrderHistory;
    /** The shopping cart of the customer. */
    private ShoppingCart ShoppingCart;
    /**
     * @param Username  The username of the customer.
     * @param Password  The password of the customer.
     * @param Firstname The first name of the customer.
     * @param Lastname  The last name of the customer.
     */

    public Customer(String Username, String Password,String Firstname, String Lastname){

        super(Username, Password);
        this.Firstname = Firstname;
        this.Lastname = Lastname;

        //Order = new Order();
        OrderHistory = new OrderHistory();

        ShoppingCart = new ShoppingCart();
    }
    /**
     * Completes the current order for the customer.
     * The shopping cart converted into an order and the order is add to order history.
     * the stock reduce
     */
    public void CompleteOrder(){
        order = new Order(ShoppingCart, ShoppingCart.getTotalPrice(),ShoppingCart.getProducts_count());
        OrderHistory.addOrderToHistoy(order);
        //Υλοποιηση: Οταν ολοκληρώνεται μια παραγγελία, πρεπει το stock των προϊόντων να μειώνεται ανάλογα.
    }
    /**
     * Sets the first name of the customer.
     *@param Firstname The new first name of the customer.
     */
    public void setFirstname(String Firstname){
        this.Firstname = Firstname;
    }
    /**
     * @param lastname The new last name of the customer.
     */
    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    /**
     @return The customer's shopping cart.
     */
    public ShoppingCart getShoppingCart(){
        return ShoppingCart;
    }
    /**
     * @return The first name of the customer.
     */
    public String getFirstname() {
        return Firstname;
    }
    /**
     * @return The last name of the customer.
     */
    public String getLastname() {
        return Lastname;
    }
}
