package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderHistoryTest {
    private ApplicationController applicationController;
    private Data data;
    private Customer customer;
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
    }


    @Test
    void addOrderToHistoy() {
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        customer.completeOrder(data);
        boolean works = false;
        if (!customer.getOrderHistory().getOrdersList().isEmpty()){
            works =true;
        }
        assertTrue(works);
    }

    @Test
    void calculateAndUpdateTotalOrdersCost() {
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        Product product2 = data.getProductFromProductsList("Κατεψυγμένες Πίτσες Margherita 400g");
        customer.getShoppingCart().addProduct(product2, 10);
        customer.completeOrder(data);
        assertEquals(100.0, customer.getOrderHistory().getTotalPriceOfAllOrders());
    }

    @Test
    void getOrdersList() {
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        Product product2 = data.getProductFromProductsList("Κατεψυγμένες Πίτσες Margherita 400g");
        customer.getShoppingCart().addProduct(product2, 10);
        customer.completeOrder(data);
        List<Order> orderList = customer.getOrderHistory().getOrdersList();;

        assertEquals(false, orderList.isEmpty());
        assertEquals(1,orderList.size());
    }

    @Test
    void getOrderBasedOnOrderDate() {
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        Product product2 = data.getProductFromProductsList("Κατεψυγμένες Πίτσες Margherita 400g");
        customer.getShoppingCart().addProduct(product2, 10);
        customer.completeOrder(data);

        assertEquals(null, customer.getOrderBasedOnOrderDate("asd"));


    }

    @Test
    void getTotalNumberOfOrders() {
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        Product product2 = data.getProductFromProductsList("Κατεψυγμένες Πίτσες Margherita 400g");
        customer.getShoppingCart().addProduct(product2, 10);
        customer.completeOrder(data);
        Product product3 = data.getProductFromProductsList("Κατεψυγμένα Γεύματα Σπαγγέτι Μπολονέζ 450g");
        customer.getShoppingCart().addProduct(product3, 20);
        customer.completeOrder(data);
        assertEquals(2,customer.getOrderHistory().getTotalNumberOfOrders());
    }

    @Test
    void getTotalPriceOfAllOrders() {
        Product product = data.getProductFromProductsList("Φιλέτο Σολομού 300g");
        customer.getShoppingCart().addProduct(product, 5);
        Product product2 = data.getProductFromProductsList("Κατεψυγμένες Πίτσες Margherita 400g");
        customer.getShoppingCart().addProduct(product2, 10);
        customer.completeOrder(data);
        assertEquals(100.0, customer.getOrderHistory().getTotalPriceOfAllOrders());
    }
}