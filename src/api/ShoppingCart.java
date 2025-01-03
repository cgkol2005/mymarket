package api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private HashMap<Product, Integer> ShoppingCartMap;
    private int productsCount; //counts the total products in the Cart
    private double CartTotalPrice;

    public ShoppingCart() {
        ShoppingCartMap = new HashMap<>();
        productsCount = 0;
        CartTotalPrice = 0;
    }

    //the stock could be either in pieces or in grams.
    public void addProduct(Product product,int stock){
        Integer stock2 = stock;
        if (product instanceof WeightedProduct){
            if (((WeightedProduct) product).getStock() * 1000 < stock){
            System.out.println("! Product is out of stock. :( !");
            }else if (ShoppingCartMap.containsKey(product)){
            UpdateProductQuantityInCart(product, stock2);
            }else{
            ShoppingCartMap.put(product,stock2);
            productsCount++;
            System.out.println("Product Added!");
            }
        }else{
            if (((PackagedProduct) product).getStock() < stock){
                System.out.println("! Product is out of stock. :( !");
            }else if (ShoppingCartMap.containsKey(product)){
                UpdateProductQuantityInCart(product, stock2);
            }else{
                ShoppingCartMap.put(product,stock2);
                productsCount++;
                System.out.println("Product Added!");
            }
        }
    }

    /**
     * Method to update the quantity of a product that already exists in the Cart.
     *
     */
    public void UpdateProductQuantityInCart(Product product, int moreStock){
        Integer quantityInCart = ShoppingCartMap.get(product);
        Integer moreStock2 = moreStock;
        if (product instanceof WeightedProduct) {
            Integer productStock = (int) ((WeightedProduct) product).getStock()*1000;
            if (!ShoppingCartMap.containsKey(product)) {
                System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
            } else if (productStock - quantityInCart < moreStock) {
                System.out.println("Quantity not available!");
            } else {
                ShoppingCartMap.put(product,quantityInCart+moreStock2);
                System.out.println("Η ποσότητα ενημερώθηκε.");
            }
        } else if (product instanceof PackagedProduct) {
            Integer productStock = ((PackagedProduct) product).getStock();
            if (!ShoppingCartMap.containsKey(product)) {
                System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
            } else if (productStock - quantityInCart < moreStock) {
                System.out.println("Quantity not available!");
            } else {
                ShoppingCartMap.put(product,quantityInCart+moreStock2);
                System.out.println("Η ποσότητα ενημερώθηκε.");
            }
        }
    }

    //this is for overall replacement of the stock in cart.
    public void ReplaceProductQuantityInCart(Product product, int new_stock){
            ShoppingCartMap.put(product,new_stock);
            System.out.println("Η ποσότητα ενημερώθηκε.");
    }

    public void removeProduct(Product product) {
        if (!ShoppingCartMap.containsKey(product))
        {
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
        }else{
            ShoppingCartMap.remove(product);
            System.out.println("Το προϊόν αφαιρέθηκε από το καλάθι.");
            productsCount--;
        }
    }

    public void CalculateAndUpdateTotalPrice(){
        BigDecimal total_price = BigDecimal.ZERO;
        for (Map.Entry<Product,Integer> entry : ShoppingCartMap.entrySet()){
            if (entry.getKey() instanceof WeightedProduct) {
                BigDecimal one = BigDecimal.valueOf(entry.getKey().getPrice());
                BigDecimal two = new BigDecimal(entry.getValue());
                total_price = total_price.add(one.multiply(two));
            } else if (entry.getKey() instanceof PackagedProduct) {
                BigDecimal one = BigDecimal.valueOf(entry.getKey().getPrice());
                BigDecimal two = new BigDecimal(entry.getValue());
                total_price = total_price.add(one.multiply(two));
            }
        }
        CartTotalPrice = total_price.doubleValue();
    }

    public List<Product> createArrayListFromShoppingCart(){
        return new ArrayList<>(ShoppingCartMap.keySet());
    }
    public Product getProductBasedOnListIndex(int j){
        return createArrayListFromShoppingCart().get(j);
    }

    public double getTotalPrice() {
        CalculateAndUpdateTotalPrice();
        return CartTotalPrice;
    }

    public int getProducts_count(){
        return productsCount;
    }

    public void printShoppingCart() {
        if (ShoppingCartMap.isEmpty())
        {
            System.out.println("Το καλάθι είναι άδειο.");
        }else{
            System.out.println("Περιεχόμενα καλαθιού:");
            for (HashMap.Entry<Product,Integer> entry : ShoppingCartMap.entrySet()) {
                Product product=entry.getKey();
                if (entry.getKey() instanceof PackagedProduct){
                    int stock=entry.getValue();
                    System.out.printf("Προϊόν: %s, Ποσότητα: %d, Συνολικό κόστος: %.2f%n",product.getTitle(), stock, CalculateTotalPriceOfAProductInCart(product));
                } else if (entry.getKey() instanceof WeightedProduct) {
                    double stock = entry.getValue(); //this is in grams
                    BigDecimal stock2 = BigDecimal.valueOf(stock);
                    stock2 = stock2.divide(BigDecimal.valueOf(1000)); //this is in Kgs now
                    System.out.printf("Προϊόν: %s, Ποσότητα: %.2f, Συνολικό κόστος: %.2f%n",product.getTitle(), stock2.doubleValue(), CalculateTotalPriceOfAProductInCart(product));
                }
            }
            CalculateAndUpdateTotalPrice();
            System.out.printf("Συνολικό κόστος καλαθιού: %.2f%n", CartTotalPrice);
        }
    }

    public Product getProductInShoppingCart(String productTitle){
        for (Product product : ShoppingCartMap.keySet()){
            if (product.getTitle().equals(productTitle)){
                return product;
            }
        }
        return null;
    }

    public int getProductQuantityInShoppingCart(String productName){
        for (Product product : ShoppingCartMap.keySet()){
            if (product.getTitle().equals(productName)){
                return ShoppingCartMap.get(product);
            }
        }
        return 0;
    }

    /**
     * Calculates the total price of the product in a ShoppingCart.
     * Total price = Amount of product in Cart * Price of Product
     *
     * @author Charitwn Mavridis
     * @return The total price of the product amount in the Cart.
     */
    public double CalculateTotalPriceOfAProductInCart(Product product){
        BigDecimal Total_Price;
        if (product instanceof WeightedProduct){
            BigDecimal dec1 = BigDecimal.valueOf(ShoppingCartMap.get(product));
            BigDecimal dec2 = BigDecimal.valueOf(product.getPrice());
            Total_Price = dec1.multiply(dec2);
            Total_Price = Total_Price.divide(BigDecimal.valueOf(1000));
        }else{
            BigDecimal dec1 = new BigDecimal(String.valueOf(ShoppingCartMap.get(product)));
            BigDecimal dec2 = new BigDecimal(String.valueOf(product.getPrice()));
            Total_Price = dec1.multiply(dec2);
        }
        return Total_Price.doubleValue();

    }


}
