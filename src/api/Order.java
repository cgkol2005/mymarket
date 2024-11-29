package api;

import java.io.Serializable;

public class Order implements Serializable {
    private ShoppingCart Cart;
    private double OrderPrice;
    private int NumberOfProductsBought;
    private String OrderDate;

    public void setCart(ShoppingCart cart) {
        Cart = cart;
    }

    public void setOrderPrice(double price) {
        OrderPrice = price;
    }
}
