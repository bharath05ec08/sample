package com.slate.android;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.slate.common.ReadProperties;
import com.slate.common.Timestamp;
import com.slate.driver.utils.Common;
import com.slate.page.TodoistPage;

import io.appium.java_client.android.AndroidDriver;

public class BaseTestcase {
	protected static WebDriver driver;
	protected static Common common;
	protected static TodoistPage TODOIST_PAGE;
	protected static Timestamp TIMESTAMP;
	protected static String TASK_ID ="";
	protected static String TASK_NAME = "TASK_";
	private final String APPIUM_URL = ReadProperties.ReadConfigProperties("APPIUM_URL");

	@BeforeMethod
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, ReadProperties.ReadConfigProperties("BROWSER_NAME"));
		capabilities.setCapability("deviceName", ReadProperties.ReadConfigProperties("DEVICE_NAME"));
		capabilities.setCapability("platformName", ReadProperties.ReadConfigProperties("PLATFORM_NAME"));
		capabilities.setCapability("platformVersion", ReadProperties.ReadConfigProperties("PLATFORM_VERSION"));
		capabilities.setCapability("appPackage", ReadProperties.ReadConfigProperties("APP_PACKAGE"));
		capabilities.setCapability("appActivity", ReadProperties.ReadConfigProperties("APP_ACTIVITY"));
				
//		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
//		capabilities.setCapability("deviceName", "LZEILRS4T8IZJZJB");
//		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("platformVersion", "6.1");
//		capabilities.setCapability("appPackage", "com.todoist");
//		capabilities.setCapability("appActivity", "com.todoist.activity.HomeActivity");
//				
		
		driver = new AndroidDriver(new URL(APPIUM_URL),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	
		
		common = new Common(driver);
		TODOIST_PAGE = new TodoistPage(driver, common);
		PageFactory.initElements(driver, TODOIST_PAGE);
		
		TIMESTAMP = new Timestamp();
		TASK_NAME = "TASK_"+TIMESTAMP.getTimestamp();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
}
