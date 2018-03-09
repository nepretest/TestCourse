package com.qatestlab.prestashop;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeWork2_Script2 {

	public static void main(String[] args) {

		// Set up
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		Logger log = LogManager.getLogger(HomeWork2_Script2.class.getName());
		GenericMethods gens = new GenericMethods(log);
		WebDriver driver = new ChromeDriver();
		AdminPanel ap = new AdminPanel(driver, log);
		driver.manage().window().maximize();
		String testURL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
		String loginUrl = "webinar.test@gmail.com";
		String password = "Xcg7299bnSmMuRLp9ITw";
		driver.get(testURL);
		
		// Logging in
		gens.sleepWait(1000);
		ap.loginToAdminPanel(loginUrl, password);
		
		// Finding elements
		gens.sleepWait(1000);
		WebElement navMenu = driver.findElement(By.id("nav-sidebar"));
		List<WebElement> allElements = navMenu.findElements(By.tagName("a"));
		List<String> elements = new ArrayList<String>();

		for (WebElement e : allElements) {
			if (e.isDisplayed()) {
				elements.add(e.getText());
			}
		}

		log.info(elements + "\n");

		for (String e : elements) {
			driver.findElement(By.linkText(e)).click();
			gens.sleepWait(1000);

			String title1 = driver.getTitle();
			log.info(title1);

			driver.navigate().refresh();
			gens.sleepWait(1000);

			String title2 = driver.getTitle();

			if (title2.equals(title1)) {
				log.info("Page refresh is successful\n");
			} else {
				log.error("Page refresh failure, expected title is " + title1 + " but actual title is " + title2 + "\n");
			}
		}

		log.info("Test finished");
		
		driver.quit();
	}
}
