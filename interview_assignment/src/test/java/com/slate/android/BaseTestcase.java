package com.slate.android;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.slate.api.TodoistAPI;
import com.slate.common.Timestamp;
import com.slate.driver.utils.Common;

import io.appium.java_client.android.AndroidDriver;

public class BaseTestcase {
	protected static WebDriver driver;
	protected static Common common;
//	protected static TodoistAPI todoistAPI;
	protected static Timestamp TIMESTAMP;
	protected static String TASK_ID="";
	private final String APPIUM_URL="http://localhost:4726/wd/hub";

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
		capabilities.setCapability("deviceName", "LZEILRS4T8IZJZJB");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.1");
		capabilities.setCapability("appPackage", "com.todoist");
//		capabilities.setCapability("appActivity", "com.todoist.attachment.drive.activity.TDDriveActivity");
		capabilities.setCapability("appActivity", "com.todoist.activity.HomeActivity");
				
		driver = new AndroidDriver(new URL(APPIUM_URL),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		common = new Common(driver);
//		todoistAPI = new TodoistAPI();
		TIMESTAMP = new Timestamp();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
