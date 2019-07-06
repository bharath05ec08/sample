package com.slate.android;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.openqa.selenium.By;

public class Todoist extends BaseTestcase{
	
	//android.widget.TextView[text()='bharath05ec08@gmail.com']
		
/*


com.todoist:id/log_in_password
com.todoist:id/btn_log_in

*/	
	@Test
	public void createProject() throws Exception {		
		common.waitForElementVisible(driver.findElement(By.id("com.todoist:id/btn_welcome_continue_with_email")));
		driver.findElement(By.id("com.todoist:id/btn_welcome_continue_with_email")).click();
		try {
		common.waitForElementVisible(driver.findElement(By.id("com.google.android.gms:id/cancel")));
		driver.findElement(By.id("com.google.android.gms:id/cancel")).click();
	}catch(NoSuchElementException e)
	{}
		common.waitForElementVisible(driver.findElement(By.id("com.todoist:id/email_exists_input")));
		driver.findElement(By.id("com.todoist:id/email_exists_input")).sendKeys("bharath05ec08@gmail.com");;
		
		common.waitForElementVisible(driver.findElement(By.id("com.todoist:id/btn_continue_with_email")));
		driver.findElement(By.id("com.todoist:id/btn_continue_with_email")).click();
		
		common.waitForElementVisible(driver.findElement(By.id("com.todoist:id/log_in_password")));
		driver.findElement(By.id("com.todoist:id/log_in_password")).sendKeys("sudhahar");
		
		common.waitForElementVisible(driver.findElement(By.id("com.todoist:id/btn_log_in")));
		driver.findElement(By.id("com.todoist:id/btn_log_in")).click();
		try {
		common.waitForElementVisible(driver.findElement(By.xpath("//android.widget.Button[@text='Remind me later']")));
		driver.findElement(By.xpath("//android.widget.Button[@text='Remind me later']")).click();
		}catch(NoSuchElementException e)
		{}
		common.waitForElementVisible(driver.findElement(By.name("Change the current view")));
		driver.findElement(By.name("Change the current view")).click();		

		common.waitForElementVisible(driver.findElement(By.xpath("//android.widget.TextView[@text='Projects']")));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Projects']")).click();
		
		common.waitForElementVisible(driver.findElement(By.xpath("//android.widget.TextView[@text='TEST_PROJ']")));
		driver.findElement(By.xpath("//android.widget.TextView[@text='TEST_PROJ']")).isDisplayed();
		
		Thread.sleep(5000);
	}

//	@Test
//	public void createTask() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void reopenTask() {
//		fail("Not yet implemented");
//	}

}
