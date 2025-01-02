package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ShoppingCartTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
        ShoppingCart Cart = new ShoppingCart();
        Cart.addProduct(new PackagedProduct("Συσκευασμένο","Περιγραφή","Κατηγορία","Υποκατηγορία",5.5,5),4);
        assertEquals(22,Cart.getTotalPrice());
        Cart.addProduct(new WeightedProduct("Προϊόν με Βάρος","Περιγραφή","Κατηγορία","Υποκατηγορία",6.7,13.6),4.8);
        assertEquals(54.16,Cart.getTotalPrice());
    }

    @Test
    void testAddProduct() {

    }

    @Test
    void updateProductQuantityInCart() {
    }

    @Test
    void testUpdateProductQuantityInCart() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void calculateAndUpdateTotalPrice() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void getProducts_count() {
    }

    @Test
    void printShoppingCart() {
    }
}