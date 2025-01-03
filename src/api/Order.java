package api;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The {@code Order} class represents an order in a system.
 * * It contains details about the shopping cart, the total price of the order,
 * the number of products bought, and the date the order was created.
 * and for save products
 */
 public class Order implements Serializable {
    private ShoppingCart Cart;
    private double OrderPrice;
    private int NumberOfProductsBought;
    private String OrderDate;
    /**
     * This constructor initializes the shopping cart, total order price,
     * number of products bought, and sets the order date to the current time.
     * @param Cart the shopping cart associated with the order
     * @param OrderPrice the total price of the order
     * @param NumberOfProductsBought the number of products purchased in the order
     */

    public Order(ShoppingCart Cart, double OrderPrice, int NumberOfProductsBought){
        this.Cart = Cart;
        this.OrderPrice = OrderPrice;
        this.NumberOfProductsBought = NumberOfProductsBought;

        LocalDateTime now = LocalDateTime.now();
        // Define a formatter with the desired pattern

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the LocalDateTime object to a string
        this.OrderDate = now.format(formatter);
    }

    /**
     * @return the total price of the order
     */
    public double getOrderPrice(){
        return OrderPrice;
    }

    /**
     * @param cart the {@code ShoppingCart} cart associated with this order
     */
    public void setCart(ShoppingCart cart) {
        Cart = cart;
    }

    /**
     * @param price the total price to set for the order
     */
    public void setOrderPrice(double price) {
        OrderPrice = price;
    }

}




