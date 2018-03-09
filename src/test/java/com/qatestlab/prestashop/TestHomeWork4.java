package com.qatestlab.prestashop;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestHomeWork4 {

	WebDriver driver;
	String adminURL;
	String shopURL;
	String randomName;
	String randomQuan;
	String randomPrice;
	MainPage mp;
	AdminPanel ap;
	WebDriverWait wait;

	Logger log = LogManager.getLogger(TestHomeWork4.class.getName());
	GenericMethods gens = new GenericMethods(log);
	SoftAssert sa = new SoftAssert();

	@DataProvider(name = "LoginParameters")
	public Object[][] getData() {
		return new Object[][] { { "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw" } };
	}

	@BeforeClass
	@Parameters({ "browser", "adminurl", "shopurl" })
	public void setUp(String browser, String adminurl, String shopurl) {
		adminURL = adminurl;
		shopURL = shopurl;
		randomName = gens.randomName();
		randomPrice = gens.randomPrice();
		randomQuan = gens.randomQuan();
		log.info("New product values are: Name - " + randomName + ", Price - " + randomPrice + ", Quantity - "
				+ randomQuan);
		driver = gens.runBrowser(browser);
		ap = new AdminPanel(driver, log);
		mp = new MainPage(driver, log);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}

	@Test(dataProvider = "LoginParameters")
	public void loginAdmin(String email, String password) {
		log.info("Navigating to " + adminURL);
		driver.get(adminURL);
		ap.loginToAdminPanel(email, password);
	}

	@Test(dependsOnMethods = { "loginAdmin" })
	public void proceedToNewProdCreation() {
		ap.clickOnCatalogue();
		ap.clickOnNewProductBtn();
	}

	@Test(dependsOnMethods = { "proceedToNewProdCreation" })
	public void enterNewProdValues() {
		ap.newProductName(randomName);
		ap.newProductPrice(randomPrice);
		ap.newProductQuantity(randomQuan);
	}

	@Test(dependsOnMethods = { "enterNewProdValues" })
	public void finishNewProdCreation() {
		ap.clickOnSwitcher();
		ap.popUpWindowClose();
		ap.submitNewProduct();
		ap.popUpWindowClose();
	}

	@Test(dependsOnMethods = { "finishNewProdCreation" })
	public void newProdVerification() {
		log.info("Navigating to " + shopURL);
		driver.get(shopURL);
		mp.clickOnAllProducts();
		WebElement newProd = wait.until(ExpectedConditions.visibilityOf(mp.findCreatedProduct(randomName)));
		Assert.assertTrue(newProd.isDisplayed(), "New product is not found");
		mp.clickOnCreatedProd(randomName);
	}

	@Test(dependsOnMethods = { "newProdVerification" })
	public void newProdValuesVerification() {
		sa.assertTrue(mp.createdProductPrice().equalsIgnoreCase(randomPrice),
				"New prod price is not equals to " + randomPrice);

		sa.assertTrue(mp.createdProductName().equalsIgnoreCase(randomName),
				"New prod name is not equals to " + randomName);

		sa.assertTrue(mp.createdProductQuantity(randomQuan).equalsIgnoreCase(randomQuan),
				"New prod quantity is not equals to " + randomQuan);

		sa.assertAll();
		log.info("All values are correct");
	}

	@AfterClass
	public void quit() {
		driver.quit();
		log.info("Test finished/n");
	}

}
