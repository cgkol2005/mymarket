package api;

import java.io.*;
import java.util.Scanner;

public class LAUNCHER {
    private Scanner scanner;

    public LAUNCHER(){
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
            case 2:
                ACCOUNTS.Sign_In();
                break;
            case 3:
                ACCOUNTS.Sign_Up();
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

    private void Program_Initialization(){
        String filename = "ProgramInitialized.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            if (reader.readLine().equals("false")){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    writer.write("true");
                    System.out.println("Program Initialized.");
                }catch(IOException e) {
                    System.err.println("An error occurred either with the file creation or with the file writer: " + e);
                    Program_Close();
                }

                User admin1 = new Admin("admin1", "password1");
                User admin2 = new Admin("admin2", "password2");
                User customer1 = new Customer("user1", "password1", "-", "-");
                User customer2 = new Customer("user2", "password2", "-", "-");

                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("credentials.ser"))) {
                    out.writeObject(admin1);
                    out.writeObject(admin2);
                    out.writeObject(customer1);
                    out.writeObject(customer2);
                } catch (IOException ioE) {
                    System.err.println("There was an error either with the credentials file or with the Objects output stream: " + ioE);
                    Program_Close();
                }
            }
            else{
                System.out.println("The program has already been initialized.");
            }

        }catch(IOException e) {
            System.err.println(e);
        }


    }
    private void Program_Close(){
        System.out.println("Program closing. . .");
        scanner.close();
        System.exit(0);
    }
}
