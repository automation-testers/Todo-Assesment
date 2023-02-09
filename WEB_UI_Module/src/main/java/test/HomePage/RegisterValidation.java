package test.HomePage;

import BaseClasses.ToDoBaseTest;
import ImplementationClasses.RegisterImpl;
import org.testng.annotations.Test;

public class RegisterValidation extends ToDoBaseTest {
    @Test(description = "Verify Register Page")
    public void verifyRegisterPage() {

        add(new RegisterImpl()).trigger();
    }
}