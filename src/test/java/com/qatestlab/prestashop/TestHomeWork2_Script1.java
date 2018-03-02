package com.qatestlab.prestashop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestHomeWork2_Script1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Logger log = LogManager.getLogger(TestHomeWork2_Script1.class.getName());
		AdminPanel ap = new AdminPanel(driver, log);
		driver.manage().window().maximize();
		String testURL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
		String loginUrl = "webinar.test@gmail.com";
		String password = "Xcg7299bnSmMuRLp9ITw";
		driver.get(testURL);
		
		// Logging in
		ap.loginAdminPanel(loginUrl, password);
		
	}

}
