package api;

/**
 * THIS CLASS CAN BE DELETED
 */
public class Product
{
    private String title;
    private String description;
    private String category;
    private String scategory;
    private double price;
    private int stock; // kilo or piece

    /** creates a product
     *
     * @param title
     * @param description
     * @param category
     * @param scategory
     * @param price
     * @param stock
     */
    public Product (String title,String description,String category,String scategory,double price,int stock)
    {
        if (price>0 && stock>=0) {

            this.title = title;
            this.description = description;
            this.category = category;
            this.scategory = scategory;
            this.price = price;
            this.stock = stock;
        }
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public String getCategory()
    {
        return category;
    }
    public String getScategory()
    {
        return scategory;
    }
    public double getPrice()
    {
        return price;
    }
    public int getStock()
    {
        return stock;
    }
    public void setStock(int stock)
    {
        this.stock=stock;
    }
    public boolean reduceStock(int quantity)
    {
        if (quantity>stock)
        {
            return false;
        }
        else
        {
            stock=stock-quantity;
            return true;
        }
    }
    // print
    @Override
    public String toString()
    {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + scategory + '\'' +
                ", price=" + price +
                ", availableQuantity=" + stock+
                '}';
    }



}
