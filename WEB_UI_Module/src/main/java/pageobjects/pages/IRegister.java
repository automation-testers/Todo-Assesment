package pageobjects.pages;

public interface IRegister {
    IRegister switchTo_RegisterPage();
    IRegister Verify_Register_Button() throws InterruptedException;
    IRegister Verify_Name();
    IRegister Verify_Email();
    IRegister Verify_Password();
    IRegister Verify_ConfirmPassword() throws InterruptedException;
}
