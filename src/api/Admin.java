package api;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(){}
    public Admin(String Username, String Password){
        super(Username, Password);
    }

    public void RegisterProduct(){}
    public void EditProducts(){}
    public void Statistics(){}
}

/*
package api;
import java.util.*;
public class Diaxeirhsths {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(String title, String description, String category, String subCategory, double price,int stock) {
        if (products.containsKey(title))
        {
            System.out.println("Το προϊόν ήδη υπάρχει.");
        }
        else
        {
            Product product = new Product(title, description, category, subCategory, price, stock);
            products.put(title, product);

        }
    }
    public void editproduct(String title, String description, String category, String subCategory, double price,int stock)
    {
        Product product=products.get(title);
        if (product!=null)
        {
            product.setDescription(description);
            product.setCategory(category);
            product.setSub_category(subCategory);
            product.setPrice(price);
            product.setStock(stock);
            System.out.println("Το προϊόν ενημερωθηκε");
        } else
        {
            System.out.println("Το προϊόν δεν βρέθηκε.");
        }

    }
    public void statistics()
    {
        boolean diathesima=true;
        for (Product product: products.values())
        {
            if (product.getStock()==0)
            {
                diathesima=false;
                System.out.println(product);
            }
        }
        if (diathesima)
        {
            System.out.println("Όλα τα προιόντα είναι διαθέσιμα");
        }
    }

}
*/
