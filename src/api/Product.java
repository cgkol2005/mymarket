package api;
import java.io.Serializable;
/**
 * The Product class represents a product in a system.
 * It contains information about the product
 */
public class Product implements Serializable{
    private String title;
    private String description;
    private String category;
    private String sub_category;
    private double price;
    private int times_ordered; //Variable to track how many times the product has been ordered. (For admins statistics)
    private boolean isAvailable;

    /**
     * Constructs a new {@code Product}
     * @param title        the title of the product
     * @param description  the description of the product
     * @param category     the category of the product
     * @param sub_category the sub-category of the product
     * @param price        the price of the product
     * @param stock        the stock of the product
     */
    public Product (String title,String description,String category,String sub_category,double price,double stock) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.sub_category = sub_category;
        this.price = price;
        if(stock > 0){
            isAvailable = true;
        }else{
            isAvailable = false;
        }
    }
    /**
     * Overloaded constructor for {@code Product}.
     * Accepts an integer for the stock
     * @param title        the title of the product
     * @param description  the description of the product
     * @param category     the category of the product
     * @param sub_category the sub_category of the product
     * @param price        the price of the product
     * @param stock        the stock of the product integer
     */
    public Product (String title,String description,String category,String sub_category,double price,int stock) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.sub_category = sub_category;
        this.price = price;
        if (stock > 0){
            isAvailable = true;
        }else{
            isAvailable = false;
        }
    }

    /**
     * Increments the times the product has been ordered by 1.
     */
    public void incrementTimesOrdered() {
        this.times_ordered++;
    }

    /**
     * @return the product title
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * @return the product description
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * @return the product category
     */
    public String getCategory()
    {
        return category;
    }
    /**
     * @return the product sub-category
     */
    public String getSub_category()
    {
        return sub_category;
    }
    /**
     * @return the product price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @return the number of times the product has been ordered
     */
    public int getTimesOrdered() {
        return times_ordered;
    }

    /**
     * @return {@code true} if the product is available else {@code false}
     */
    public boolean getAvailability(){
        return isAvailable;
    }

    /**
     * @param newTitle the new title of the product
     */
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    /**
     * @param description the new description of the product
     */
    public void setDescription(String description)
    {
        this.description=description;
    }
    /**
     * @param category the new category of the product
     */
    public void setCategory(String category)
    {
        this.category=category;
    }
    /**
     * @param sub_category the new sub-category of the product
     */
    public void setSub_category(String sub_category)
    {
        this.sub_category=sub_category;
    }
    /**
     * @param price the new price of the product
     */
    public void setPrice(double price)
    {
        this.price=price;
    }
    /**
     * @param available {@code true} to mark the product as available else  {@code false}
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}