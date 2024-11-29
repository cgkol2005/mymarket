import api.Admin;
import api.Customer;
import api.User;

import java.io.*;
import java.util.Scanner;

public class STARTING_SCREEN {
    private Scanner scanner;

    public STARTING_SCREEN() throws IOException, ClassNotFoundException {
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




    private void Program_Initialization() throws IOException, ClassNotFoundException {
        String filename = "ProgramInitialized.txt";

        File initializer = new File(filename);

        if (!initializer.exists()){

            try {

                initializer.createNewFile();

                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

                writer.write("True");
                writer.close();

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

            File credentials = new File("credentials.bin");
            credentials.createNewFile();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("credentials.bin",true));

            out.writeObject(admin1);
            out.writeObject(admin2);
            out.writeObject(customer1);
            out.writeObject(customer2);

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.bin"));

            Admin testadmin1 = (Admin) in.readObject();
            Admin testadmin2 = (Admin) in.readObject();
            Customer testcustomer1 = (Customer) in.readObject();
            Customer testcustomer2 = (Customer) in.readObject();



            /*
            System.out.printf("Admin1: Username-> %s, Password-> %s\n" +
                    "Admin2: Username-> %s, Password-> %s\n" +
                    "Customer1: Username-> %s, Password-> %s\n" +
                    "Customer2: Username-> %s, Password-> %s .",admin1.getUsername(),admin1.getPassword(),
                    admin2.getUsername(),admin2.getPassword(),customer1.getUsername(),customer1.getPassword(),
                    customer2.getUsername(),customer2.getPassword());

             */

            System.out.printf("Admin1: Username-> %s, Password-> %s\n" +
                            "Admin2: Username-> %s, Password-> %s\n" +
                            "Customer1: Username-> %s, Password-> %s\n" +
                            "Customer2: Username-> %s, Password-> %s .",testadmin1.getUsername(),testadmin1.getPassword(),
                    testadmin2.getUsername(),testadmin2.getPassword(),testcustomer1.getUsername(),testcustomer1.getPassword(),
                    testcustomer2.getUsername(),testcustomer2.getPassword());



        }
    }

    private void program_close(){
        System.out.println("Program closing. . .");
        System.exit(0);
    }
}
