package api;
import java.util.HashMap;
import java.util.Map;
public class ShoppingCart
{
    private HashMap<Product, Integer> ShoppingCart;
    private int products_count;
    private double total_price;
    public ShoppingCart()
    {
        ShoppingCart= new HashMap<>();
        products_count = 0;
        total_price = 0;
    }
    public void addProduct(Product product,int stock) // προσθήκη ενός προιόντος
    {
        if (product.getStock()<stock)
        {
            System.out.println("Μη διαθέσιμη ποσότητα για το προϊόν:");

        }
        int products_count=ShoppingCart.getOrDefault(product, 0);
        ShoppingCart.put(product,products_count + stock);
        total_price += product.getPrice() * stock;
        System.out.println("Το προϊόν προστέθηκε/ενημερώθηκε στο καλάθι.");
    }
    public void removeProduct(Product product) //αφαίρεση ενός προιόντος
    {
        if (!ShoppingCart.containsKey(product))
        {
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
        }
        int stock=ShoppingCart.get(product);
        ShoppingCart.remove(product);
        total_price -=product.getPrice() *stock;
        System.out.println("Το προϊόν αφαιρέθηκε από το καλάθι.");
    }
    public void updatestock(Product product,int new_stock) // ενημέρωση προιόντος
    {
        if (!ShoppingCart.containsKey(product))
        {
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
        }
        if (product.getStock() <new_stock)
        {
            System.out.println("Η νέα ποσότητα δεν είναι διαθέσιμη για το προϊόν:");
        }
        int oldstock=ShoppingCart.get(product);
        ShoppingCart.put(product, new_stock);
        total_price += product.getPrice() * (new_stock- oldstock);
        System.out.println("Η ποσότητα ενημερώθηκε.");
    }
    public double getTotalPrice()
    {

        return total_price;
    }
    public void printShoppingCart()
    {
        if (ShoppingCart.isEmpty())
        {
            System.out.println("Το καλάθι είναι άδειο.");
        }
        System.out.println("Περιεχόμενα καλαθιού:");
        for (HashMap.Entry<Product,Integer> entry :ShoppingCart.entrySet())
        {
            Product product=entry.getKey();
            int stock=entry.getValue();
            System.out.printf("Προϊόν: %s, Ποσότητα: %d, Συνολικό κόστος: %.2f%n",product.getTitle(), stock, product.getPrice() * stock);
        }
        System.out.printf("Συνολικό κόστος καλαθιού: %.2f%n", total_price);
    }
}
