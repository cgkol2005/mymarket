package api;

import java.io.Serializable;


public class Product implements Serializable{
    private String title;
    private String description;
    private String category;
    private String sub_category;
    private double price;
    private int stock; // kilo or piece
    private int times_ordered; //Variable to track how many times the product has been ordered. (For admins statistics)

    public Product (String title,String description,String category,String sub_category,double price,int stock)
    {
            this.title = title;
            this.description = description;
            this.category = category;
            this.sub_category = sub_category;
            this.price = price;
            this.stock = stock;
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
    public int getStock()
    {
        return stock;
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
    public void setStock(int stock)
    {
        this.stock=stock;
    }


    public void ProductOrdered() {
        times_ordered++;
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
                ", stock=" + stock+
                '}';
    }



}
