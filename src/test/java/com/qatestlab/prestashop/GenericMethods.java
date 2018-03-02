package com.qatestlab.prestashop;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GenericMethods {
	
	Actions act;
	Random rand = new Random();
	WebDriver driver;
	
	public GenericMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	public String randomName( ) {
		String randName = "";
		char[] letters = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for(int i = 0; i < 10; i++) {
			char randLet = letters[rand.nextInt(letters.length)];
			randName = randName + randLet;
		}	
		return randName;	
	}
	
	public void moveToElementAct(Actions act, String type, String locator) {
		if(type.equalsIgnoreCase("xpath")) {
			WebElement element = driver.findElement(By.xpath(locator));
			act.moveToElement(element).perform();
		} else if (type.equalsIgnoreCase("css")) {
			WebElement element = driver.findElement(By.cssSelector(locator));
			act.moveToElement(element).perform();
		} else if (type.equalsIgnoreCase("id")) {
			WebElement element = driver.findElement(By.id(locator));
			act.moveToElement(element).perform();
		} else if(type.equalsIgnoreCase("name")) {
			WebElement element = driver.findElement(By.name(locator));
			act.moveToElement(element).perform();
		} else if(type.equalsIgnoreCase("class")) {
			WebElement element = driver.findElement(By.className(locator));
			act.moveToElement(element).perform();
		} else if(type.equalsIgnoreCase("linktext")) {
			WebElement element = driver.findElement(By.linkText(locator));
			act.moveToElement(element).perform();
		} else if(type.equalsIgnoreCase("partialLinkText")) {
			WebElement element = driver.findElement(By.partialLinkText(locator));
			act.moveToElement(element).perform();
		}
	}
	
	public void sleepWait(int timeMs) {
		try {
		Thread.sleep(timeMs);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
