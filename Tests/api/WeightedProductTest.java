package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeightedProductTest {
    ApplicationController applicationController;
    Data data;
    private WeightedProduct product;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        product = (WeightedProduct) data.getProductFromProductsList("Πορτοκάλια 1kg");
    }



    @Test
    void getStock() {
        assertEquals(200, product.getStock());
    }

    @Test
    void setStock() {
        product.setStock(40.69);
        assertEquals(40.69, product.getStock());
    }

    @Test
    void reduceStock() {
        product.reduceStock(3000);
        assertEquals(197,product.getStock());
    }
}