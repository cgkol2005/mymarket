package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ShoppingCartTest {
    ApplicationController applicationController;
    Data data;
    private Customer customer;
    private Product product;
    private ShoppingCart cartOfCustomer;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        customer = new Customer("a","a","a","a");
        product = data.getProductFromProductsList("Κατεψυγμένα Γεύματα Σπαγγέτι Μπολονέζ 450g");
        cartOfCustomer = customer.getShoppingCart();
        customer.getShoppingCart().addProduct(product,5);
    }


    @Test
    void addProduct() {
        assertEquals(1,cartOfCustomer.getProducts_count());

    }

    @Test
    void updateProductQuantityInCart() {
        cartOfCustomer.UpdateProductQuantityInCart(product,10);
        assertEquals(15, cartOfCustomer.getProductQuantityInShoppingCart("Κατεψυγμένα Γεύματα Σπαγγέτι Μπολονέζ 450g"));
    }


    @Test
    void removeProduct() {
        cartOfCustomer.removeProduct("Κατεψυγμένα Γεύματα Σπαγγέτι Μπολονέζ 450g");
        boolean sizeIsZeroNow = cartOfCustomer.getShoppingCartMap().size() == 0;

        assertTrue(sizeIsZeroNow);
    }

    @Test
    void calculateAndUpdateTotalPrice() {
        cartOfCustomer.CalculateAndUpdateTotalPrice();
        assertEquals(19, cartOfCustomer.getTotalPrice());
    }

    @Test
    void removeQuantityFromShoppingCart() {
        cartOfCustomer.removeQuantityFromShoppingCart(product, 3);
        assertEquals(2, cartOfCustomer.getProductQuantityInShoppingCart("Κατεψυγμένα Γεύματα Σπαγγέτι Μπολονέζ 450g"));
    }

    @Test
    void createArrayListFromShoppingCart() {
        List<Product> productsInCartList = cartOfCustomer.createArrayListFromShoppingCart();
        assertTrue(productsInCartList.size() == 1);
    }

    @Test
    void getProductBasedOnListIndex() {
        assertTrue(cartOfCustomer.getProductBasedOnListIndex(0) != null && cartOfCustomer.getProductBasedOnListIndex(0) instanceof PackagedProduct);
    }

    @Test
    void getTotalPrice() {
        assertEquals(19, cartOfCustomer.getTotalPrice());
    }

    @Test
    void getProducts_count() {
        assertEquals(1, cartOfCustomer.getProducts_count());
    }


    @Test
    void getProductQuantityInShoppingCart() {
        assertEquals(5, cartOfCustomer.getProductQuantityInShoppingCart("Κατεψυγμένα Γεύματα Σπαγγέτι Μπολονέζ 450g"));
    }


    @Test
    void getShoppingCartMap() {
        HashMap<Product, Integer> cartMap = cartOfCustomer.getShoppingCartMap();
        assertNotNull(cartMap);
    }

    @Test
    void clear() {
        cartOfCustomer.clear();
        assertEquals(0, cartOfCustomer.getShoppingCartMap().size());
    }

    @Test
    void calculateTotalPriceOfAProductInCart() {
        assertEquals(19.0, cartOfCustomer.CalculateTotalPriceOfAProductInCart(product));
    }
}