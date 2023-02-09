package BaseClasses;

import pageobjects.PageFactoryClasses.ToDoPageFactory;
import pageobjects.PageFactoryClasses.ToDoPageFactory;
import pageobjects.pages.IToDo;

import static com.codeborne.selenide.Selenide.open;
import static data.SystemProperties.ToDo_URL;

public class ToDoLoginPage {



    public IToDo openToDoPage()
    {
        open(ToDo_URL);

        return ToDoPageFactory.getHomepage();
    }


}
