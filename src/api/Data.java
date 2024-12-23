package api;

import java.io.*;
import java.util.List;

/**
 * Overview:
 *      The Data class is a class responsible for handling all the data that needs to be
 *      processed and managed during the run and use of the program. What this means is simply that the Data
 *      class will be used to retrieve all the necessary information from the database (files), and will
 *      also store to the database all the appropriate information.
 *
 * Information that the Data class manages:
 *      E-shop's users( Credentials, Shopping Carts, Unfinished Orders, Order History )
 *      E-shop's products( Quantities, Availability, Product info , Categories - Subcategories )
 *
 *
 * @author Mavridis Xaris
 */
public class Data {
    private List<User> Users;
    private List<Product> Products;

    /**
     * Class Constructor: It loads the Data from the database(Products and Users files)
     */
    public Data(){
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

    public void UpdateUserData(List<User> UsersList){
        Users = UsersList;
    }
    public void UpdateProductsData(List<Product> ProductsList){
        Products = ProductsList;
    }
    public void DataSave(){
        try(ObjectOutputStream ProductsOut = new ObjectOutputStream(new FileOutputStream("Products.ser"));
            ObjectOutputStream UsersOut = new ObjectOutputStream(new FileOutputStream("Accounts.ser"))){
            ProductsOut.writeObject(Products);
            UsersOut.writeObject(Users);
        }catch(IOException ioe){
            System.out.println(ioe+"There was a problem with the writing of the Lists to the Products and Accounts files.");
        }
    }
    public List<Product> getProductsList() {
        return Products;
    }
    public List<User> getUsersList() {
        return Users;
    }

}
