package com.qatestlab.prestashop;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class AdminPanel {

	WebDriver driver;
	Logger log;

	public AdminPanel(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// Login Object
	public void loginToAdminPanel(String email, String password) {
		emailField(email);
		passwordField(password);
		clickOnSubmitLogin();
	}

	public void emailField(String email) {
		Reporter.log("Entering email :" + email);
		log.info("Entering email :" + email);
		WebElement element = driver.findElement(By.name("email"));
		element.sendKeys(email);
	}

	public void passwordField(String password) {
		Reporter.log("Entering password :" + password);
		log.info("Entering password :" + password);
		WebElement element = driver.findElement(By.id("passwd"));
		element.sendKeys(password);
	}

	public void clickOnSubmitLogin() {
		Reporter.log("Clicking on submit login btn");
		log.info("Clicking on submit login btn");
		WebElement element = driver.findElement(By.name("submitLogin"));
		element.click();
		log.info("Submit login bnt is clicked");
	}

	public void clickOnCatalogue() {
		Reporter.log("Clicking on Catalogue");
		log.info("Clicking on Catalogue");
		WebElement element = driver.findElement(By.xpath("//li[@id='subtab-AdminCatalog']/a"));
		element.click();
		log.info("Catalogue is clicked");
	}

	public WebElement mouseHoverOnCatalogue() {
		Reporter.log("Hovering mouse over the catalogue");
		log.info("Hovering mouse over the catalogue");
		WebElement element = driver.findElement(By.xpath("//li[@id='subtab-AdminCatalog']"));
		return element;
	}

	public WebElement mouseHoverOnCategories() {
		Reporter.log("Hovering mouse over the categories");
		log.info("Hovering mouse over the categories");
		WebElement element = driver.findElement(By.xpath("//li[@id='subtab-AdminCatalog']"));
		return element;
	}

	public void clickOnCategories() {
		Reporter.log("Clicking on Categories");
		log.info("Clicking on Categories");
		WebElement element = driver.findElement(By.xpath("//li[@id='subtab-AdminCategories']"));
		element.click();
		log.info("Categories is clicked");
	}

	public void clickOnAddCategory() {
		Reporter.log("Clicking on Add Category btn");
		log.info("Clicking on Add Category btn");
		WebElement element = driver.findElement(By.id("page-header-desc-category-new_category"));
		element.click();
		log.info("Add Category bnt is clicked");
	}

	public void enterCategoryName(String name) {
		Reporter.log("Entering new category name");
		log.info("Entering new category name");
		WebElement element = driver.findElement(By.id("name_1"));
		element.sendKeys(name);
	}

	public void clickSaveCategory() {
		Reporter.log("Clicking on Save Category btn");
		log.info("Clicking on Save Category btn");
		WebElement element = driver.findElement(By.id("category_form_submit_btn"));
		element.click();
		log.info("Save Category bnt is clicked");
	}

	public WebElement findConfirmAlert() {
		Reporter.log("Finding confirmation alert");
		log.info("Finding confirmation alert");
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
		return element;

	}

	public void filterCategoriesByName(String name) {
		Reporter.log("Filter the list of elements by name - " + name);
		log.info("Filter the list of elements by name - " + name);
		WebElement element = driver.findElement(By.name("categoryFilter_name"));
		element.sendKeys(name);
		element.sendKeys(Keys.ENTER);
	}

	public WebElement findCreatedCategory(String name) {
		Reporter.log("Finding created category");
		log.info("Finding created category");
		WebElement element = driver.findElement(By.xpath("//td[contains(text(),'" + name + "')]"));
		log.info("Created category found");
		return element;
	}

	public void clickOnNewProductBtn() {
		Reporter.log("Clicking on new prod btn");
		log.info("Clicking on new prod btn");
		WebElement element = driver.findElement(By.id("page-header-desc-configuration-add"));
		element.click();
		log.info("New prod btn is clicked");
	}

	public void newProductName(String newName) {
		Reporter.log("Entering " + newName + " to prod name");
		log.info("Entering " + newName + " to prod name");
		WebElement element = driver.findElement(By.id("form_step1_name_1"));
		element.clear();
		element.sendKeys(newName);
	}

	public void newProductQuantity(String quantity) {
		Reporter.log("Entering " + quantity + " to prod quantity");
		log.info("Entering " + quantity + " to prod quantity");
		WebElement element = driver.findElement(By.id("form_step1_qty_0_shortcut"));
		element.clear();
		element.sendKeys(quantity);
	}

	public void newProductPrice(String price) {
		Reporter.log("Entering " + price + " to prod price");
		log.info("Entering " + price + " to prod price");
		WebElement element = driver.findElement(By.id("form_step1_price_shortcut"));
		element.clear();
		element.sendKeys(price);
	}

	public void clickOnSwitcher() {
		Reporter.log("Clicking on switcher");
		log.info("Clicking on switcher");
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'switch-input')]"));
		element.click();
		log.info("Swither is clicked");
	}

	public void popUpWindowClose() {
		Reporter.log("Closing the pop up window");
		log.info("Closing the pop up window");
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'growl-close')]"));
		element.click();
		log.info("Pop up window is closed");
	}

	public void submitNewProduct() {
		Reporter.log("Clicking on submit new prod btn");
		log.info("Clicking on submit new prod btn");
		WebElement element = driver.findElement(By.id("submit"));
		element.click();
		log.info("Submit new prod btn is clicked");
	}

}
