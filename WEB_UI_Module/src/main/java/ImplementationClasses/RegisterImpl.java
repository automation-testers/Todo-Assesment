package ImplementationClasses;

import BaseClasses.Feature;
import BaseClasses.ToDoLoginPage;
import pageobjects.pages.IToDo;

public class RegisterImpl extends ToDoLoginPage implements Feature {
    private IToDo homePage;
    @Override
    public void init() {
        homePage = openToDoPage();

    }

    @Override
    public void execute() throws InterruptedException {
        homePage.switchTo_RegisterPage()
                .Verify_Register_Button()
                .Verify_Name()
                .Verify_Email()
                .Verify_Password()
                .Verify_ConfirmPassword();

    }
}
