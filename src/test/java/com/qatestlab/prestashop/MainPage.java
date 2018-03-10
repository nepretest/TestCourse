package com.qatestlab.prestashop;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class MainPage {

	WebDriver driver;
	Logger log;

	public MainPage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	public void clickOnAllProducts() {
		Reporter.log("Clicking on All Products btn");
		log.info("Clicking on All Products btn");
		WebElement element = driver.findElement(By.xpath("//section/a[contains(@class,'all-product')]"));
		element.click();
		log.info("All Products btn is clicked");
	}

	// public void clickMoreInfo() {
	// log.info("Clicking on More Info bnt");
	// WebElement element = driver.findElement(By.xpath("//a[@class='nav-link']"));
	// element.click();
	// log.info("More Info bnt is clicked");
	// }

	public String createdProductQuantity(String quantity) {
		Reporter.log("Verifying new prod quantity");
		log.info("Verifying new prod quantity");
		String element = driver.findElement(By.xpath("//div[@class='product-quantities']/span")).getText();
		// Cutting "товары" from the span
		String checkQuan = element.substring(0, quantity.length());
		return checkQuan;
	}

	public WebElement findCreatedProduct(String newName) {
		WebElement element = null;
		int x = 1;
		Reporter.log("Verifying new prod");
		log.info("Verifying new prod");
		do {
			try {
				element = driver
						.findElement(By.xpath("//div[@class='products row']//a[contains(text(),'" + newName + "')]"));
				x = 2;
			} catch (Exception e) {
				WebElement forward = driver.findElement(By.xpath("//a[@rel='next']"));
				forward.click();
			}
		} while (x == 1);
		return element;
	}

	public void clickOnCreatedProd(String newName) {
		Reporter.log("Clicking on new product link");
		log.info("Clicking on new product link");
		WebElement element = driver
				.findElement(By.xpath("//div[@class='products row']//a[contains(text(),'" + newName + "')]"));
		element.click();

	}

	public String createdProductPrice() {
		Reporter.log("Verifying new prod price");
		log.info("Verifying new prod price");
		String element = driver.findElement(By.xpath("//span[@itemprop='price']")).getAttribute("content");
		return element;
	}

	public String createdProductName() {
		Reporter.log("Verifying new prod name");
		log.info("Verifying new prod name");
		String element = driver.findElement(By.className("h1")).getText();
		return element;
	}

}
