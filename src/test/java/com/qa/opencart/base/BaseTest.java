package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	DriverFactory df;
	protected Properties prop;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();	
		driver = df.initDriver();
		loginPage = new LoginPage(driver);		
	}

	@AfterTest()
	public void tearDown(){
		driver.quit();
	}
}
