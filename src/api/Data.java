package api;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
 * @author Mavridis Xaris
 */
public class Data {
    private List<User> Users;

    private List<Product> Products;

    private Customer currentCustomerUser;

    private Admin currentAdminUser;

    private List<JFrame> listOfActiveFrames;

    /**
     * Class Constructor: It loads the Data from the database (Products and Users files)
     */
    public Data(){
        listOfActiveFrames = new ArrayList<>();
        DataLoad();
    }

    public void addActiveFrame(JFrame frame){
        listOfActiveFrames.add(frame);
    }

    public void removeActiveFrame(String frameName){
        for (int i = 0;i<listOfActiveFrames.size();i++){
            if (listOfActiveFrames.get(i).getName().equals(frameName)){
                listOfActiveFrames.remove(i);
            }
        }
    }

    public List<JFrame> getListOfActiveFrames() {
        return listOfActiveFrames;
    }

    public JFrame getFrameFromActiveFramesList(String frameName){
        for (JFrame frame2 : listOfActiveFrames){
            if (frame2.getName().equals(frameName)){
                return frame2;
            }
        }
        return null; //return null if the frame wasn't found.
    }

    public void OverWriteUsersList(List<User> UsersList){
        Users = UsersList;
    }

    public void OverWriteProductsList(List<Product> ProductsList){
        Products = ProductsList;
    }

    public void AddUser(User user){
        Users.add(user);
    }

    public List<Product> getProductsList() {
        return Products;
    }

    public List<User> getUsersList() {
        return Users;
    }

    public Product getProductFromProductsList(String productName){
        for (Product product : Products){
            if (product.getTitle().equals(productName)){
                return product;
            }
        }
        return null; //return null if the program doesn't exist in the products list.
    }

    public void setCurrentCustomerUser(Customer user){
        this.currentCustomerUser = user;
    }

    public Customer getCurrentCustomerUser(){
        return currentCustomerUser;
    }

    public void DataLoad(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Accounts.ser"))){
            Users = (List<User>) in.readObject();
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch(IOException ioe){
            System.out.println("There was a problem reading from the Accounts.ser file.");
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Products.ser"))){
            Products = (List<Product>) in.readObject();
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }catch(IOException ioe){
            System.out.println("There was a problem reading from the products file."+ioe);
        }
    }

    public void DataSave(){
        try(ObjectOutputStream ProductsOut = new ObjectOutputStream(new FileOutputStream("Products.ser"));
            ObjectOutputStream UsersOut = new ObjectOutputStream(new FileOutputStream("Accounts.ser"))){
            ProductsOut.writeObject(Products);
            UsersOut.writeObject(Users);
        }catch(IOException ioe){
            System.out.println(ioe+"There was a problem with the writing of the Lists to the Products and Accounts files.");
        }
        System.out.println("Data saved!");
    }

}
