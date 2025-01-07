package api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Application_Controller class is responsible for initializing the program
 * managing data from files
 */
public class ApplicationController {

    public ApplicationController(){
        programInitialization();
    }

    /**
     * Reads a text file containing product data and creates a list of Product objects.
     *
     * @return A list of Product objects created from the file data.
     */
    public static List<Product> createProductsListFromTXT(){
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
                    String subCategory = in.readLine().split(":")[1].trim();
                    double price = Double.parseDouble(in.readLine().split(":")[1].trim().replace(",", ".").replace("€",""));
                    String stockLine = in.readLine().split(":")[1].trim();
                    if (stockLine.endsWith("kg")) {
                        double weight = Double.parseDouble(stockLine.replace("kg", "").trim());
                        Product product = new WeightedProduct(title, description, category, subCategory, price, weight);
                        products.add(product);
                    } else if (stockLine.endsWith("τεμάχια")) {
                        int quantity = Integer.parseInt(stockLine.replace("τεμάχια", "").trim());
                        Product product = new PackagedProduct(title, description, category, subCategory, price, quantity);
                        products.add(product);
                    } else {
                        System.err.println("Άγνωστη μονάδα μέτρησης για το προϊόν: " + title);
                    }
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
        return products;
    }

    /**
     * Initializes the program by creating default user accounts and products
     * track in "ProgramInitialized.txt" file.
     */
    private void programInitialization(){
        String filename = "ProgramInitialized.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            if (reader.readLine().equals("false")){

                User admin1 = new Admin("admin1", "password1");
                User admin2 = new Admin("admin2", "password2");
                User customer1 = new Customer("user1", "password1", "-", "-");
                User customer2 = new Customer("user2", "password2", "-", "-");

                List<User> userList = new ArrayList<>();

                userList.add(admin1);
                userList.add(admin2);
                userList.add(customer1);
                userList.add(customer2);

                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Accounts.ser"))) {
                    out.writeObject(userList);
                } catch (IOException ioE) {
                    System.err.println(" Error writing the Credentials list to the Accounts.ser file. : " + ioE);
                    programClose();
                }

                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Products.ser"))){
                    List<Product> products = createProductsListFromTXT();
                    out.writeObject(products);
                }catch(IOException ioe){
                    System.out.println("There was an error writing the products list to the Products.ser file: "+ioe);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    writer.write("true");
                    System.out.println("Program successfully Initialized.");
                }catch(IOException e) {
                    System.err.println("Error when writing to 'ProgramInitialized.txt file. " + e);
                    programClose();
                }
            }
            else{
                System.out.println("The program has already been initialized.");
            }
        }catch(IOException e) {
            System.out.println("Error reading the ProgramInitialized.txt file. : "+e);
        }
    }

    /**
     *  closes the program with a message and terminating the application.
     */
    private void programClose(){
        System.out.println("Program closing. . .");
        System.exit(0);
    }
}
