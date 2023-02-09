package pageobjects.PageFactoryClasses;


//import org.jetbrains.annotations.NotNull;
import pageobjects.pages.*;


public class ToDoPageFactory extends PageFactory
{

    public static IToDo getHomepage()
    {

        return wrap(new ToDo(), IToDo.class);
    }
    public static IRegister getRegister()
    {

        return wrap(new Register(), IRegister.class);
    }



}
