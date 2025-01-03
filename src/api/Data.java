package api;

import javax.swing.*;
import api.WeightedProduct;
import api.PackagedProduct;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Overview:
 *      The Data class is a class responsible for handling all the data that needs to be
 *      processed and managed during the run and use of the program. What this means is simply that the Data
 *      class will be used to retrieve all the necessary information from the database (files), and will
 *      also store to the database all the appropriate information.
 * Information that the Data class manages:
 *      E-shop's users (Credentials, Shopping Carts, Unfinished Orders, Order History)
 *      E-shop's products (Quantities, Availability, Product info, Categories - Subcategories )
 *
 *
 * @author Mavridis Xaris ,Golfos Christos
 */
public class Data {
    /** List of all users in the system. */
    private List<User> Users;
    /** List of all products in the system. */
    private List<Product> Products;
    /** List of frames */
    private String unavailableProducts;
    /** The logged-in customer. */
    private Customer currentCustomerUser;
    /** The logged-in admin  */
    private Admin currentAdminUser;

    private User currentUser;

    private List<JFrame> listOfActiveFrames;


    /**
     * Class Constructor: It loads the Data from the database (Products and Users files)
     */
    public Data() {
        listOfActiveFrames = new ArrayList<>();
        DataLoad();
    }
    /**
     * Adds a frame to the list of active frames.
     * @param frame the frame add
     */

    public void addActiveFrame(JFrame frame) {
        listOfActiveFrames.add(frame);
    }

    /**
     * Removes a frame from the list by its name.
     *
     * @param frameName The name of the frame to remove.
     */
    public void removeActiveFrame(String frameName) {
        for (int i = 0; i < listOfActiveFrames.size(); i++) {
            if (listOfActiveFrames.get(i).getName().equals(frameName)) {
                listOfActiveFrames.remove(i);
            }
        }
    }

    /**
     * @return A list of active frames.
     */
    public List<JFrame> getListOfActiveFrames() {
        return listOfActiveFrames;
    }

    /**
     * @param frameName The name of the frame to retrieve.
     * @return The frame if found, or null
     */
    public JFrame getFrameFromActiveFramesList(String frameName) {
        for (JFrame frame2 : listOfActiveFrames) {
            if (frame2.getName().equals(frameName)) {
                return frame2;
            }
        }
        return null; //return null if the frame wasn't found.
    }

    /**
     * Overwrites the current list of users with a new list.
     * @param UsersList The new list of users.
     */
    public void OverWriteUsersList(List<User> UsersList) {
        Users = UsersList;
    }

    /**
     * Overwrites the current list of products with a new list.
     * @param ProductsList The new list of products.
     */
    public void OverWriteProductsList(List<Product> ProductsList) {
        Products = ProductsList;
    }

    /**
     * Adds a user to the list of users.
     * @param user The user to add.
     */
    public void AddUser(User user) {
        Users.add(user);
    }

    /**
     * @return The list of products.
     */
    public List<Product> getProductsList() {
        return Products;
    }

    /**
     * @return The list of users.
     */
    public List<User> getUsersList() {
        return Users;
    }

    /**
     * @param productName The name of the product to retrieve.
     * @return The product if found or null
     */
    public Product getProductFromProductsList(String productName) {
        for (Product product : Products) {
            if (product.getTitle().equals(productName)) {
                return product;
            }
        }
        return null; //return null if the program doesn't exist in the products list.
    }

    /**
     * Sets the current logged-in customer
     * @param user customer
     */
    public void setCurrentCustomerUser(Customer user) {
        this.currentCustomerUser = user;
    }

    /**
     * Sets the current logged-in admin.
     * @param user  admin
     */

    public void setCurrentAdminUser(Admin user) {
        this.currentAdminUser = user;
    }

    /**
     * Sets the current logged-in user.
     * @param user user
     */
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Retrieves the current logged-in user
     * @return The current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Retrieves the current logged-in admin
     * @return The current admin
     */
    public Admin getCurrentAdminUser() {
        return currentAdminUser;
    }

    public Customer getCurrentCustomerUser() {
        return currentCustomerUser;
    }

    /**
     * Loads data from the database files.
     */
    public void DataLoad() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Accounts.ser"))) {
            Users = (List<User>) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            System.out.println("There was a problem reading from the Accounts.ser file.");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Products.ser"))) {
            Products = (List<Product>) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            System.out.println("There was a problem reading from the products file." + ioe);
        }
    }

    public void setUsersList(List<User> users) {
        this.Users = users;
    }

    /**
     * Saves data to the database files.
     */
    public void DataSave() {
        try (ObjectOutputStream ProductsOut = new ObjectOutputStream(new FileOutputStream("Products.ser"));
             ObjectOutputStream UsersOut = new ObjectOutputStream(new FileOutputStream("Accounts.ser"))) {
            ProductsOut.writeObject(Products);
            UsersOut.writeObject(Users);
        } catch (IOException ioe) {
            System.out.println(ioe + "There was a problem with the writing of the Lists to the Products and Accounts files.");
        }
        System.out.println("Data saved!");
    }

    /**
     * Checks if a product with the given name exists in the product list.
     * @param name The name of the product to check.
     * @return True if the product exists else false.
     */
    public boolean productExists(String name) {
        for (Product product : getProductsList()) {

            if (product.getTitle().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a string of all unavailable products.
     * @return A string of unavailable product names
     */
    public String getUnavailableProducts() {
        StringBuilder unavailableProductsBuilder = new StringBuilder();
        for (Product product : Products) {
            if (product instanceof PackagedProduct && ((PackagedProduct) product).getStock() == 0) {
                unavailableProductsBuilder.append(product.getTitle()).append("\n");
            } else if (product instanceof WeightedProduct && ((WeightedProduct) product).getStock() == 0.0) {
                unavailableProductsBuilder.append(product.getTitle()).append("\n");
            }
        }
        return unavailableProductsBuilder.toString().trim();
    }

    /**
     * Retrieves a map of products and their order counts.
     * @return A map where keys are products and values are the number of times each product was ordered.
     */
    public Map<Product, Integer> getProductOrderCounts() {
        Map<Product, Integer> productOrderCounts = new HashMap<>();
        for (Product product : Products) {
            productOrderCounts.put(product, product.getTimesOrdered());
        }
        return productOrderCounts;
    }

    /**
     * Retrieves the most popular products based on the number of orders.
     * @return A list of the most popular products.
     */
    public List<Product> getProductPopular() {
        Map<Product, Integer> productOrderCounts=getProductOrderCounts();
        int maxOrders = productOrderCounts.values().stream().max(Integer::compare).orElse(0);
        List<Product> mostOrderedProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productOrderCounts.entrySet()) {
            if (entry.getValue() == maxOrders) {
                mostOrderedProducts.add(entry.getKey());
            }
        }
        return mostOrderedProducts;
    }

}
