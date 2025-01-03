package api;

public class WeightedProduct extends Product{
    double stock;

    public WeightedProduct(String title, String description, String category, String sub_category, double price, double stock) {
        super(title, description, category, sub_category, price,stock);
        this.stock = stock;
    }
    public double getStock(){
        return stock;
    }
    public void setStock(double stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
        }
        this.stock = stock;
    }
}