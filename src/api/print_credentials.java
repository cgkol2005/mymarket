package api;
import java.io.*;

public class print_credentials {
    public static void print(){
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
        }catch(IOException | ClassNotFoundException e){
            System.err.println(e);
            System.out.print(" ,problem in the print_credentials Class.");
        }
    }
}
