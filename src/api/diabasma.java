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
                    double price = Double.parseDouble(in.readLine().split(":")[1].trim().replace(",", "").replace("€",""));
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
        for(Product product: products)
        {
            System.out.println(product);
        }
        Scanner scanner =new Scanner(System.in);
        System.out.println("Δώστε τον τίτλο του προϊόντος:");
        String titleInput=scanner.nextLine().trim();
        boolean found=false;
        for (Product product:products)
        {
            if (product.getTitle().equalsIgnoreCase(titleInput))
            {
                System.out.println(product);
                found=true;
            }
        }
        if (!found)
        {
            System.out.println("Δεν βρέθηκαν προϊόν με αυτόν τον τίτλο");
        }

    }

}
