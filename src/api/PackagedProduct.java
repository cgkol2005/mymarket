package api;

public class PackagedProduct extends Product{
    int stock;

    public PackagedProduct(String title, String description, String category, String sub_category, double price, int stock) {
        super(title, description, category, sub_category, price,stock);
        this.stock = stock;
    }
    public int getStock(){
        return stock;
    }
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Το απόθεμα δεν μπορεί να είναι αρνητικό.");
        }
        this.stock = stock;
    }
    public void reduceStock(int stock){
        this.stock -= stock;
        System.out.println("Removed "+stock+" stock for the product "+this.getTitle()+"! Now the remaining stock is: "+this.stock+".");
        if (this.stock <= 0){
            this.setAvailable(false);
        }
    }
}
