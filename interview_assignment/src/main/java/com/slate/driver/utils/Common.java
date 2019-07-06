package com.slate.driver.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.slate.common.ReadProperties;

public class Common {
	private WebDriver driver;
	private final String MIN_TIME;
	private final String MAX_TIME;
	public Common(WebDriver driver)
	{
		this.driver = driver;
		MIN_TIME = ReadProperties.ReadConfigProperties("MIN_TIME");
		MAX_TIME = ReadProperties.ReadConfigProperties("MAX_TIME");
	}

	public void waitForElementClickable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(MIN_TIME));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementVisible(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(MAX_TIME));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
