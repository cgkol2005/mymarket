package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountsManagerTest {
    private AccountsManager accManager;
    private ApplicationController applicationController;
    private Data data;
    @BeforeEach
    void setUp() {
        accManager = new AccountsManager();
        applicationController = new ApplicationController();
        data = new Data();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signInTestCorrectCredentials() {
        User userTest = null;
        for (User user : data.getUsersList()){
            if (user.getUsername().equals("user1")){
                userTest = user;
            }
        }
        assertEquals(userTest,accManager.signIn("user1","password1",data.getUsersList()));
    }

    @Test
    void testSignInWrongInitialCredentials(){
        User userTest = null;
        for (User user : data.getUsersList()){
            if (user.getUsername().equals("user1")){
                userTest = user;
            }
        }
        assertNotEquals(userTest,accManager.signIn("user11","password12",data.getUsersList()));
    }

    @Test
    void testWrongCredentialsReturnNull(){
        assertNull(accManager.signIn("wrongUserName","fakePassword",data.getUsersList()));
    }

    @Test
    void testAlreadyExistingUserNameReturnsFalseSignUp() {
        assertFalse(accManager.signUp("user1", "anotherPassword","Georgios", "Giwrgopoulos",data));
    }

    @Test
    void testUniqueUserNameCreatesAccAndReturnsTrue(){
        assertTrue(accManager.signUp("giwrgaras24", "abgdezhth","Stavromeleneomws", "athanasiadhs",data));
        User testUser = null;
        for (User user : data.getUsersList()){
            if (user.getUsername().equals("giwrgaras24")){
                testUser = user;
            }
        }
        assertNotNull(testUser);
    }
}