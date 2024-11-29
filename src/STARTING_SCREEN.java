import api.Admin;
import api.Customer;
import api.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class STARTING_SCREEN {
    private Scanner scanner;

    public STARTING_SCREEN(){
        scanner = new Scanner(System.in);


    }
    public void ProgramStart(){
        System.out.println();
        ShowOptions();
        System.out.println();
        System.out.print("What would you like to do?\nOption: ");
        int option = scanner.nextInt();
        switch (option){
            case 1:Browse();
            case 2:Sign_In();
            case 3:Sign_Up();
            case 4:program_close();
        }

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

    }
    private void Sign_In() {

    }
    private void Sign_Up() {
    }




    private void Program_Initialization()throws IOException{
        String filename = "ProgramInitialized.txt";
        File initializer = new File(filename);
        if (!initializer.exists()){
            try {
                initializer.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                writer.write("True");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Admin admin1 = new Admin("admin1", "password1");
            Admin admin2 = new Admin("admin2", "password2");
            Customer customer1 = new Customer("user1", "password1");
            Customer customer2 = new Customer("user2", "password2");
            customer1.setFirstname("-");
            customer1.setLastname("-");
            customer2.setFirstname("-");
            customer2.setLastname("-");

        }
    }

    private void program_close(){
        System.out.println("Program closing. . .");
        System.exit(0);
    }
}
