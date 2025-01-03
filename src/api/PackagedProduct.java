package api;
/**
 * The PackagedProduct class represents a type of product that has a stock count.
 * It extends the Product class and adds  the stock
 */
public class PackagedProduct extends Product{
    int stock;

    /**
     * Constructs a new PackagedProduct object with the specified details and stock count.
     * @param title       the title of the product
     * @param description the description of the product
     * @param category    the category of the product
     * @param sub_category the sub_category of the product
     * @param price       the price of the product
     * @param stock       the stock of the product
     */
    public PackagedProduct(String title, String description, String category, String sub_category, double price, int stock) {
        super(title, description, category, sub_category, price,stock);
        this.stock = stock;
    }
    /**
     * Returns the current stock level of the product.
     *
     * @return the stock level
     */
    public int getStock(){
        return stock;
    }

    /**
     * Sets the stock level of the product.
     * The stock value must be non-negative.
     * @param stock the new stock level
     */
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
        }
        this.stock = stock;
    }
}
