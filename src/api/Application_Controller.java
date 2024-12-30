package api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Application_Controller {
    public Application_Controller(){
        Program_Initialization();
    }

    public static List<Product> CreateProductsListFromTXT(){
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
                    String stockLine = in.readLine().split(":")[1].trim();
                    if (stockLine.endsWith("kg")) {
                        double weight = Double.parseDouble(stockLine.replace("kg", "").trim());
                        Product product = new WeightedProduct(title, description, category, sub_category, price, weight);
                        products.add(product);
                    } else if (stockLine.endsWith("τεμάχια")) {
                        int quantity = Integer.parseInt(stockLine.replace("τεμάχια", "").trim());
                        Product product = new PackagedProduct(title, description, category, sub_category, price, quantity);
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

    private void Program_Initialization(){
        String filename = "ProgramInitialized.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            if (reader.readLine().equals("false")){

                User admin1 = new Admin("admin1", "password1");
                User admin2 = new Admin("admin2", "password2");
                User customer1 = new Customer("user1", "password1", "-", "-");
                User customer2 = new Customer("user2", "password2", "-", "-");

                List<User> UsersList = new ArrayList<>();

                UsersList.add(admin1);
                UsersList.add(admin2);
                UsersList.add(customer1);
                UsersList.add(customer2);

                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Accounts.ser"))) {
                    out.writeObject(UsersList);
                } catch (IOException ioE) {
                    System.err.println(" Error writing the Credentials list to the Accounts.ser file. : " + ioE);
                    Program_Close();
                }

                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Products.ser"))){
                    List<Product> Products = CreateProductsListFromTXT();
                    out.writeObject(Products);
                }catch(IOException ioe){
                    System.out.println("There was an error writing the products list to the Products.ser file: "+ioe);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    writer.write("true");
                    System.out.println("Program successfully Initialized.");
                }catch(IOException e) {
                    System.err.println("Error when writing to 'ProgramInitialized.txt file. " + e);
                    Program_Close();
                }
            }
            else{
                System.out.println("The program has already been initialized.");
            }
        }catch(IOException e) {
            System.out.println("Error reading the ProgramInitialized.txt file. : "+e);
        }
    }

    private void Program_Close(){
        System.out.println("Program closing. . .");
        System.exit(0);
    }

}
