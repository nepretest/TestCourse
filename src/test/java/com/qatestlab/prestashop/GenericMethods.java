package com.qatestlab.prestashop;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GenericMethods {
	
	Actions act;
	Logger log;
	Random rand = new Random();

	public GenericMethods(Logger log) {
		this.log = log;
	}

	public void getURL(WebDriver driver, String url) {
		log.info("Navigating to " + url);
		driver.get(url);
	}

	public void endTest(WebDriver driver) {
		driver.quit();
		log.info("Test finished\n");
	}

	public String randomName() {
		String randName = "";
		char[] letters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		for (int i = 0; i < 10; i++) {
			char randLet = letters[rand.nextInt(letters.length)];
			randName = randName + randLet;
		}
		return randName;
	}

	public String randomPrice() {
		double i = rand.nextDouble() * 100;
		String j = String.valueOf(Math.round(i * 100) / 100.00);
		return j;
	}

	public String randomQuan() {
		String i = String.valueOf(1 + rand.nextInt(100));
		return i;
	}
	
//	public void moveToElementAct(WebDriver driver, Actions act, String type, String locator) {
//		if(type.equalsIgnoreCase("xpath")) {
//			WebElement element = driver.findElement(By.xpath(locator));
//			act.moveToElement(element).perform();
//		} else if (type.equalsIgnoreCase("css")) {
//			WebElement element = driver.findElement(By.cssSelector(locator));
//			act.moveToElement(element).perform();
//		} else if (type.equalsIgnoreCase("id")) {
//			WebElement element = driver.findElement(By.id(locator));
//			act.moveToElement(element).perform();
//		} else if(type.equalsIgnoreCase("name")) {
//			WebElement element = driver.findElement(By.name(locator));
//			act.moveToElement(element).perform();
//		} else if(type.equalsIgnoreCase("class")) {
//			WebElement element = driver.findElement(By.className(locator));
//			act.moveToElement(element).perform();
//		} else if(type.equalsIgnoreCase("linktext")) {
//			WebElement element = driver.findElement(By.linkText(locator));
//			act.moveToElement(element).perform();
//		} else if(type.equalsIgnoreCase("partialLinkText")) {
//			WebElement element = driver.findElement(By.partialLinkText(locator));
//			act.moveToElement(element).perform();
//		}
//	}
	
	public void sleepWait(int timeMs) {
		try {
			Thread.sleep(timeMs);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public WebDriver runBrowser(String browser) {
		WebDriver driver;
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			log.info("Running Firefox");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			log.info("Running Chrome");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			// Using IEDriver x32 to get IE faster
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\drivers\\IEDriverServer32.exe");
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			log.info("Running Ieternet Explorer");
			driver = new InternetExplorerDriver(capability);
		} else {
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", browser);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			log.info("Running " + browser + " mobile device emulation on chrome");
			driver = new ChromeDriver(chromeOptions);
		}
		driver.manage().window().maximize();
		return driver;

	}
	
}
