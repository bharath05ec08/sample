package com.slate.android;

import java.net.URL;
import java.util.concurrent.TimeUnit;

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
	protected static String USER_NAME = ReadProperties.ReadConfigProperties("USER_NAME");
	protected static String PASSWORD = ReadProperties.ReadConfigProperties("PASSWORD");
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
		
		driver = new AndroidDriver(new URL(APPIUM_URL),capabilities);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(ReadProperties.ReadConfigProperties("MIN_TIME")), TimeUnit.SECONDS);
			
		common = new Common(driver);
		TODOIST_PAGE = new TodoistPage(driver, common);
		PageFactory.initElements(driver, TODOIST_PAGE);
		
		TIMESTAMP = new Timestamp();
		TASK_NAME = "TASK_"+TIMESTAMP.getTimestamp();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		TODOIST_PAGE.logout();
		driver.quit();
	}
}
