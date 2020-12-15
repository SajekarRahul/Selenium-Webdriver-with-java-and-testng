package com.herokuapp.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class PositiveTests {

	@Test
	public void loginTests() throws InterruptedException {

		System.out.println("Login Test Starting......");

//		create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// maximize the browser
		driver.manage().window().maximize();

//		open web page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);

		System.out.println("Page Opened");

		// find username textbox by its id and enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// find password textbox by it's name and enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		// find login button by tagName and click
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		Thread.sleep(2000);

//		Authentication

//		check new url
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		Assert.assertEquals(expectedUrl, actualUrl, "Expected Url is not same as Actual Url");

//		welcome message
		WebElement message = driver.findElement(By.id("flash"));
		String actualMessage = message.getText();
		String expectedMessage = "You logged into a secure area!";
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not same as Expected");

//		logout button visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout Button is not Visible");

		// quit browser
		driver.quit();

	}

}
