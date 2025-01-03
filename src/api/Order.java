package api;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import  api.Data;

public class Order implements Serializable {
    private ShoppingCart Cart;
    private double OrderPrice;
    private int NumberOfProductsBought;
    private String OrderDate;

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

    public double getOrderPrice(){
        return OrderPrice;
    }

    public void setCart(ShoppingCart cart) {
        Cart = cart;
    }

    public void setOrderPrice(double price) {
        OrderPrice = price;
    }

}




