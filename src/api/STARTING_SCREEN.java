package api;

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
        while (true) {
            System.out.println("Press 0 to exit, press 1 to enter credentials");
            int choice = Integer.parseInt(scanner.next());
            if (choice == 0) {
                ProgramStart();
                return;
            }
            scanner.nextLine();
            System.out.println("Enter your Username: ");
            String Username = scanner.nextLine();

            System.out.println("Enter your Password: ");
            String Password = scanner.nextLine();
            System.out.println("Username: " + Username);
            System.out.println("Password: " + Password);

            User authentication = null;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.ser"))) {
                while (true) {
                    try {
                        authentication = (User) in.readObject();
                        if (authentication.getUsername().equals(Username) && authentication.getPassword().equals(Password)) {
                            System.out.println("Authentication successful!");
                            if (authentication instanceof Admin) {
                                System.out.println("You are an Admin of the e-shop");
                            } else if (authentication instanceof Customer) {
                                System.out.println("You are a Customer of the e-shop");
                            }
                            return;
                        }
                    } catch (EOFException eof) {
                        System.err.println(eof);
                        System.out.print("file ended, the credentials you entered do not match any account. Try again: ");
                        break;
                    }
                }
            } catch (ClassNotFoundException | IOException E) {
                System.err.println(E);
                System.out.print("There was a problem reading from the credentials file!");
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

        User user = null;
        while (true) {
            System.out.println("Enter your account's username(one word, no spaces): ");
            Username = scanner.next();
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.ser"))) {
                try {
                    while (true) {
                        user = (User) in.readObject();
                        if (user.getUsername().equals(Username)) {
                            System.out.println("The Username you entered has been taken. Please enter a unique Username.");
                            break;
                        }
                    }
                } catch (EOFException eof) {
                    System.err.println(eof);
                    System.out.println(" ,username successfully created.");
                    break;
                }
            } catch (ClassNotFoundException | IOException e) {
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
        System.out.println("Object created. Proof: Here's the object's info: "+User.getUsername()+ User.getPassword());

        try (FileOutputStream fos = new FileOutputStream("credentials.ser", true);
             AppendableObjectOutputStream out = new AppendableObjectOutputStream(fos)) {
            out.writeObject(User);
        } catch (IOException e) {
            System.err.println(e);
            System.out.println("There was a problem. Couldn't write the object, or credentials.ser file not properly used.");
            Program_Close();
        }

        /*
        try{
            testing_class.print();
        }catch (IOException e){
            System.out.println(e);
        }

         */
        System.out.println("If everything worked correctly the new user has been assigned. Please try to sign-in to confirm!");
        print_credentials.print();
        ProgramStart();
    }




    private void Program_Initialization() {
        String filename = "ProgramInitialized.txt";
        File initializer = new File(filename);

        if (!initializer.exists()) {
            try {
                if (initializer.createNewFile()) {
                    System.out.println("The initializer file was successfully created!");
                } else {
                    System.out.println("The initializer not created for some reason???");
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    writer.write("True");
                }

            } catch (IOException e) {
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

        } else {
            System.out.println("The program has already been initialized.");
        }
    }


    private void Program_Close(){
        System.out.println("Program closing. . .");
        scanner.close();
        System.exit(0);
    }
}
