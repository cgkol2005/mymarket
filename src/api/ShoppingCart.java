package api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private HashMap<Product, Quantity> ShoppingCartMap;
    private int productsCount; //counts the total products in the Cart
    private double CartTotalPrice;

    public ShoppingCart() {
        ShoppingCartMap = new HashMap<>();
        productsCount = 0;
        CartTotalPrice = 0;
    }

    public void addProduct(PackagedProduct product,int stock){
        if (product.getStock() < stock){
            System.out.println("! Product is out of stock. :( !");
        }else if (ShoppingCartMap.containsKey(product)){
            UpdateProductQuantityInCart(product, stock);
        }else{
            Quantity quantity = new Pieces(stock);
            ShoppingCartMap.put(product,quantity);
            productsCount++;
            System.out.println("Product Added!");
        }
    }

    public void addProduct(WeightedProduct product,double stock){
        if (product.getStock() < stock){
            System.out.println("! Product is out of stock. :( !");
        }else if (ShoppingCartMap.containsKey(product)){
            UpdateProductQuantityInCart(product, stock);
        }else{
            Quantity quantity = new Kgs(stock);
            ShoppingCartMap.put(product,quantity);
            productsCount++;
            System.out.println("Product Added!");
        }
    }


    /**
     * Method to update the quantity of a product that already exists in the Cart.
     *
     */
    public void UpdateProductQuantityInCart(PackagedProduct product, int moreStock){
        if (!ShoppingCartMap.containsKey(product)){
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
        } else if (product.getStock() < moreStock) {
            System.out.println("Quantity not available!");
        }else{
            ShoppingCartMap.get(product).addQuantity(moreStock);
            System.out.println("Η ποσότητα ενημερώθηκε.");
        }
    }
    public void UpdateProductQuantityInCart(WeightedProduct product, double new_stock){
        if (!ShoppingCartMap.containsKey(product)){
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
        } else if (product.getStock() < new_stock) {
            System.out.println("Quantity not available!");
        }else{
            ShoppingCartMap.get(product).addQuantity(new_stock);
            System.out.println("Η ποσότητα ενημερώθηκε.");
        }
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
        for (Map.Entry<Product,Quantity> entry : ShoppingCartMap.entrySet()){
            if (entry.getValue() instanceof Kgs) {
                String str1 = String.valueOf(entry.getKey().getPrice());
                BigDecimal one = new BigDecimal(str1);
                String str2 = String.valueOf(((Kgs) entry.getValue()).getQuantity());
                BigDecimal two = new BigDecimal(str2);
                total_price = total_price.add(one.multiply(two));
            } else if (entry.getValue() instanceof Pieces) {
                String str1 = String.valueOf(entry.getKey().getPrice());
                BigDecimal one = new BigDecimal(str1);
                String str2 = String.valueOf(((Pieces) entry.getValue()).getQuantity());
                BigDecimal two = new BigDecimal(str2);
                total_price = total_price.add(one.multiply(two));
            }
        }
        CartTotalPrice = total_price.doubleValue();
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
            for (HashMap.Entry<Product,Quantity> entry : ShoppingCartMap.entrySet()) {
                Product product=entry.getKey();
                if (entry.getValue() instanceof Pieces){
                    int stock=entry.getValue().getPieces();
                    System.out.printf("Προϊόν: %s, Ποσότητα: %d, Συνολικό κόστος: %.2f%n",product.getTitle(), stock, CalculateTotalPriceOfAProductInCart(product));
                } else if (entry.getValue() instanceof Kgs) {
                    double stock = entry.getValue().getKgs();
                    System.out.printf("Προϊόν: %s, Ποσότητα: %.2f, Συνολικό κόστος: %.2f%n",product.getTitle(), stock, CalculateTotalPriceOfAProductInCart(product));
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

    public double getProductQuantityInShoppingCart(String productName){
        for (Product product : ShoppingCartMap.keySet()){
            if (product.getTitle().equals(productName)){
                if (product instanceof WeightedProduct){
                return ShoppingCartMap.get(product).getKgs();
                }
                else{
                    return ShoppingCartMap.get(product).getPieces();
                }
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
    private double CalculateTotalPriceOfAProductInCart(Product product){
        BigDecimal Total_Price;
        if (product instanceof WeightedProduct){
            BigDecimal dec1 = new BigDecimal(String.valueOf(ShoppingCartMap.get(product).getKgs()));
            BigDecimal dec2 = new BigDecimal(String.valueOf(product.getPrice()));
            Total_Price = dec1.multiply(dec2);
        }else{
            BigDecimal dec1 = new BigDecimal(String.valueOf(ShoppingCartMap.get(product).getPieces()));
            BigDecimal dec2 = new BigDecimal(String.valueOf(product.getPrice()));
            Total_Price = dec1.multiply(dec2);
        }
        return Total_Price.doubleValue();

    }


}
