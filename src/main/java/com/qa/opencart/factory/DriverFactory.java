package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.BrowserException;
import com.qa.opencart.frameworkexception.FrameworkException;
import com.qa.opnecart.errors.AppError;

import io.qameta.allure.Step;

/**
 * 
 * @author sahil
 *
 */
public class DriverFactory {
	
	
	//3 methods - initDriver, initProp, takeScreenshot
	//initDriver is used to launch a browser and return driver instance
	//initProp is used to intialize the config.properties file and then return the prop instance
	//takeScreenshot - is to take a screenshot
	//DriverFactory says that if you need to have more options like ChromeOptions where you can handle headless, highlight,incognito better to create a new class OptionsManager...I am not responsible for it
	

	//we are not mentioning as static, because it will hamper the parallel execution as it will supply
	WebDriver driver;
	
	Properties prop;

	public static String isHighlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	//ThreadLocal Driver has 2 methods, set and get method
	//set method is used to set the driver
	//get method is used to get the driver

	OptionsManager optionsManager;

	/**
	 * This method is used to init the driver on the basis of given browsername.
	 * 
	 * @param browserName
	 * @return it returns driver
	 */
	@Step("initializing the driver with properties: {0}")
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser"); //getProperty method is used to get the property name
		String url = prop.getProperty("url");
		isHighlight= prop.getProperty("isHighlight");
		optionsManager = new OptionsManager(prop);//this optionsmanager also needs the prop instance coming from BaseTest, so we created a object over here.

		System.out.println("browser name is : " + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		case "safari":
			tlDriver.set(new SafariDriver()); //Safari does not supports headless browser
			break;

		default:
			System.out.println(AppError.INVALID_BROWSER_MESG + browserName + " is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MESG);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		getDriver().get(url);

		return getDriver();
	}
	
	/**
	 * this method is returning the driver with threadlocal driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
//	/**
//	 * this will make a connection with config.properties file 
//	 * @return the prop instance through which we will access the properties.
//	 */
//	public Properties initProp() {
//		
//		prop = new Properties(); // this is java inbuilt class coming from java.util package
//		try {
//			//it will make the connection with config.properties file
//			FileInputStream ip = new FileInputStream("./resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}
	
	

//	private void init_remoteDriver(String browserName) {
//		System.out.println("running tests on grid with browser : " + browserName);
//
//		try {
//
//			switch (browserName.toLowerCase().trim()) {
//			case "chrome":
//				tlDriver.set(
//						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
//				break;
//			case "firefox":
//				tlDriver.set(
//						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
//				break;
//			case "edge":
//				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
//				break;
//
//			default:
//				System.out.println("please pass the right remote browser name....");
//				throw new BrowserException(AppError.INVALID_BROWSER_MESG);
//			}
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//
//	}



	/**
	 * this method is used to init the properties from the config file
	 * 
	 * @return
	 */

	// mvn clean install -Denv="qa"

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("running tests on env: " + envName);

		try {
			if (envName == null) {
				System.out.println("env is null....hence running tests on QA env");
				ip = new FileInputStream("./resources/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./resources/config/stage.config.properties");
					break; 
				case "uat":
					ip = new FileInputStream("./resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./resources/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name..." + envName);
					throw new FrameworkException("INVALID ENV NAME");
				}
			}

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		// converting string "path" to a file object
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
