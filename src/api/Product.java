package api;

/**
 * THIS CLASS CAN BE DELETED
 */
public class Product
{
    private String title;
    private String description;
    private String category;
    private String sub_category;
    private double price;
    private int stock; // kilo or piece

    /** creates a product
     *
     * @param title
     * @param description
     * @param category
     * @param sub_category
     * @param price
     * @param stock
     */
    public Product (String title,String description,String category,String sub_category,double price,int stock)
    {
            this.title = title;
            this.description = description;
            this.category = category;
            this.sub_category = sub_category;
            this.price = price;
            this.stock = stock;
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
    public String getSub_category()
    {
        return sub_category;
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
                ", subCategory='" + sub_category + '\'' +
                ", price=" + price +
                ", availableQuantity=" + stock+
                '}';
    }



}
