package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.utils.ElementUtil;

public class BaseTest {
	
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	DriverFactory df;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop); //instead of passing browser name, url, headless value, incognito value...I thought to pass directly the prop refrence...ke tu apne hisab se use kr le..mai tuje direct reference bhej rha hu
		//driver = df.initDriver("chrome"); earlier this was a hardcoded value, now we need to replace it by the value we are passing in properties file
		loginPage = new LoginPage(driver);	
		softAssert = new SoftAssert();
	}

	@AfterTest()
	public void tearDown(){
		driver.quit();
	}
}
