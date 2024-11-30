import api.Admin;
import api.Customer;
import api.User;

import java.io.*;
import java.util.Scanner;

public class STARTING_SCREEN {
    private Scanner scanner;

    public STARTING_SCREEN(){
        scanner = new Scanner(System.in);
        Program_Initialization();

    }
    public void ProgramStart(){
        System.out.println();
        ShowOptions();
        System.out.println();
        System.out.print("What would you like to do?\nOption: ");
        int option = scanner.nextInt();
        switch (option){
            case 1:Browse();
            break;
            case 2:Sign_In();
                break;
            case 3:Sign_Up();
                break;
            case 4:program_close();
        }
        ProgramStart();

    }



    private void ShowOptions(){
        System.out.println("Welcome to our e-shop!");
        System.out.println();
        System.out.println("1. Browse the shop as guest / Περιήγηση ως επισκέπτης");
        System.out.println("2. Sign-In / Σύνδεση");
        System.out.println("3. Sign-Up / Εγγραφή");
        System.out.println("4. Exit");
    }
    private void Browse() {
        System.out.println("BROWSER!");
    }
    private void Sign_In() {
        scanner.nextLine();
        System.out.println("Enter your Username: ");
        String Username = scanner.nextLine();

        System.out.println("Enter your password: ");
        String Password = scanner.nextLine();
        System.out.println("Username: "+Username);
        System.out.println("Password: "+Password);

        System.out.println("Beginning the search of an object with these credentials...");
        ObjectInputStream in = null;
        User authentication = null;
        try{
            in = new ObjectInputStream(new FileInputStream("credentials.ser"));
            try{
                while(true){
                    authentication = (User) in.readObject();
                    if (authentication.getUsername().equals(Username)){
                        if (authentication.getPassword().equals(Password)){
                            System.out.println("Authentication successful!");
                            if (authentication instanceof Admin){
                                System.out.println("You are an Admin of the e-shop");
                            } else if (authentication instanceof Customer) {
                                System.out.println("You are a Customer of the e-shop");
                            }
                            break;
                        }
                    }
                }
            }catch(ClassNotFoundException | EOFException eof){
                System.out.println("File search ended, User not found");
            }
            in.close();
        }catch(IOException ioE){
            System.out.println("There was a problem reading from the credentials file!");
        }finally {
            if (in != null){
                try{
                    in.close();
                }catch(IOException ioE){
                    System.out.println("Problem closing the input stream.");
                }
            }
        }

    }
    private void Sign_Up() {
        System.out.println("IM THE SIGNUP!");
    }




    private void Program_Initialization(){
        String filename = "ProgramInitialized.txt";

        File initializer = new File(filename);
        BufferedWriter writer = null;
        if (!initializer.exists()) {

            try {

                initializer.createNewFile();

                writer = new BufferedWriter(new FileWriter(filename));

                writer.write("True");

                writer.close();

            } catch (IOException e) {
                System.out.println("An error occurred either with the file creation or with the file writer.");
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ioe) {
                        System.out.println("Writer could not be closed properly!");
                    }
                }
            }
            System.out.println("The file was successfully created!");

            User admin1 = new Admin("admin1", "password1");
            User admin2 = new Admin("admin2", "password2");

            User customer1 = new Customer("user1", "password1");
            User customer2 = new Customer("user2", "password2");

            ((Customer) customer1).setFirstname("-");
            ((Customer) customer1).setLastname("-");

            ((Customer) customer2).setFirstname("-");
            ((Customer) customer2).setLastname("-");

            File credentials = new File("credentials.ser");

            ObjectOutputStream out = null;

            try {
                if (credentials.createNewFile()) {
                    System.out.println("The credentials.ser file was successfully created!");
                } else {
                    System.out.println("The credentials.ser file was not created for some reason.");
                }


                out = new ObjectOutputStream(new FileOutputStream("credentials.ser"));

                out.writeObject(admin1);
                out.writeObject(admin2);
                out.writeObject(customer1);
                out.writeObject(customer2);

                out.close();

            } catch (IOException ioE) {
                System.out.println("There was an error either with the credentials file or with the Objects output stream");
            }finally {
                if(out != null){
                    try{
                        out.close();
                    }catch(IOException IoE){
                        System.out.println("There as a problem closing the Object Output Stream");
                    }
                }
            }

        }else{
            System.out.println("The program has already been already initialized.");
        }
    }

    private void program_close(){
        System.out.println("Program closing. . .");
        scanner.close();
        System.exit(0);
    }
}
