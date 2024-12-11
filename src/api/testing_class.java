package api;

import java.io.*;

public class testing_class {
    /*
    public static void print()throws IOException {
        User user = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.ser"))){
            while (true){
                try{
                    user = (User) in.readObject();
                    System.out.printf("Username: %s, Password: %s%n", user.getUsername(),user.getPassword());
                }catch(EOFException e){
                    System.out.println(e);
                    break;
                }
            }
        }catch(ClassNotFoundException | FileNotFoundException e){
            System.err.println(e);
            //System.out.println(e);
        }
    }

     */
    /*
    public static void main(String[] args) {
        User user = null;
        int i = 1;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("credentials.ser"))){
            try{
                while(true){
                    user = (User) in.readObject();
                    System.out.printf("User"+i+"Username: %s, Password: %s\n", user.getUsername(),user.getPassword());
                    i++;
                }
            }catch(EOFException e){
                System.out.println("File ended, error: ");
                System.err.print(e);
            }
        }catch(ClassNotFoundException | IOException e){
            System.out.println("Error: ");
            System.err.println(e);
            System.out.print(" ,initialize the program once again and then run me.");
        }
    }

     */
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Protokalia", "portokali", "frouta", "dasda", 5.4, 40), 5);

        Product mantarinia = new Product("mantarinia", "portokali", "frouta", "dasda", 5.4, 40);
        cart.addProduct(mantarinia, 10);

        cart.printShoppingCart();

        cart.removeProduct(mantarinia);

        cart.addProduct(new Product("mila", "portokali", "frouta", "dasda", 5.4, 40), 2);
        //cart.updatestock();

        System.out.println("Total price: " + cart.getTotalPrice());

        cart.printShoppingCart();


    }
}
