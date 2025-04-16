package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By logoutLink = By.linkText("Logout");
	private By accountHeaders = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		return eleutil.waitForTitleContainsAndReturn(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.SHORT_TIME_OUT);
	}

	public List<String> getAccountPageHeadings() {
		List<WebElement> accountPageHeadings = eleutil.waitForElementsVisible(accountHeaders,
				AppConstants.MEDIUM_TIME_OUT);
		List<String> accountPageHeadingsList = new ArrayList<String>();
		for (WebElement e : accountPageHeadings) {
			String text = e.getText();
			accountPageHeadingsList.add(text);
		}
		return accountPageHeadingsList;
	}

	public int getAccountPageHeadingCount() {

		return eleutil.waitForElementsVisible(accountHeaders, AppConstants.MEDIUM_TIME_OUT).size();
	}
	
	public boolean isLogoutLinkExsits() {
			return eleutil.waitForElementPresence(logoutLink,AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}

}
