package api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable {
    private HashMap<Product, Integer> ShoppingCart; //Integer: Represents the quantity of the Product being ordered.
    private int products_count; //how many products are in the shopping cart?
    private double total_price; //what's the total price of all the products in the cart(each product multiplied by its quantity of course)

    public ShoppingCart(){
        ShoppingCart = new HashMap<>();
        products_count = 0;
        total_price = 0;
    }
    public boolean addProduct(Product product,Integer quantity){
        ShoppingCart.put(product, quantity);
        return true;
    }
    public void calculateTotalPrice(){
        int total_price = 0;
        for (Map.Entry<Product, Integer> entry: ShoppingCart.entrySet()) {
            total_price+= entry.getKey().getPrice() * entry.getValue();
        }
        this.total_price += total_price;
        System.out.println("Total price of the cart: "+this.total_price);
    }
    public double getTotal_price() {
        return total_price;
    }


}
