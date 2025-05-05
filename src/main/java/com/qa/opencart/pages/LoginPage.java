package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailAddress = By.id("input-email");
	private By pwd = By.id("input-password");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registerLink = By.cssSelector("div.list-group a[href$='register']");
	
	public LoginPage(WebDriver driver) {	
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean getLogiPageTitle() {
		boolean title =  eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page url is:: "+url);
		return url;
	}
	
	public boolean checkForgotPasswordLinkExists() {
		return eleUtil.waitForElementVisible(forgotPasswordLink, AppConstants.SHORT_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String username, String password) {
		System.out.println("App creds are:: "+username+" : "+password);
		eleUtil.waitForElementVisible(emailAddress, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		//return eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, 5);
	}
	
	public RegisterPage doRegister() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.MEDIUM_TIME_OUT).click();
		return new RegisterPage(driver);	
	}
}
