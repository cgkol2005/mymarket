package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    private Data data;
    private User admin;
    private ApplicationController controller;
    private BufferedWriter writer = null;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        controller = new ApplicationController();
        data = new Data();
        admin = new Admin("username1", "password1");
    }


    @Test
    void getPassword() {
        assertEquals("password1",admin.getPassword());
    }

    @Test
    void getUsername() {
        assertEquals("username1",admin.getUsername());
    }

    @Test
    void registerProductTestUniqueNameGetsAccepted() {
        Product product = new WeightedProduct("GreeklishNameThatDoesn'tExist","Description","Category", "Subcategory", 6.66, 666);
        if (admin instanceof Admin && product instanceof WeightedProduct){
            ((Admin) admin).registerProduct(product.getTitle(),product.getDescription(),product.getCategory(),product.getSub_category(),product.getPrice(),((WeightedProduct) product).getStock(),data,true);
        }
        boolean productFound = false;
        for (Product product1 : data.getProductsList()){
            if (product1.getTitle().equals(product.getTitle())){
                productFound = true;
            }
        }
        assertTrue(productFound);
    }

    @Test
    void registerProductTestNotUniqueNameGetsRejected() {
        Product product = new WeightedProduct("Καρότα 1kg","Description","Category", "Subcategory", 6.66, 666);
        if (admin instanceof Admin && product instanceof WeightedProduct){
            ((Admin) admin).registerProduct(product.getTitle(),product.getDescription(),product.getCategory(),product.getSub_category(),product.getPrice(),((WeightedProduct) product).getStock(),data,true);
        }
        boolean productFound = false;
        for (Product product1 : data.getProductsList()){
            if (product1.getTitle().equals("Καρότα 1kg") && product1.getPrice() == 6.66 && product1.getCategory().equals("Category")){
                productFound = true;
            }
        }
        assertFalse(productFound);
    }

    @Test
    void editProductsTestToCheckIfCarrotsChangeName() {
        if (admin instanceof Admin){
            ((Admin) admin).editProducts("Καρότα 1kg","Καροτίνια 25kg","Καινουργιο Description","Κατηγορια", "Υποκατηγορια", 25.34,200.0,data);
        }
        boolean productNameChanged = false;
        for (Product product: data.getProductsList()){
            if (product.getTitle().equals("Καροτίνια 25kg")){
                productNameChanged = true;
                System.out.println(product.getTitle());
            }
        }
        assertTrue(productNameChanged);
    }
}