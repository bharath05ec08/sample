package com.slate.page;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
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
		clickElement(CONTINUE_WITH_MAIL);
		try
		{
		clickElement(NONE_OF_ABOVE);
		}catch(NoSuchElementException e)
		{}
		
		enterText(EMAIL_TEXTBOX, username);
		clickElement(CONTINUE_BUTTON);
		enterText(PASSWORD_TEXTBOX, password);
		clickElement(LOGIN_BUTTON);
	}
	
	public void movetoProject(String projectname)
	{
		movetoProjectFolder();
		clickElement(PROJECT_NAME);
	}
	
	public void movetoProjectFolder()
	{
		try
		{
		clickElement(REMIND_ME_LATER);
		}catch(NoSuchElementException e)
		{}
		clickElement(MENU);
		clickElement(PROJECTS_FOLDER);
	}
	
	public void addTask(String taskname)
	{
		clickElement(ADD_TASK_ICON);
		enterText(TASK_NAME_TEXTBOX, taskname);
		clickElement(SAVE_TASK);
		clickElement(TASK_CLOSE_BUTTON);
	}
	
	public void completeTask(String taskname)
	{
		clickElement(driver.findElement(By.xpath("//android.widget.TextView[@text='"+taskname+"']")));
		clickElement(COMPLETE_TASK_ICON);		
		checkDisplayed(TASK_COMPLETED_MESSAGE);
	}
	
	public void verifyTaskDisplayed(String taskname)
	{
		checkDisplayed(driver.findElement(By.xpath("//android.widget.TextView[@text='"+taskname+"']")));
	}
	
	public void verifyProjectDisplayed(String projectname)
	{
		checkDisplayed(driver.findElement(By.xpath("//android.widget.TextView[@text='"+projectname+"']")));
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
	
	private void checkDisplayed(WebElement element)
	{
		common.waitForElementVisible(element);
		element.isDisplayed();
	}
}
