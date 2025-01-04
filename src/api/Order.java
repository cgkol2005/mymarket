package api;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements Serializable {
    private ShoppingCart Cart;
    private double OrderPrice;
    private int NumberOfProductsBought;
    private String OrderDate;
    private Data data;
    public Order(ShoppingCart Cart, double OrderPrice, int NumberOfProductsBought, Data data){
        this.Cart = Cart;
        this.OrderPrice = OrderPrice;
        this.NumberOfProductsBought = NumberOfProductsBought;

        LocalDateTime now = LocalDateTime.now();
        // Define a formatter with the desired pattern

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the LocalDateTime object to a string
        this.OrderDate = now.format(formatter);

        data.updateProductsStockInShop(Cart.getShoppingCartMap());
    }

    public String getOrderDate(){
        return OrderDate;
    }

    public int getNumberOfProductsBought(){
        return NumberOfProductsBought;
    }

    public double getOrderPrice(){
        return OrderPrice;
    }

    public void setCart(ShoppingCart cart) {
        Cart = cart;
    }
}




