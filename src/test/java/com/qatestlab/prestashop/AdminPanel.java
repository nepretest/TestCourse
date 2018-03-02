package com.qatestlab.prestashop;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AdminPanel {
	
	WebDriver driver;
	Actions act;
	Logger log;
	
	public AdminPanel(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}
	
	// Login Object
	public void loginAdminPanel(String email, String password) {
		emailField(email);
		passwordField(password);
		clickOnSubmitLogin();
	}
	
	public void emailField(String email) {
		log.info("Entering email :" + email);
		WebElement element = driver.findElement(By.id("email"));
		element.sendKeys(email);
	}
	
	public void passwordField(String password) {
		log.info("Entering password :" + password);
		WebElement element = driver.findElement(By.id("passwd"));
		element.sendKeys(password);
	}
	
	public void clickOnSubmitLogin() {
		log.info("Clicking on submit login btn");
		WebElement element = driver.findElement(By.name("submitLogin"));
		element.click();
		log.info("Submit login bnt is clicked");
	}
	
//	public void clickOnCatalogue() {
//		log.info("Clicking on Catalogue");
//		WebElement element = driver.findElement(By.xpath("//li[@id='subtab-AdminCatalog']/a"));
//		element.click();
//		log.info("Catalogue is clicked");
//		
//	}

	public void clickOnCategories() {
		log.info("Clicking on Categories");
		WebElement element = driver.findElement(By.xpath("//li[@id='subtab-AdminCategories']/a"));
		element.click();
		log.info("Categories is clicked");
	}
	
	public void clickOnAddCategory() {
		log.info("Clicking on Add Category btn");
		WebElement element = driver.findElement(By.id("page-header-desc-category-new_category"));
		element.click();
		log.info("Add Category bnt is clicked");
	}
	
	public void enterCategoryName(String name) {
		log.info("Entering new category name");
		WebElement element = driver.findElement(By.id("name_1"));
		element.sendKeys(name);
	}
	
	public void clickSaveCategory() {
		log.info("Clicking on Save Category btn");
		WebElement element = driver.findElement(By.id("category_form_submit_btn"));
		element.click();
		log.info("Save Category bnt is clicked");
	}
	
	public WebElement findConfirmAlert() {
		log.info("Finding confirmation alert");
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
		return element;
		
	}
	
	public void clickOnFilterCategoriesByName() {
		log.info("Filter category list by name");
		WebElement element = driver.findElement(By.xpath("//tr[@class='nodrag nodrop']/th[3]/span/a[1]"));
		element.click();
		log.info("Filter bnt is clicked");
	}
	
	public WebElement findCreatedCategory(String name) {
		log.info("Finding created category");
		WebElement element = driver.findElement(By.xpath("//td[contains(text(),'" + name + "')]"));
		return element;
	}
	
	

}
