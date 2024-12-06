package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class diabasma2
{

    public static void main(String[] args) throws IOException
    {
        try (BufferedReader in = new BufferedReader(new FileReader("categories_subcategories.txt"))) {
            String line;
            while ((line = in.readLine()) != null)
            {
                String[] parts = line.split("\\(");
                if (parts.length > 1)
                {
                    String maincategory=parts[0].trim();
                    String subCategories= parts[1].replace(")", "");
                    String[] subCategoryArray= subCategories.split("@");
                    System.out.println(maincategory);
                    for (String sub : subCategoryArray)
                    {
                        System.out.println(sub.trim());
                    }
                    System.out.println();
                }
                else
                {
                    System.out.println(line.trim());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Παρουσιάστηκε σφάλμα κατά την ανάγνωση του αρχείου:");
        }
        Scanner scanner =new Scanner(System.in);
        System.out.println("Δώστε την κατηγορία του προϊόντος:");
        String categoryInput=scanner.nextLine().trim();
        boolean found=false;
        for (Product product: products)
        {
            if (product.getCategory().equalsIgnoreCase(categoryInput))
            {
                System.out.println(product);
                found=true;
            }
        }
        if (!found)
        {
            System.out.println("Δεν βρέθηκαν προϊόν με αυτή την κατηγορία");
        }

    }
}

