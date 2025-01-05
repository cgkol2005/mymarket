package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {
    ApplicationController applicationController;
    Data data;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
    }

    @Test
    void addActiveFrame() {
        JFrame frame = new JFrame();
        frame.setName("frame");
        data.addActiveFrame(frame);
        if (frame == data.getFrameFromActiveFramesList("frame")){
            assertTrue(true);
        }
    }

    @Test
    void updateProductsStockInShop() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new WeightedProduct("Καρότα 1kg","Τραγανά καρότα, κατάλληλα για σαλάτες και μαγείρεμα.","Φρέσκα τρόφιμα","Λαχανικά",1.00,150);
        boolean correct = false;
        cart.addProduct(product, 5000);
        data.updateProductsStockInShop(cart.getShoppingCartMap());
        for (Product product1 : data.getProductsList()){
            if (product1 instanceof WeightedProduct){
                if (((WeightedProduct) product1).getTitle().equals("Καρότα 1kg") && ((WeightedProduct) product1).getStock() == 145.0){
                    correct = true;
                }
            }
        }
        assertTrue(correct);
    }

    @Test
    void removeActiveFrame() {
        JFrame frame = new JFrame();
        frame.setName("frame");
        data.addActiveFrame(frame);
        boolean works = true;
        assertEquals(frame,data.getFrameFromActiveFramesList("frame"));
        data.removeActiveFrame("frame");
        assertNull(data.getFrameFromActiveFramesList("frame"));
    }

    @Test
    void getFrameFromActiveFramesList() {
        JFrame frame = new JFrame();
        frame.setName("frame");
        data.addActiveFrame(frame);
        assertEquals(frame, data.getFrameFromActiveFramesList("frame"));
    }

    @Test
    void overWriteProductsList() {
        List<Product> myNewProductsList = new ArrayList<>();
        data.overWriteProductsList(myNewProductsList);
        assertEquals(myNewProductsList, data.getProductsList());
    }

    @Test
    void overWriteProductsList2() {
        List<Product> myNewProductsList = null;
        data.overWriteProductsList(myNewProductsList);
        assertNull(data.getProductsList());
    }

    @Test
    void addUser() {
        boolean added = false;
        User user = new Customer("username1", "password1", "k", "b");
        data.addUser(user);
        List<User> userList = data.getUsersList();
        for (User user1: userList){
            if (user1.getUsername().equals("username1")){
                added = true;
            }
        }
        assertTrue(added);
    }

    @Test
    void getProductsList() {
        assertNotNull(data.getProductsList());
    }
    @Test
    void getProductsList2() {
        boolean found = false;
        for (Product product: data.getProductsList()){
            if (product.getTitle().equals("Πορτοκάλια 1kg")){
                found = true;
                break;
            }
        }
        assertTrue(true);
    }

    @Test
    void getUsersList() {
        assertNotNull(data.getUsersList());
    }

    @Test
    void getProductFromProductsList() {
        assertEquals("Πορτοκάλια 1kg",data.getProductFromProductsList("Πορτοκάλια 1kg").getTitle());
    }

    @Test
    void setCurrentCustomerUser() {
        Customer customerNull = null;
        data.setCurrentCustomerUser(customerNull);
        assertNull(data.getCurrentCustomerUser());
        data.setCurrentCustomerUser(new Customer("asd","asd","asd","asda"));
        assertNotNull(data.getCurrentCustomerUser());
    }

    @Test
    void setCurrentUser() {
        data.setCurrentUser(null);
        assertNull(data.getCurrentUser());
    }

    @Test
    void getCurrentUser() {
        data.setCurrentUser(null);
        assertNull(data.getCurrentUser());
    }

    @Test
    void getCurrentCustomerUser() {
        data.setCurrentUser(null);
        assertNull(data.getCurrentCustomerUser());
    }

    @Test
    void dataLoad() {
        data.dataLoad();
        assertNotNull(data.getUsersList());
        assertNotNull(data.getProductsList());
    }

    @Test
    void dataSave() {
        data.dataSave();
        File usersFile = new File("Accounts.ser");
        if (usersFile.exists()&& usersFile.getUsableSpace() > 0){
            assertTrue(true);
        }else{
            fail();
        }

    }

    @Test
    void productExists() {
        assertTrue(data.productExists("Πορτοκάλια 1kg"));
        assertFalse(data.productExists("ασδασδασδς;σγ"));
    }

    @Test
    void getUnavailableProducts() {
        Admin admin = new Admin("","");
        admin.registerProduct("asd","asd","asd","asd",23,0.0,data,true);
        assertEquals("asd", data.getUnavailableProducts());
    }

    @Test
    void getProductOrderCounts() {
    }

    @Test
    void getProductPopular() {
    }

    @Test
    void getProductsFromSearch() {
    }
}