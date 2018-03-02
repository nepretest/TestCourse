package com.qatestlab.prestashop;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class TestHomeWork3 {

	public static void main(String[] args) {
		
				// Set up
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				Actions action = new Actions(driver);
				Logger log = LogManager.getLogger(TestHomeWork3.class.getName());
				AdminPanel ap = new AdminPanel(driver, log);
				
				GenericMethods genMeth = new GenericMethods(driver);
				String randName = genMeth.randomName();
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				String testURL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
				String loginUrl = "webinar.test@gmail.com";
				String password = "Xcg7299bnSmMuRLp9ITw";
				
				log.info("Navigating to " + testURL);
				driver.get(testURL);
				
				// Login
				ap.emailField(loginUrl);
				ap.passwordField(password);
				ap.clickOnSubmitLogin();
				
				// Adding category
				genMeth.moveToElementAct(action, "xpath", "//li[@id='subtab-AdminCatalog']");
				genMeth.moveToElementAct(action, "xpath", "//li[@id='subtab-AdminCategories']");
				ap.clickOnCategories();
				ap.clickOnAddCategory();
				ap.enterCategoryName(randName);
				ap.clickSaveCategory();
				
				Assert.assertTrue(ap.findConfirmAlert().isDisplayed());
				log.info("Confirmation alert is found");
				try {
				ap.clickOnFilterCategoriesByName();
				} finally {
				Assert.assertTrue(ap.findCreatedCategory(randName).isDisplayed());
				log.info("Created category is found");
				}
				
				log.info("Test finished\n");
				
			driver.quit();	
				
	}

}
