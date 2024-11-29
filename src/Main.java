import api.Admin;
import api.Customer;
import api.User;

import java.io.*;

/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main {
    public static void main(String[] args) throws IOException {


        String initialized = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("initializer.txt"))) {
            initialized = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        if (initialized.equals("False")) {
            User admin1 = new Admin("admin1", "password1");
            User admin2 = new Admin("admin2", "password2");
            User customer1 = new Customer("user1", "password1","Χαρης","Μαυριδης");
            User customer2 = new Customer("user2", "password2","Χρηστος","Γκολφος");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("admins.bin",true));
            out.writeObject(admin1);
            out.writeObject(admin2);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("initializer.txt"))) {
                writer.write("True");
                writer.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }




    }
}
