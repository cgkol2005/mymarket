package api;

import java.math.BigDecimal;

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

    public void reduceStock(int stock){
        BigDecimal stockB = BigDecimal.valueOf(stock);
        stockB = stockB.divide(BigDecimal.valueOf(1000));
        BigDecimal productStock = BigDecimal.valueOf(this.stock);
        productStock = productStock.subtract(stockB);
        this.stock = productStock.doubleValue();
        System.out.println("Removed "+stockB+" stock for the product "+this.getTitle()+"! Now the remaining quantity is: "+this.stock);
        if (this.stock <= 0){
            this.setAvailable(false);
        }
    }
}