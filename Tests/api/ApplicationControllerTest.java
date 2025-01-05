package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {
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
    void createProductsListFromTXT() {
        List<Product> productsList = ApplicationController.createProductsListFromTXT();
        boolean itWorks = false;
        for (Product product : productsList){
            if (product.getTitle().equals("Πορτοκάλια 1kg")){
                itWorks = true;
            }
        }
        assertTrue(itWorks);
    }
}