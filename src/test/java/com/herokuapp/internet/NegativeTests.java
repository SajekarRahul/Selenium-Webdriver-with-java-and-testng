package com.herokuapp.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NegativeTests {

	private WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void setup() {
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();

		// maximize the browser
		driver.manage().window().maximize();

		// open web page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
	}

	@Test(priority = 1) // lower priority tests schedule first.
	public void IncorrectUsername() throws InterruptedException {

		// find username textbox by its id and enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmithIncorrect");

		// find password textbox by it's name and enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		// find login button by tagName and click
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		Thread.sleep(2000);

		// Authentication
		// find message
		WebElement message = driver.findElement(By.id("flash"));
		String actualMessage = message.getText();
		String expectedMessage = "Your username is invalid!";
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual Message is not same as Expected Message");

	}

	@Test(priority = 2, enabled = false) // Disabled the test
	public void IncorrectPassword() throws InterruptedException {

		// find username textbox by its id and enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// find password textbox by it's name and enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("IncorrectPassword!");

		// find login button by tagName and click
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		Thread.sleep(2000);

		// Authentication
		// find message
		WebElement message = driver.findElement(By.id("flash"));
		String actualMessage = message.getText();
		String expectedMessage = "Your password is invalid!";
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual Message is not same as Expected Message");

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// Close Browser
		driver.quit();
	}

}
