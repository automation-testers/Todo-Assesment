package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.C;
import pageobjects.PageFactoryClasses.ToDoPageFactory;

import static com.codeborne.selenide.Selenide.$x;
import static pageobjects.PageFactoryClasses.ToDoPageFactory.getRegister;

public class Register implements IRegister {
    SelenideElement RegisterButton = $x("//a[@role='button'][normalize-space()='Register']");
    SelenideElement Name = $x("//div/input[@id='user_name']");
    SelenideElement Email = $x("//div/input[@id='user_email']");
    SelenideElement Password = $x("//div/input[@id='user_password']");
    SelenideElement Confirm_Password = $x("//div/input[@id='user_password_confirmation']");
    SelenideElement RegisterBttn = $x("//input[@name='commit']");

    @Override
    public IRegister switchTo_RegisterPage() {

        return ToDoPageFactory.getRegister();
    }

    @Override
    public IRegister Verify_Register_Button() throws InterruptedException {
        RegisterButton.click();
        //Assertions on RegButton
       // RegisterButton.shouldHave(Condition.visible);
        Thread.sleep(4000);

        return ToDoPageFactory.getRegister();
    }

    @Override
    public IRegister Verify_Name() {
        Name.setValue("Gyandeep");
        //Assertion on Name Field
        Name.shouldHave(Condition.enabled);

        return ToDoPageFactory.getRegister();
    }

    @Override
    public IRegister Verify_Email() {
        Email.setValue("Gyandeep123@gmail.com");
        //Assertion on Email
        Email.shouldHave(Condition.enabled);

        return ToDoPageFactory.getRegister();
    }

    @Override
    public IRegister Verify_Password() {
        Password.setValue("abcd123@");
        //Assertion on Password Field
        Password.shouldHave(Condition.enabled);

        return ToDoPageFactory.getRegister();
    }

    @Override
    public IRegister Verify_ConfirmPassword() throws InterruptedException {
        Confirm_Password.setValue("abcd123@");
        //Assertion on ConfirmPassword
        Confirm_Password.shouldHave(Condition.enabled);

        //Assertion on RegButton
        RegisterBttn.shouldHave(Condition.visible);
        RegisterBttn.shouldHave(Condition.exactValue("Register"));
        RegisterBttn.click();
        Thread.sleep(4000);

        return ToDoPageFactory.getRegister();
    }
}
