package api;

import java.io.Serializable;


public class Product implements Serializable{
    private String title;
    private String description;
    private String category;
    private String sub_category;
    private double price;
    private int times_ordered; //Variable to track how many times the product has been ordered. (For admins statistics)

    public Product (String title,String description,String category,String sub_category,double price)
    {
            this.title = title;
            this.description = description;
            this.category = category;
            this.sub_category = sub_category;
            this.price = price;
    }

    public void ProductOrdered() {
        times_ordered++;
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
    public int getTimes_ordered() {
        return times_ordered;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", sub_category='" + sub_category + '\'' +
                ", price=" + price +
                '}';
    }

}
