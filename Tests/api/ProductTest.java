package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    ApplicationController applicationController;
    Data data;
    Product product;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        product = new Product("title", "description","category","subcategory",5,5);

    }


    @Test
    void incrementTimesOrdered() {
        product.incrementTimesOrdered();
        assertEquals(1,product.getTimesOrdered());
    }

    @Test
    void getTitle() {
        assertEquals("title",product.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("description", product.getDescription());
    }

    @Test
    void getCategory() {
        assertEquals("category", product.getCategory());
    }

    @Test
    void getSub_category() {
        assertEquals("subcategory",product.getSub_category());
    }

    @Test
    void getPrice() {
        assertEquals(5,product.getPrice());
    }

    @Test
    void getTimesOrdered() {
        assertEquals(0,product.getTimesOrdered());
    }

    @Test
    void getTimesOrdered2() {
        product.incrementTimesOrdered();
        assertEquals(1,product.getTimesOrdered());
    }

    @Test
    void getAvailability() {
       assertTrue(product.getAvailability() == true);
    }


    @Test
    void setTitle() {
        product.setTitle("yolo");
        assertEquals("yolo", product.getTitle());
    }

    @Test
    void setDescription() {
        product.setDescription("mm");
        assertEquals("mm", product.getDescription());
    }

    @Test
    void setCategory() {
        product.setCategory("aa");
        assertEquals("aa", product.getCategory());
    }

    @Test
    void setSub_category() {
        product.setSub_category("bb");
        assertEquals("bb", product.getSub_category());
    }

    @Test
    void setPrice() {
        product.setPrice(666);
        assertEquals(666.0,product.getPrice());
    }

    @Test
    void setAvailable() {
        product.setAvailable(false);
        assertFalse(product.getAvailability());
    }
}