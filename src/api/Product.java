package api;
import java.io.Serializable;
public class Product implements Serializable{
    private String title;
    private String description;
    private String category;
    private String sub_category;
    private double price;
    private int times_ordered; //Variable to track how many times the product has been ordered. (For admins statistics)
    private boolean isAvailable;

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

    public void incrementTimesOrdered() {
        this.times_ordered++;
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
    }public double getPrice()
    {
        return price;
    }
    public int getTimesOrdered() {
        return times_ordered;
    }

    public boolean getAvailability(){
        return isAvailable;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setDescription(String description)
    {
        this.description=description;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }
    public void setSub_category(String sub_category)
    {
        this.sub_category=sub_category;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}