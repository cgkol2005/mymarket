package api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    ApplicationController applicationController;
    Data data;
    Customer customer;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        customer = new Customer("username1", "password1", "firtsname", "lastname");
    }

    @Test
    void getPassword() {
        assertEquals("password1", customer.getPassword());
    }

    @Test
    void getUsername() {
        assertEquals("username1", customer.getUsername());
    }

    @Test
    void completeOrder() {
        boolean worksCorrectlyUntilNow = true;
        worksCorrectlyUntilNow = customer.getOrderHistory().getOrdersList().isEmpty();
        Product product = new WeightedProduct("as","as","as","as",4.50,3.0);
        customer.getShoppingCart().addProduct(product, 5000); //when we put a weighted product in the cart, we measure it in grams!
        customer.completeOrder(data);
        worksCorrectlyUntilNow = !customer.getOrderHistory().getOrdersList().isEmpty();
        System.out.println(customer.getOrderHistory().getOrdersList().get(0).getOrderPrice());
        assertTrue(worksCorrectlyUntilNow);
    }

    @Test
    void getOrderHistory() {
        boolean works = false;
        if (customer.getOrderHistory() instanceof OrderHistory){
            works = true;
        }
        assertTrue(works);
    }

    @Test
    void getShoppingCart() {
        boolean works = false;
        if (customer.getShoppingCart() instanceof ShoppingCart){
            works = true;
        }
        assertTrue(works);
    }

    @Test
    void getOrderBasedOnOrderDate() {
        Product product = new WeightedProduct("as","as","as","as",4.50,3.0);


        customer.getShoppingCart().addProduct(product, 5);

        customer.completeOrder(data);

        String orderDate = customer.getOrderHistory().getOrdersList().get(0).getOrderDate();

        if(customer.getOrderBasedOnOrderDate(orderDate)!=null){
            assertTrue(true);
        }
    }
}