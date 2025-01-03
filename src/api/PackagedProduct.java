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
}
