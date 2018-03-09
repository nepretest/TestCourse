package com.qatestlab.prestashop;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomeWork3 {

	public static void main(String[] args) {

		// Set up
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions action = new Actions(driver);
		Logger log = LogManager.getLogger(HomeWork3.class.getName());
		AdminPanel ap = new AdminPanel(driver, log);
		GenericMethods gm = new GenericMethods(log);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String randomName = gm.randomName();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String testURL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
		String loginUrl = "webinar.test@gmail.com";
		String password = "Xcg7299bnSmMuRLp9ITw";

		gm.getURL(driver, testURL);
		wait.until(ExpectedConditions.urlContains("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/"));

		// Login
		ap.loginToAdminPanel(loginUrl, password);

		// Adding category
		action.moveToElement(ap.mouseHoverOnCatalogue()).perform();
		action.moveToElement(ap.mouseHoverOnCategories()).perform();
		ap.clickOnCategories();
		ap.clickOnAddCategory();
		ap.enterCategoryName(randomName);
		ap.clickSaveCategory();
		
		// Verification
		Assert.assertTrue(ap.findConfirmAlert().isDisplayed(), "Confirmation alert is not displayed");
		log.info("Confirmation alert is found");
		ap.filterCategoriesByName(randomName);
		Assert.assertTrue(ap.findCreatedCategory(randomName).isDisplayed(), "Created category is not found");
		log.info("Created category is found");

		gm.endTest(driver);

	}

}
