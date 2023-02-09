package ImplementationClasses;

import BaseClasses.Feature;
import BaseClasses.ToDoLoginPage;
import pageobjects.pages.IToDo;

public class ToDoImpl extends ToDoLoginPage implements Feature
{
   private IToDo homePage;
    @Override
    public void init()
    {

     homePage = openToDoPage();
    }

    @Override
    public void execute() {
     try {

      homePage.Verify_SignIn_Button()
              .Verify_MyTask()
              .Verify_AddTask()
              .Verify_AddTask_With_return_key()
              .Verify_Entering_lessThan_3_Character_inTask()
              .Verify_Entering_moreThan_250_Character_inTask()
              .Verify_Manage_Subtask_Button()
              .Verify_popup_Modal()
              .Verify_Manage_Subtask_Description()
              .Verify_Manage_Subtask_FormValidation();

     } catch (Exception exception) {
      exception.printStackTrace();
     }
    }
}
