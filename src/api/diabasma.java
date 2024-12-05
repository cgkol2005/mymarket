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
        List<String> titlosarray=new ArrayList<>();
        List<String[]> values = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("products.txt")))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                String[] parts = line.split(":");
                if (parts.length > 1)
                {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    values.add(new String[]{key, value});
                    if (key.equals("Τίτλος"))
                    {
                        titlosarray.add(value);
                    }
                }
                else
                {
                    values.add(new String[]{line.trim(), ""});
                }
            }
        } catch (IOException e)
        {
            System.err.println("Παρουσιάστηκε σφάλμα κατά την ανάγνωση του αρχείου: ");
        }
        for (String title : titlosarray)
        {
            System.out.println(title);
        }
        for (String[] pair : values)
        {
            System.out.println(pair[0] + ":" + pair[1]);

        }
        diabasma diabasmaInstance = new diabasma();
        diabasmaInstance.searchProduct(titlosarray);
    }
    public String searchProduct(List<String> titlosarray)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Δώστε τον τίτλο που θέλετε να αναζητήσετε:");
        String searchTitle = scanner.nextLine().trim();
        for (String title : titlosarray)
        {
            if (title.equals(searchTitle))
            {
                String result="Βρέθηκε ο τίτλος: " + title;
                System.out.println(result);
                return result;
            }
        }
        String notFoundMessage = "Ο τίτλος \"" + searchTitle + "\" δεν βρέθηκε.";
        System.out.println(notFoundMessage);
        return notFoundMessage;
    }

}
