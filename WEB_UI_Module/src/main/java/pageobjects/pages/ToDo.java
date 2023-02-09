package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageobjects.PageFactoryClasses.ToDoPageFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$x;

public class ToDo implements IToDo
{
    SoftAssert Assert = new SoftAssert();
    SelenideElement SignInButton = $x("//a[@role='button'][normalize-space()='Sign In']");
    SelenideElement MyTask_Link = $x("//a[@id='my_task']");
    SelenideElement Email_Input = $x("//div/input[@id='user_email']");
    SelenideElement Password = $x("//div/Input[@id='user_password']");
    SelenideElement SignIn_Submit_Btn = $x("//input[@name='commit']");
    SelenideElement Users_Name_Heading = $x("/html/body/div[1]/h1");
    SelenideElement Add_task_input = $x("//div/input[@id='new_task']");
    SelenideElement Add_tak_Button = $x("//span[@class='input-group-addon glyphicon glyphicon-plus']");
    SelenideElement ToDo_Text = $x("/html/body/div[1]/div[2]/div[2]/div[2]/table/tbody");
    SelenideElement Manage_Subtask = $x("//tbody/tr[1]/td[4]/button[1]");
    SelenideElement SubTask_Description = $x("//*[@id='new_sub_task']");

    SelenideElement SubTask_List = $x("/html/body/div[4]/div/div/div[2]/div[2]/table/tbody");
    SelenideElement Due_Date = $x("//*[@id='dueDate']");
    SelenideElement Add_SubTask_Button = $x("//*[@id='add-subtask']");
    SelenideElement Remove_Task_Button = $x("//tbody/tr[1]/td[5]/button[1]");
    SelenideElement Remove_SubTask_Button = $x("button[ng-click='removeSubTask(sub_task)']");
    SelenideElement ToDo_Close_Button = $x("//button[normalize-space()='Close']");
    SelenideElement ToDo_SubTaskText = $x("//input[@id='new_sub_task']");
    SelenideElement Added_SubtaskText = $x("//td[@class='task_body col-md-8 limit-word-size']");
    SelenideElement TaskID = $x("//h3[@class='modal-title ng-binding']");
    SelenideElement TaskDescriptionInModal = $x("//*[@id=\"edit_task\"]");
    SelenideElement ClosePopup = $x("//button[normalize-space()='Close']");



    public String GetRandomString(int Length)
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = Length;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Date GetFutureDate() {
        return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
    }

    @Override
    public IToDo Verify_SignIn_Button() throws InterruptedException {
        SignInButton.click();
        Email_Input.setValue("Username");
        Password.setValue("Password");
        SignIn_Submit_Btn.click();
        Thread.sleep(4000);
        return ToDoPageFactory.getHomepage();
    }

    @Override
    //The user should always see the ‘My Tasks’ link on the NavBar
    public IToDo Verify_MyTask() {
        MyTask_Link.shouldBe(Condition.exactText("My Tasks"));
        MyTask_Link.click();
        //Assertions on Link
        MyTask_Link.shouldBe(Condition.visible);
        String Expected_A = MyTask_Link.getText();
        String Actual_A = "My Tasks";
        Assert.assertEquals(Expected_A, Actual_A);
        System.out.println("Passed");

//The user should see a message on the top part saying that list belongs to the logged user

        Users_Name_Heading.shouldHave(Condition.exactText("Gyanadeep Sahoo 's ToDo List"));
        String Actual_Heading = Users_Name_Heading.getText();
        String Expected_Heading = "Gyanadeep Sahoo 's ToDo List";
        Assert.assertEquals(Expected_Heading, Actual_Heading);
        System.out.println("Passed");

        return ToDoPageFactory.getHomepage();
    }

    @Override
    //The user should be able to enter a new task by clicking on the add task button.
    public IToDo Verify_AddTask() throws InterruptedException {
        String ActualToDoResult = GetRandomString(10);
        Add_task_input.setValue(ActualToDoResult);

        //Assertion on Add Task Input Field
        Add_task_input.shouldBe(Condition.enabled);
        Thread.sleep(4000);
        Add_tak_Button.click();

        //Assertion for Adding task by Clicking on + button

        String ExpectedToDoResult = ToDo_Text.find(By.cssSelector( ".ng-binding")).getText();

        Assert.assertEquals(ExpectedToDoResult, ActualToDoResult);
        System.out.println("Test Case Passed");
        Add_task_input.clear();

        return ToDoPageFactory.getHomepage();
    }

    @Override
    //The user should be able to enter a new task by hitting enter.
    public IToDo Verify_AddTask_With_return_key() throws InterruptedException {

        String ActualToDoResult = GetRandomString(10);
        //Assertion for Adding task by pressing Enter Key
        Thread.sleep(6000);
        Add_task_input.setValue(ActualToDoResult);
        new Actions(WebDriverRunner.getWebDriver()).moveToElement(Add_task_input).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(4000);

        String ExpectedToDoValue = ToDo_Text.find(By.cssSelector( ".ng-binding")).getText();
        Assert.assertEquals(ExpectedToDoValue, ActualToDoResult);
        System.out.println("Test Case Passed");


        return ToDoPageFactory.getHomepage();
    }

    //Assertion on Entering less than 3 Characters in Task
    @Override
    public IToDo Verify_Entering_lessThan_3_Character_inTask() throws InterruptedException {
        String ActualToDoResult =GetRandomString(2);
        Add_task_input.setValue(ActualToDoResult);
        Thread.sleep(4000);
        Add_tak_Button.click();
        String ExpectedToDoValue = ToDo_Text.find(By.cssSelector( ".ng-binding")).getText();
        Assert.assertTrue(ExpectedToDoValue.length()>=3);
        return ToDoPageFactory.getHomepage();
    }

    //Assertion on Entering more than 250 Characters in Task
    @Override
    public IToDo Verify_Entering_moreThan_250_Character_inTask() throws InterruptedException {

        // Length greater than 251
        String ActualToDoResult =GetRandomString(251);
        Add_task_input.setValue(ActualToDoResult);
        Thread.sleep(4000);
        Add_tak_Button.click();
        String  ExpectedToDoValue= ToDo_Text.find(By.cssSelector( ".ng-binding")).getText();
        Assert.assertFalse(ExpectedToDoValue.length()>250);

        // Length =250
        ActualToDoResult =GetRandomString(250);  Add_task_input.setValue(ActualToDoResult);
        Thread.sleep(4000);
        Add_tak_Button.click();
        ExpectedToDoValue= ToDo_Text.find(By.cssSelector( ".ng-binding")).getText();
        Assert.assertEquals(ExpectedToDoValue,ActualToDoResult);

        // Length =249
        ActualToDoResult =GetRandomString(249);
        Thread.sleep(4000);
        Add_tak_Button.click();
        ExpectedToDoValue= ToDo_Text.find(By.cssSelector( ".ng-binding")).getText();
        Assert.assertEquals(ExpectedToDoValue,ActualToDoResult);

        return ToDoPageFactory.getHomepage();
    }


    //  ----------------- User Story 2 ---------------------------------------------------

    @Override
    public IToDo Verify_Manage_Subtask_Button() throws InterruptedException {
        Thread.sleep(4000);
        String ButtonText=Manage_Subtask.getText();

        Assert.assertTrue(ButtonText.contains("Manage Subtasks"));


        Manage_Subtask.click();
        Set<String> Windows = WebDriverRunner.getWebDriver().getWindowHandles();//Switch window to ManageTask.
        Iterator<String> iterator = Windows.iterator();
        String subWindowsHandler = null;
        while (iterator.hasNext()) {
            subWindowsHandler = iterator.next();
        }
        WebDriverRunner.getWebDriver().switchTo().window(subWindowsHandler);
        Thread.sleep(4000);
        //Assertion on SubTask
        int ActualSubtaskCount= SubTask_List.findAll(By.tagName("tr")).size();
        Assert.assertTrue(ButtonText.contains(ActualSubtaskCount+""));
        ClosePopup.click();
        System.out.println("Test Case Passed");
        return ToDoPageFactory.getHomepage();
    }

    @Override
    //This popup should have a read-only field with the task ID and the task description
    public IToDo Verify_popup_Modal() throws InterruptedException {
        //Assertion on button labeled as ‘Manage Subtasks’
        Manage_Subtask.shouldHave(Condition.visible);
        Manage_Subtask.click();


        Set<String> Windows = WebDriverRunner.getWebDriver().getWindowHandles();//Switch window to ManageTask.
        Iterator<String> iterator = Windows.iterator();
        String subWindowsHandler = null;
        while (iterator.hasNext()) {
            subWindowsHandler = iterator.next();
        }
        WebDriverRunner.getWebDriver().switchTo().window(subWindowsHandler);
        Thread.sleep(4000);
        //Assertion on SubTask
        TaskID.is(Condition.disabled);
        TaskDescriptionInModal.is(Condition.not(Condition.enabled));

        ClosePopup.click();
        return ToDoPageFactory.getHomepage();
    }

    @Override
    public IToDo Verify_Manage_Subtask_Description() throws InterruptedException {
        Thread.sleep(4000);
        Manage_Subtask.click();
        Set<String> Windows = WebDriverRunner.getWebDriver().getWindowHandles();//Switch window to ManageTask.
        Iterator<String> iterator = Windows.iterator();
        String subWindowsHandler = null;
        while (iterator.hasNext()) {
            subWindowsHandler = iterator.next();
        }
        WebDriverRunner.getWebDriver().switchTo().window(subWindowsHandler);
        Thread.sleep(4000);
        //Assertion on SubTask
        SubTask_Description.shouldBe(Condition.visible);
        Due_Date.shouldBe(Condition.visible);

        Add_SubTask_Button.shouldHave(Condition.visible);
        String ActualToDoResult = GetRandomString(250 );

        SubTask_Description.setValue(ActualToDoResult);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Due_Date.setValue(dateFormat.format(GetFutureDate()));

        Add_SubTask_Button.click();

        Thread.sleep(4000);

        //Assertion on Adding Subtask
        String ExpectedToDoResult1= Added_SubtaskText.getText();
        Assert.assertEquals(ExpectedToDoResult1, ActualToDoResult);
        System.out.println("Test Case Passed");
        ClosePopup.click();
        Thread.sleep(4000);

        return ToDoPageFactory.getHomepage();
    }

    @Override
    public IToDo Verify_Remove_Button() {
        Remove_Task_Button.click();
        //Assertion on Remove Button
        Remove_Task_Button.shouldBe(Condition.visible);
        Remove_Task_Button.shouldHave(Condition.exactText("Remove"));
        return ToDoPageFactory.getHomepage();
    }

    @Override
    public IToDo Verify_Manage_Subtask_FormValidation() throws InterruptedException {

        Manage_Subtask.click();
        Set<String> Windows = WebDriverRunner.getWebDriver().getWindowHandles();//Switch window to ManageTask.
        Iterator<String> iterator = Windows.iterator();
        String subWindowsHandler = null;
        while (iterator.hasNext()) {
            subWindowsHandler = iterator.next();
        }
        WebDriverRunner.getWebDriver().switchTo().window(subWindowsHandler);
        Thread.sleep(4000);
        //Assertion on SubTask
        SubTask_Description.shouldBe(Condition.visible);
        Due_Date.shouldBe(Condition.visible);

        Add_SubTask_Button.shouldHave(Condition.visible);
        SubTask_Description.setValue("");
        Due_Date.setValue("");
        int ActualSubtaskCount= SubTask_List.findAll(By.tagName("tr")).size();
        Add_SubTask_Button.click();
        Thread.sleep(4000);

        //Assertion on Adding Subtask
        int ExpectedSubtaskCount=  SubTask_List.findAll(By.tagName("tr")).size();

        Assert.assertEquals(ExpectedSubtaskCount, ActualSubtaskCount);
        System.out.println("Test Case Passed");
        Thread.sleep(4000);
        return ToDoPageFactory.getHomepage();
    }

    @Override
    public IToDo Verify_Remove_SubTask_Button() {
        Remove_SubTask_Button.click();
        //Assertion on Subtask Remove Button
        Remove_SubTask_Button.shouldHave(Condition.visible);
        Remove_SubTask_Button.shouldHave(Condition.exactText("Remove SubTask"));


        return ToDoPageFactory.getHomepage();
    }

    @Override
    public IToDo Verify_ToDo_Close_Button() {
        ToDo_Close_Button.click();
        //Assertion on Close Button
        ToDo_Close_Button.shouldHave(Condition.visible);
        ToDo_Close_Button.shouldHave(Condition.exactValue("Close"));
        return ToDoPageFactory.getHomepage();
    }

    @Override
    public IRegister switchTo_RegisterPage() {

        return ToDoPageFactory.getRegister();
    }


}
