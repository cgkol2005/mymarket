package api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private HashMap<Product, Integer> shoppingCartMap;
    private int productsCount; //counts the total products in the Cart
    private double CartTotalPrice;

    public ShoppingCart() {
        shoppingCartMap = new HashMap<>();
        productsCount = 0;
        CartTotalPrice = 0;
    }

    //the stock could be either in pieces or in grams.
    public void addProduct(Product product,int stock){
        Integer stock2 = stock;
        boolean found = false;
        for (Product product2 : shoppingCartMap.keySet()){
            if (product2.getTitle().equals(product.getTitle())){
                UpdateProductQuantityInCart(product2, stock2);
                return;
            }
        }
            shoppingCartMap.put(product,stock2);
            productsCount++;
            System.out.println("Product Added!");

    }

    /**
     * Method to update the quantity of a product that already exists in the Cart.
     *
     */
    public void UpdateProductQuantityInCart(Product product, int moreStock){
        Integer quantityInCart = shoppingCartMap.get(product);
        Integer moreStock2 = moreStock;
        if (product instanceof WeightedProduct) {
            Integer productStock = (int) ((WeightedProduct) product).getStock()*1000;
            shoppingCartMap.put(product,quantityInCart+moreStock2);
            System.out.println("Η ποσότητα ενημερώθηκε.");
        } else if (product instanceof PackagedProduct) {
            Integer productStock = ((PackagedProduct) product).getStock();
            shoppingCartMap.put(product,quantityInCart+moreStock2);
            System.out.println("Η ποσότητα ενημερώθηκε.");
        }
    }

    //this is for overall replacement of the stock in the cart.
    public void ReplaceProductQuantityInCart(Product product, int new_stock){
            shoppingCartMap.put(product,new_stock);
            System.out.println("Η ποσότητα ενημερώθηκε.");
    }

    public void removeProduct(String productName) {
        for (Product product : shoppingCartMap.keySet()){
            if (product.getTitle().equals(productName)){
                shoppingCartMap.remove(product);
                productsCount--;
                break;
            }
        }
    }

    public void CalculateAndUpdateTotalPrice(){
        BigDecimal total_price = BigDecimal.ZERO;
        for (Map.Entry<Product,Integer> entry : shoppingCartMap.entrySet()){
            if (entry.getKey() instanceof WeightedProduct) {
                BigDecimal one = BigDecimal.valueOf(entry.getKey().getPrice());
                BigDecimal two = new BigDecimal(entry.getValue());
                two = two.divide(BigDecimal.valueOf(1000));
                total_price = total_price.add(one.multiply(two));
            } else if (entry.getKey() instanceof PackagedProduct) {
                BigDecimal one = BigDecimal.valueOf(entry.getKey().getPrice());
                BigDecimal two = new BigDecimal(entry.getValue());
                total_price = total_price.add(one.multiply(two));
            }
        }
        CartTotalPrice = total_price.doubleValue();
    }

    public Integer getQuantityInShoppingCart(String productName){
        for (Map.Entry<Product, Integer> product : shoppingCartMap.entrySet()){
            if (product.getKey().getTitle().equals(productName)){
                return product.getValue();
            }
        }
        return null;
    }

    public void removeQuantityFromShoppingCart(Product product, int quantity){
        for (Map.Entry<Product, Integer> product2 : shoppingCartMap.entrySet()){
            if (product2.getKey().getTitle().equals(product.getTitle())){
                int oldQuantity = product2.getValue();
                Integer newQuantity = oldQuantity - quantity;
                product2.setValue(newQuantity);
            }
        }
    }
    public List<Product> createArrayListFromShoppingCart(){
        return new ArrayList<>(shoppingCartMap.keySet());
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
        if (shoppingCartMap.isEmpty())
        {
            System.out.println("Το καλάθι είναι άδειο.");
        }else{
            System.out.println("Περιεχόμενα καλαθιού:");
            for (HashMap.Entry<Product,Integer> entry : shoppingCartMap.entrySet()) {
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
        for (Product product : shoppingCartMap.keySet()){
            if (product.getTitle().equals(productTitle)){
                return product;
            }
        }
        return null;
    }

    public int getProductQuantityInShoppingCart(String productName){
        for (Map.Entry<Product, Integer> product : shoppingCartMap.entrySet()){
            if (product.getKey().getTitle().equals(productName)){
                return product.getValue();
            }
        }
        return 0;
    }

    public HashMap<Product, Integer> getShoppingCartMap(){
        return shoppingCartMap;
    }

    public void clear(){
        shoppingCartMap.clear();
        productsCount = 0;
        this.CartTotalPrice = 0;
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
            BigDecimal dec1 = BigDecimal.valueOf(shoppingCartMap.get(product));
            BigDecimal dec2 = BigDecimal.valueOf(product.getPrice());
            Total_Price = dec1.multiply(dec2);
            Total_Price = Total_Price.divide(BigDecimal.valueOf(1000));
        }else{
            BigDecimal dec1 = new BigDecimal(String.valueOf(shoppingCartMap.get(product)));
            BigDecimal dec2 = new BigDecimal(String.valueOf(product.getPrice()));
            Total_Price = dec1.multiply(dec2);
        }
        return Total_Price.doubleValue();

    }


}
