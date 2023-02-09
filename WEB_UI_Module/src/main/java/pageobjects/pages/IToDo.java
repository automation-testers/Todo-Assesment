package pageobjects.pages;

public interface IToDo
{
    IToDo Verify_SignIn_Button() throws InterruptedException;
    IToDo Verify_MyTask();
    IToDo Verify_AddTask() throws InterruptedException;
    IToDo Verify_AddTask_With_return_key() throws InterruptedException;
    IToDo Verify_Entering_lessThan_3_Character_inTask() throws InterruptedException;
    IToDo Verify_Entering_moreThan_250_Character_inTask() throws InterruptedException;
    IToDo Verify_Manage_Subtask_Button() throws InterruptedException;
    IToDo Verify_Manage_Subtask_FormValidation() throws InterruptedException;

    IToDo Verify_Remove_Button();
    IToDo Verify_Manage_Subtask_Description() throws InterruptedException;

    IToDo Verify_popup_Modal() throws InterruptedException;
    IToDo Verify_Remove_SubTask_Button();
    IToDo Verify_ToDo_Close_Button();
    IRegister switchTo_RegisterPage();



}
