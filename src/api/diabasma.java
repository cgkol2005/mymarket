package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class diabasma
{

    public static void main(String[] args) throws IOException
    {
        /*
        List<Product> products=new ArrayList<>();
        try (BufferedReader in =new BufferedReader(new FileReader("products.txt")))
        {
            String line;
            while ((line=in.readLine()) != null)
            {
                try
                {
                    if (line.trim().isEmpty())
                    {
                        continue;
                    }
                    String title = line.split(":")[1].trim();
                    String description = in.readLine().split(":")[1].trim();
                    String category = in.readLine().split(":")[1].trim();
                    String sub_category = in.readLine().split(":")[1].trim();
                    double price = Double.parseDouble(in.readLine().split(":")[1].trim().replace(",", ".").replace("€",""));
                    int stock = Integer.parseInt(in.readLine().split(":")[1].trim().replace("kg","").replace(" τεμάχια", ""));
                    products.add(new Product(title, description, category, sub_category, price, stock));
                }
                catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("Λάθος στην επεξεργασία ενός προϊόντος: " + e.getMessage());
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Παρουσιάστηκε σφάλμα κατά την ανάγνωση του αρχείου: ");
        }
         */
        List<Product> products = Application_Controller.CreateProductsList();
        Scanner scanner2 =new Scanner(System.in);
        System.out.println("Άν θέλεις αναζήτηση με τίτλος πάτα 1,εάν θέλεις αναζήτηση με κατηγορία πάτα 2 και εάν θέλεις και με τα δυό πάτα 3:");
        String methodos=scanner2.nextLine().trim();
        if (methodos.equals("1"))
        {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Δώστε τον τίτλο του προϊόντος:");
            String titleInput = scanner.nextLine().trim();
            boolean found = false;
            for (Product product : products) {
                if (product.getTitle().equalsIgnoreCase(titleInput)) {
                    System.out.println(product);
                    found = true;
                }
            }
            if (!found)
            {
                System.out.println("Δεν βρέθηκαν προϊόν με αυτόν τον τίτλο");
            }
        }

        else if (methodos.equals("2"))
        {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Δώστε την κατηγορία του προϊόντος:");
            String categoryInput = scanner1.nextLine().trim();
            boolean found1 = false;
            for (Product product : products) {
                if (product.getCategory().equalsIgnoreCase(categoryInput)) {
                    System.out.println(product);
                    found1 = true;
                }
            }
            if (!found1) {
                System.out.println("Δεν βρέθηκαν προϊόν με αυτή την κατηγορία");
            }
        }
        else if (methodos.equals("3"))
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Δώστε τον τίτλο του προϊόντος:");
            String titleInput = scanner.nextLine().trim();
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Δώστε την κατηγορία του προϊόντος:");
            String categoryInput = scanner1.nextLine().trim();
            boolean found = false;
            for (Product product : products)
            {
                if (product.getTitle().equalsIgnoreCase(titleInput)&& product.getCategory().equalsIgnoreCase(categoryInput))
                {
                    System.out.println(product);
                    found =true;
                }
            }
            if (!found)
            {
                System.out.println("Δεν βρέθηκαν προϊόν με αυτόν τον τίτλο και αυτή την κατηγορία");
            }

        }
        else
        {
            for(Product product: products)
            {
                System.out.println(product);
            }
        }
    }

}
