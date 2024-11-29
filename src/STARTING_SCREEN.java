import java.util.Scanner;

public class STARTING_SCREEN {
    private Scanner scanner;

    public STARTING_SCREEN(){
        scanner = new Scanner(System.in);

    }
    public void ProgramStart(){
        ShowOptions();
        System.out.println();
        System.out.println("What would you like to do?\nOption: ");
        int option = scanner.nextInt();
        switch (option){
            case 1:
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
}
