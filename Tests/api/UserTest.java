package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    ApplicationController applicationController;
    Data data;
    private User user;

    @BeforeEach
    void setUp() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ProgramInitialized.txt"))){
            writer.write("false");
        }catch (IOException e){
            System.out.println(e);
        }
        applicationController = new ApplicationController();
        data = new Data();
        user = new User("username", "password");
    }

    @Test
    void getPassword() {
        assertEquals("username", user.getUsername());
    }

    @Test
    void getUsername() {
        assertEquals("password", user.getPassword());
    }
}