package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        System.out.println("asda");
    }
}

