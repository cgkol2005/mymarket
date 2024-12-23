package api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Application_Controller {
    private Scanner scanner;
    private boolean Logged_In;
    private Accounts_Manager AccManager;
    private User CurrentUser;
    private Data data;

    public Application_Controller(){
        scanner = new Scanner(System.in);
        Logged_In = false;
        Program_Initialization();
        data = new Data();
        AccManager = new Accounts_Manager();
        CurrentUser = null;
    }



    public void ProgramStart(){
        Options();
    }

    private void Options(){
        int option = 0;
        if (Logged_In && CurrentUser instanceof Customer){
            System.out.println();
            System.out.println("1.Browse the shop.");
            System.out.println("5.Log-Out.");
            System.out.println("4.Exit");
            System.out.print("What would you like to do?\nOption: ");

            while(option != 1 && option != 4 && option != 5){
                try{
                    option = Integer.parseInt(scanner.next());
                    if (option < 1 || option > 4 && option != 5){
                        System.out.println("Not an option. Please enter a valid number.");
                    }
                }catch(NumberFormatException nfe){
                    System.err.println(nfe);
                    System.out.println("Not an option. Please enter a valid number.");
                }
            }
        } else if (Logged_In && CurrentUser instanceof Admin) {
            //Admin's Options. To be developed.Currently the same with Customer's Options
            //just to prevent stack overflow error. (Calls the Options() method infinitely)
            System.out.println();
            System.out.println("1.Browse the shop.");
            System.out.println("5.Log-Out.");
            System.out.println("4.Exit");
            System.out.print("What would you like to do?\nOption: ");

            while(option != 1 && option != 4 && option != 5){
                try{
                    option = Integer.parseInt(scanner.next());
                    if (option < 1 || option > 4 && option != 5){
                        System.out.println("Not an option. Please enter a valid number.");
                    }
                }catch(NumberFormatException nfe){
                    System.err.println(nfe);
                    System.out.println("Not an option. Please enter a valid number.");
                }
            }
        } else{
            System.out.println();
            System.out.println("Welcome to our e-shop!");
            System.out.println();
            System.out.println("1. Browse the shop as guest / Περιήγηση ως επισκέπτης");
            System.out.println("2. Sign-In / Σύνδεση");
            System.out.println("3. Sign-Up / Εγγραφή");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("What would you like to do?\nOption: ");

            while(option < 1 || option>4){
                try{
                    option = Integer.parseInt(scanner.next());
                    if (option < 1 || option > 4 ){
                        System.out.println("Not an option. Please enter a valid number.");
                    }
                }catch(NumberFormatException nfe){
                    System.err.println(nfe);
                    System.out.println("Not an option. Please enter a valid number.");
                }
            }

        }

        switch (option){
            case 1:Browse();
                break;
            case 2:
                CurrentUser = AccManager.Sign_In(data.getUsersList());
                Logged_In = true;
                break;
            case 3:
                List<User> NewUsersList = AccManager.Sign_Up(data.getUsersList());
                data.UpdateUserData(NewUsersList);
                break;
            case 5: Logged_In = false;
                break;
            case 4:Program_Close();
        }
        Options();
    }

    private void Browse() {
        System.out.println("BROWSER!");
    }

    public static List<Product> CreateProductsList(){
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
                List<User> Credentials = new ArrayList<>();
                Credentials.add(admin1);
                Credentials.add(admin2);
                Credentials.add(customer1);
                Credentials.add(customer2);
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Accounts.ser"))) {
                    out.writeObject(Credentials);
                } catch (IOException ioE) {
                    System.err.println(" Error writing the Credentials list to the Accounts.ser file. : " + ioE);
                    Program_Close();
                }

                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Products.ser"))){
                    List<Product> Products = CreateProductsList();
                    out.writeObject(Products);
                }catch(IOException ioe){
                    System.out.println("There was an error writing the products list to the Products.ser file: "+ioe);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    writer.write("true");
                    System.out.println("Program successfully Initialized.");
                }catch(IOException e) {
                    System.err.println("Error when writing to 'ProgramInitialized.txt file. Closing program.." + e);
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
        scanner.close();
        data.DataSave();
        System.exit(0);
    }
}
