package com.slate.page;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.slate.driver.utils.Common;

public class TodoistPage {
	
	private WebDriver driver;
	private Common common;

	@FindBy(id = "com.todoist:id/btn_welcome_continue_with_email")
	public static WebElement CONTINUE_WITH_MAIL;
	
	@FindBy(id = "com.google.android.gms:id/cancel")
	public static WebElement NONE_OF_ABOVE;
	
	@FindBy(id = "com.todoist:id/email_exists_input")
	public static WebElement EMAIL_TEXTBOX;
	
	@FindBy(id  = "com.todoist:id/btn_continue_with_email")
	public static WebElement CONTINUE_BUTTON;
	
	@FindBy(id =  "com.todoist:id/log_in_password")
	public static WebElement PASSWORD_TEXTBOX;
	
	@FindBy(id = "com.todoist:id/btn_log_in")
	public static WebElement LOGIN_BUTTON;
	
	@FindBy(xpath = "//android.widget.Button[@text='Remind me later']")
	public static WebElement REMIND_ME_LATER;
	
	@FindBy(xpath = "//android.widget.Button[@text='OK']")
	public static WebElement OK_BUTTON;
	
	@FindBy(xpath = "//android.widget.Button[@text='Never ask']")
	public static WebElement NEVER_ASK_BUTTON;
	
	@FindBy(name = "Change the current view")
	public static WebElement MENU;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Projects']")
	public static WebElement PROJECTS_FOLDER;
	
	@FindBy(xpath = "//android.widget.TextView[@text='TEST_PROJ']")
	public static WebElement PROJECT_NAME;
	
	@FindBy(id = "com.todoist:id/fab")
	public static WebElement ADD_TASK_ICON;
	
	@FindBy(id = "android:id/message")
	public static WebElement TASK_NAME_TEXTBOX;
	
	@FindBy(id = "android:id/button1")
	public static WebElement SAVE_TASK;
	
	@FindBy(id = "com.todoist:id/action_mode_close_button")
	public static WebElement TASK_CLOSE_BUTTON;
	
	@FindBy(id = "com.todoist:id/menu_item_complete")
	public static WebElement COMPLETE_TASK_ICON;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Completed.']")
	public static WebElement TASK_COMPLETED_MESSAGE;

	public TodoistPage(WebDriver driver, Common common)
	{
		this.driver = driver;
		this.common = common;
	}
	
	public void loginTodoist(String username, String password)
	{
		try
		{
			// Following can be commented if the gapps installed correctly in the emulator
			try
			{
				clickElement(OK_BUTTON);
			}catch(NoSuchElementException e)
			{
				
			}catch(TimeoutException e)
			{
				
			}
			
			clickElement(CONTINUE_WITH_MAIL);
			
			enterText(EMAIL_TEXTBOX, username);
			clickElement(CONTINUE_BUTTON);
			enterText(PASSWORD_TEXTBOX, password);
			clickElement(LOGIN_BUTTON);
			
			// Following can be commented if the gapps installed correctly and timezone set in the emulator
			try
			{
				clickElement(NEVER_ASK_BUTTON);
				clickElement(REMIND_ME_LATER);
				clickElement(OK_BUTTON);
			}catch(NoSuchElementException e)
			{
				
			}catch(TimeoutException e)
			{
				
			}			
		}catch(Exception e)
		{
			Assert.fail("Todoist is failed in login screen with exception "+e.toString());
		}
	}
	
	public void movetoProject(String projectname)
	{
		try {
		movetoProjectFolder();
		clickElement(PROJECT_NAME);
	}catch(Exception e)
	{
		Assert.fail("Unable to move to the project "+projectname+"\t"+e.toString());
	}
	}
	
	public void movetoProjectFolder()
	{
		try {
			//Following block can be commented once the app updated with latest version
			try
			{
				clickElement(REMIND_ME_LATER);
			}catch(NoSuchElementException e)
			{
				
			}catch(TimeoutException e)
			{
				
			}
			
			clickElement(MENU);
			clickElement(PROJECTS_FOLDER);
		}catch(Exception e)
		{
			Assert.fail("Unable to move to project folder "+e.toString());
		}		
	}
	
	public void addTask(String taskname)
	{
		try {
				clickElement(ADD_TASK_ICON);
				enterText(TASK_NAME_TEXTBOX, taskname);
				clickElement(SAVE_TASK);
				clickElement(TASK_CLOSE_BUTTON);
			}catch(Exception e)
			{
				Assert.fail("Unable to move to project folder "+e.toString());
			}
	}
	
	public void completeTask(String taskname)
	{
		try {
				clickElement(driver.findElement(By.xpath("//android.widget.TextView[@text='"+taskname+"']")));
				clickElement(COMPLETE_TASK_ICON);		
				checkDisplayed(TASK_COMPLETED_MESSAGE);
			}catch(Exception e)
			{
				Assert.fail("Unable to move to project folder "+e.toString());
			}
	}
	
	public void verifyTaskDisplayed(String taskname)
	{
		try {
				checkDisplayed(driver.findElement(By.xpath("//android.widget.TextView[@text='"+taskname+"']")));
			}catch(Exception e)
			{
				Assert.fail("Task is not displayed "+e.toString());
			}
	}
	
	public void verifyProjectDisplayed(String projectname)
	{
		try {
				checkDisplayed(driver.findElement(By.xpath("//android.widget.TextView[@text='"+projectname+"']")));
			}catch(Exception e)
			{
				Assert.fail("Project is not displayed in the screen "+e.toString());
			}
	}
	
	private void clickElement(WebElement element)
	{
		common.waitForElementVisible(element);
		common.waitForElementClickable(element);
		element.click();
	}
	
	private void enterText(WebElement element, String text)
	{
		common.waitForElementVisible(element);
		common.waitForElementClickable(element);
		element.sendKeys(text);
	}
	
	private Boolean checkDisplayed(WebElement element)
	{
		common.waitForElementVisible(element);
		return element.isDisplayed();
	}
}
