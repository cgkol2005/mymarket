
package api;

import java.io.Serializable;
import java.util.List;
public class Admin extends User implements Serializable {
    public Admin(){
        super();
    }
    public Admin(String Username, String Password){
        super(Username, Password);
    }
    /**
     * Register a new product into the system's product list.
     *
     * @param name        Name of the product
     * @param description Description of the product
     * @param category    Category of the product
     * @param subcategory Subcategory of the product
     * @param price       Price of the product (as String to allow for validation)
     * @param stock       Stock quantity of the product (as String to allow for validation)
     * @param data        The Data instance managing the system's data
     */

    public void RegisterProduct(String name,String description,String category,String subcategory,double price,Object stock,Data data) {
        Product newproduct;

        if (stock instanceof Integer) {
            int finalStock = (Integer) stock;
            newproduct=new PackagedProduct(name, description, category, subcategory, price, finalStock);
        } else if (stock instanceof Double) {
            double finalStock = (Double) stock;
            newproduct=new WeightedProduct(name, description, category, subcategory, price, finalStock);
        } else {
            throw new IllegalArgumentException("Stock must be of type Integer or Double");
        }
        List<Product> productList = data.getProductsList();
        for (Product product : productList) {
            if (product.getTitle().equalsIgnoreCase(name))
            {
                System.out.println("Product with the same name already exists.");
                return;
            }
        }
        productList.add(newproduct);
        data.OverWriteProductsList(productList);
        data.DataSave();
        System.out.println("Product successfully registered.");
    }
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
