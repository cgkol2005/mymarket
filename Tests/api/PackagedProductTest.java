package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PackagedProductTest {
    ApplicationController applicationController;
    Data data;
    PackagedProduct product;


    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        product = new PackagedProduct("title", "description","category","subcategory",5,5);

    }


    @Test
    void getStock() {
        assertEquals(5,product.getStock());
    }

    @Test
    void setStock() {
        product.setStock(6);
        assertEquals(6,product.getStock());
    }

    @Test
    void reduceStock() {
        product.reduceStock(3);
        assertEquals(2,product.getStock());
    }
}