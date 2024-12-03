package api;

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
            case 4:Program_Close();
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
        //scanner.nextLine();
        System.out.println("Press 0 to exit, press 1 to enter credentials");
        int choice = Integer.parseInt(scanner.next());
        if (choice == 0){
            ProgramStart();
        }
        scanner.nextLine();
        System.out.println("Enter your Username: ");
        String Username = scanner.nextLine();

        System.out.println("Enter your Password: ");
        String Password = scanner.nextLine();
        System.out.println("Username: "+Username);
        System.out.println("Password: "+Password);


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
                System.out.println(eof + "FIle ended, the credentials you entered do not match any account. Try again: ");
                Sign_In();
            }
            in.close();
        }catch(IOException ioE){
            System.out.println(ioE + "There was a problem reading from the credentials file!");
        }finally {
            if (in != null){
                try{
                    in.close();
                }catch(IOException ioE){
                    System.out.println(ioE + "Problem closing the input stream.");
                }
            }
        }

    }
    private void Sign_Up() {
        System.out.println("This is the sign-up process.");
        System.out.println("Enter your firstname: ");
        String FirstName = scanner.next();
        System.out.println("Enter your lastname: ");
        String LastName = scanner.next();
        String Username = null;
        ObjectInputStream in = null;
        User user = null;
        while (true) {
            System.out.println("Enter your account's username: ");
            Username = scanner.next();
            try {
                in = new ObjectInputStream(new FileInputStream("credentials.ser"));
                try {
                    while (true) {
                        user = (User) in.readObject();
                        if (user.getUsername().equals(Username)) {
                            System.out.println("The Username you entered has been taken. Please enter a unique Username.");
                            break;
                        }
                    }
                    in.close();
                } catch (ClassNotFoundException | EOFException eof) {
                    System.out.println(eof + "Username successfully created.");
                    break;
                }finally {
                    if (in!=null){
                        try{
                            in.close();
                        }catch(IOException e){
                            System.out.println(e + "There was a problem closing the ObjectInput Stream. . .");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e + "An error occurred with initialization of the Stream Input");
                System.out.println("Aborting . . .");
                Program_Close();
            }
        }

        String Password;
        while(true){
            System.out.println("Enter your account's password");
            Password = scanner.next();
            System.out.println("Confirm your password once again: ");
            String Password2 = scanner.next();
            if (Password.equals(Password2)){
                break;
            }else System.out.println("The passwords doesn't match! Please repeat the password process..");
        }
        System.out.println("Password confirmed!");
        User User = new Customer(Username, Password, FirstName, LastName);
        System.out.println("Object created. Proof: Here's the object's username: "+User.getUsername());
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("credentials.ser",true));
            out.writeObject(User);
            out.close();
        }catch(IOException e){
            System.out.println(e + "There was a problem. Couldn't write the object, or credentials.ser file not properly used.");
        }finally{
            if (out!= null){
                try{
                    out.close();
                }catch(IOException e){
                    System.out.println(e + "Problem closing the Output Stream file!");
                }
            }
        }
        System.out.println("If everything worked correctly the new user has been assigned. Please try to sign-in to confirm!");
        ProgramStart();
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
                System.out.println(e + "An error occurred either with the file creation or with the file writer.");
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ioe) {
                        System.out.println(ioe + "Writer could not be closed properly!");
                    }
                }
            }
            System.out.println("The file was successfully created!");

            User admin1 = new Admin("admin1", "password1");
            User admin2 = new Admin("admin2", "password2");

            User customer1 = new Customer("user1", "password1","-","-");
            User customer2 = new Customer("user2", "password2","-","-");


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
                System.out.println(ioE + "There was an error either with the credentials file or with the Objects output stream");
            }finally {
                if(out != null){
                    try{
                        out.close();
                    }catch(IOException IoE){
                        System.out.println(IoE + "There as a problem closing the Object Output Stream");
                    }
                }
            }

        }else{
            System.out.println("The program has already been already initialized.");
        }
    }

    private void Program_Close(){
        System.out.println("Program closing. . .");
        scanner.close();
        System.exit(0);
    }
}
