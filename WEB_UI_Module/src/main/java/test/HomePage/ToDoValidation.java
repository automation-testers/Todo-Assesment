package test.HomePage;

import BaseClasses.ToDoBaseTest;
import ImplementationClasses.ToDoImpl;
import org.testng.annotations.Test;



public class ToDoValidation extends ToDoBaseTest
{
    @Test(description = "Verify ToDo Page")
    public void verifyToDoPage()
    {

        add(new ToDoImpl()).trigger();
    }


}
