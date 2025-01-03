package api;
/**
 * The WeightedProduct class represents a product sold by weight
 * It extends the Product class
 */
public class WeightedProduct extends Product{
    double stock;
    /**
     * Constructs a {@code WeightedProduct}
     * @param title        the title of the product
     * @param description  the description of the product
     * @param category     the category of the product
     * @param sub_category the sub_category of the product
     * @param price        the price per weight
     * @param stock        the stock of the product in weight
     */

    public WeightedProduct(String title, String description, String category, String sub_category, double price, double stock) {
        super(title, description, category, sub_category, price,stock);
        this.stock = stock;
    }
    /**
     * @return the stock of the product
     */
    public double getStock(){
        return stock;
    }
    /**
     * Sets the stock of the product in weight units.
     * @param stock the new stock value
     * @throws IllegalArgumentException if the stock is negative
     */
    public void setStock(double stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
        }
        this.stock = stock;
    }
}