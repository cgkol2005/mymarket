package api;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class ShoppingCart implements Serializable
{
    private HashMap<Product, Integer> ShoppingCart;
    private int Products_count; //counts the total products in the Cart
    private double total_price;
    public ShoppingCart()
    {
        ShoppingCart= new HashMap<>();
        Products_count = 0;
        total_price = 0;
    }
    public void addProduct(Product product,int stock) // προσθήκη ενός προιόντος
    {
        if (product.getStock()<stock)
        {
            System.out.println("Μη διαθέσιμη ποσότητα για το προϊόν:");
        }else{
            int products_count=ShoppingCart.getOrDefault(product, 0); //counts the amount of a product in the cart.
            ShoppingCart.put(product,products_count + stock);
            total_price += product.getPrice() * stock;
            System.out.println("Το προϊόν προστέθηκε/ενημερώθηκε στο καλάθι.");
            Products_count++;
        }

    }
    //BUG, Πως θα εισαγουμε στο καλάθι προϊόντα με κιλα; (δεκαδικο μερος)
    /*
    public void addProduct(Product product,double stock) // προσθήκη ενός προιόντος
    {
        if (product.getStock()<stock)
        {
            System.out.println("Μη διαθέσιμη ποσότητα για το προϊόν:");
        }else{
            int products_count=ShoppingCart.getOrDefault(product, 0);
            ShoppingCart.put(product,products_count + stock);
            total_price += product.getPrice() * stock;
            System.out.println("Το προϊόν προστέθηκε/ενημερώθηκε στο καλάθι.");
            Products_count++;
        }

    }

     */
    public void removeProduct(Product product) //αφαίρεση ενός προιόντος
    {
        if (!ShoppingCart.containsKey(product))
        {
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");
        }else{
            int stock=ShoppingCart.get(product);
            ShoppingCart.remove(product);
            total_price -=product.getPrice() *stock;
            System.out.println("Το προϊόν αφαιρέθηκε από το καλάθι.");
            Products_count--;
        }

    }
    public void updatestock(Product product,int new_stock) // ενημέρωση προιόντος
    //fix bugs for decimal values (some products are given in kgs)

    {
        if (!ShoppingCart.containsKey(product))
        {
            System.out.println("Το προϊόν δεν υπάρχει στο καλάθι.");

        } else if (product.getStock() <new_stock) {
            System.out.println("Η νέα ποσότητα δεν είναι διαθέσιμη για το προϊόν:");
        }else{
            int oldstock=ShoppingCart.get(product);
            ShoppingCart.put(product, new_stock);
            total_price += product.getPrice() * (new_stock- oldstock);
            System.out.println("Η ποσότητα ενημερώθηκε.");
        }

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
        }else{
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
    public void CompleteOrder(){
        Order order = new Order(this, total_price, Products_count);
        //Υλοποιηση: Οταν ολοκληρώνεται μια παραγγελία, πρεπει το stock των προϊόντων να μειώνεται ανάλογα.
    }
}
