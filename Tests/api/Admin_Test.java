package api;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class Admin_Test {
    private Admin admin;
    private Data data;
    @BeforeEach
    public void setUp() {
        admin = new Admin("admin1", "password1");
        data = new Data();
        List<Product> initialProducts = new ArrayList<>();
        for (int i = 1; i <= 38; i++) {
            initialProducts.add(new PackagedProduct("Product" + i, "Description" + i, "Category", "Subcategory", 1.0, 10));
        }
        data.setProductsList(initialProducts);
    }
    @Test
    void testRegisterProduct() {
        admin.RegisterProduct("Μανταρίνια 1kg", "Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.", "Φρέσκα τρόφιμα", "Φρούτα", 2.0, 150, data);
        List<Product> productList = data.getProductsList();
        assertEquals(39, productList.size());
        Product newProduct = productList.get(38);
        assertEquals("Μανταρίνια 1kg", newProduct.getTitle());
    }
    @Test
    void testEditExistingProduct() {
        admin.RegisterProduct("Πορτοκάλια 1kg", "Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.", "Φρέσκα τρόφιμα", "Φρούτα", 1.2, 200, data);
        admin.EditProducts("Πορτοκάλια 1kg","Πορτοκάλια 2kg","Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.", "Φρέσκα τρόφιμα", "Φρούτα", 2, 150, data);
        Product product = data.getProductsList().get(0);
        assertEquals("Πορτοκάλια 2kg", product.getTitle());
        assertEquals(2.0, product.getPrice());
        assertEquals(150, ((PackagedProduct) product).getStock());
    }
    @Test
    void testEditNonExistingProduct() {
        admin.EditProducts("NonExistent", "New Name", "New Desc", "Category", "Subcategory", 2.0, 50, data);
        assertFalse(data.getProductsList().isEmpty());
    }



}
