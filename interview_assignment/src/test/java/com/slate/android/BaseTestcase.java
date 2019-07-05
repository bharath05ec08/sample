package com.slate.android;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class BaseTestcase {
	protected WebDriver driver;
	private final String APPIUM_URL="http://127.0.0.1:4723/wd/hub";

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
		capabilities.setCapability("deviceName", "LZEILRS4T8IZJZJB");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("appPackage", "com.todoist");
		capabilities.setCapability("appActivity", "com.todoist.attachment.drive.activity.TDDriveActivity");
		
				driver = new AndroidDriver(new URL(APPIUM_URL),capabilities);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
