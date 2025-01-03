package api;

import java.io.Serializable;
import java.util.List;
public class Admin extends User implements Serializable {
    private Data data;

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
    public void EditProducts(String productName, String newTitle, String newDescription, String newCategory, String newSubcategory, double newPrice, Object newStock, Data data) {
        List<Product> productList = data.getProductsList();
        boolean productFound = false;
        for (Product product : productList) {
            if (product.getTitle().equalsIgnoreCase(productName)) {
                productFound = true;
                product.setTitle(newTitle);
                product.setDescription(newDescription);
                product.setCategory(newCategory);
                product.setSub_category(newSubcategory);
                product.setPrice(newPrice);
                if (product instanceof PackagedProduct) {
                    ((PackagedProduct) product).setStock((Integer) newStock);
                    int i = (int) newStock;
                    if (i == 0){
                        product.setAvailable(false);
                    }
                } else if (product instanceof WeightedProduct) {
                    ((WeightedProduct) product).setStock((Double) newStock);
                    int i = (int) newStock;
                    if (i == 0){
                        product.setAvailable(false);
                    }
                } else {
                    System.out.println("Invalid stock type for the product type.");
                    return;
                }
                data.OverWriteProductsList(productList);
                data.DataSave();
                System.out.println("Product successfully updated.");
                break;
            }
        }

        if (!productFound) {
            System.out.println("Product name was not found.");
        }
    }
    public String getUnavailableProducts() {
        List<Product> productList=data.getProductsList();
        StringBuilder unavailable=new StringBuilder();

        for (Product product : productList) {
            boolean isUnavailable= (product instanceof PackagedProduct && ((PackagedProduct) product).getStock() <= 0) ||
                    (product instanceof WeightedProduct && ((WeightedProduct) product).getStock() <= 0);

            if (isUnavailable) {
                unavailable.append(product.getTitle()).append("\n");
            }
        }
        return unavailable.length() > 0 ? unavailable.toString()  : "Δεν υπάρχουν μη διαθέσιμα προϊόντα.";
    }


}

