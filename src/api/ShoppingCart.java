package api;

import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    private HashMap<Product, Integer> ShoppingCart;
    private int products_count;
    private double total_price;


    public double getTotal_price() {
        return total_price;
    }


}
