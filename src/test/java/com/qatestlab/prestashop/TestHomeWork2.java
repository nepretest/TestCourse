package com.qatestlab.prestashop;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestHomeWork2 {

	public static void main(String[] args) throws InterruptedException {

		// Set up
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		WebDriver driver;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String testURL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";

		// Script A
		driver.navigate().to(testURL);
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("webinar.test@gmail.com");

		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("Xcg7299bnSmMuRLp9ITw");

		WebElement loginBtn = driver.findElement(By.name("submitLogin"));
		loginBtn.click();
		Thread.sleep(1000);

		// Script B
		WebElement navMenu = driver.findElement(By.id("nav-sidebar"));
		List<WebElement> allElements = navMenu.findElements(By.tagName("a"));
		List<String> elements = new ArrayList<String>();

		for (WebElement e : allElements) {
			if (e.isDisplayed()) {
				elements.add(e.getText());
			}
		}

		System.out.println(elements + "\n");

		for (String e : elements) {
			driver.findElement(By.linkText(e)).click();
			Thread.sleep(1000);

			String title1 = driver.getTitle();
			System.out.println(title1);

			driver.navigate().refresh();
			Thread.sleep(1000);

			String title2 = driver.getTitle();

			if (title2.equals(title1)) {
				System.out.println("Page refresh is successful\n");
			} else {
				System.out.println(
						"Page refresh failure, expected title is " + title1 + " but actual title is " + title2 + "\n");
			}
		}

		System.out.println("Test finished");
	}
}
