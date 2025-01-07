package api;

import java.io.Serializable;

/**
 * Represents a customer in the program
 * A customer has personal details, a shopping cart, an order history,complete orders.
 */
public class Customer extends User implements Serializable {

    /** The first name of the customer. */
    protected String firstname;

    /** The last name of the customer. */
    protected String lastname;

    /** The order history of the customer. */
    private final OrderHistory orderHistory;

    /** The shopping cart of the customer. */
    private final ShoppingCart shoppingCart;

    /**
     * @param username  The username of the customer.
     * @param password  The password of the customer.
     * @param firstname The first name of the customer.
     * @param lastname  The last name of the customer.
     */
    public Customer(String username, String password,String firstname, String lastname){
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        orderHistory = new OrderHistory();
        shoppingCart = new ShoppingCart();
    }

    /**
     * Completes the current order for the customer.
     * The shopping cart is converted into an order, and the order is added to the order history.
     * The stock will be reduced inside the Order class.
     * @param data, the object with all the data of the program. (Products List, Users List)
     */
    public void completeOrder(Data data){
        Order order = new Order(shoppingCart, shoppingCart.getTotalPrice(), shoppingCart.getProducts_count(), data);
        orderHistory.addOrderToHistoy(order);
        shoppingCart.clear();
    }

    /**
     * Return the OrderHistory class, which represents all the orders that the customer made.
     * @return OrderHistory, the order history of the current Customer.
     */
    public OrderHistory getOrderHistory(){
        return orderHistory;
    }

    /**
     @return The customer's shopping cart.
     */
    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }

    /**
     * Returns an order based in the date given.
     * If the date given matches the date that the order has been,
     * then that order will be returned by this method.
     * @param date, the Exact date of the order to be returned.
     * @return </Order>
     */
    public Order getOrderBasedOnOrderDate(String date){
        return orderHistory.getOrderBasedOnOrderDate(date);
    }
}
