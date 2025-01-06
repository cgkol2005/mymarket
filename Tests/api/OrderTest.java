package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private ApplicationController applicationController;
    private Data data;
    private Customer customer;
    Order order;
    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        customer = new Customer("username", "password", "Firstname", "Lastname");
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        Product product2 = data.getProductFromProductsList("Κατεψυγμένες Πίτσες Margherita 400g");
        customer.getShoppingCart().addProduct(product2, 10);
        customer.completeOrder(data); //price == 100
        order = customer.getOrderHistory().getOrdersList().get(0);
    }

    @Test
    void getOrderDate() {
        assertNotEquals("22/5/2022", order.getOrderDate());
    }

    @Test
    void getNumberOfProductsBought() {
        boolean works = order.getNumberOfProductsBought() == 2;
        assertTrue(works);
    }

    @Test
    void getOrderPrice() {
        assertEquals(100.0, order.getOrderPrice());
    }

    @Test
    void getOrderShoppingCart() {
        assertTrue(order.getOrderShoppingCart() instanceof ShoppingCart);
    }
}