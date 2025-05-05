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
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		return eleutil.getPageTitleIs(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.SHORT_TIME_OUT);
	}

	public List<String> getAccountPageHeaders() {
		List<WebElement> accountPageHeadings = eleutil.waitForElementsVisible(accountHeaders,AppConstants.MEDIUM_TIME_OUT);
		List<String> accountPageHeadingsList = new ArrayList<String>();
		for (WebElement e : accountPageHeadings) {
			String text = e.getText();
			accountPageHeadingsList.add(text);
		}
		System.out.println("Account Page Headers are:: "+accountPageHeadingsList);
		return accountPageHeadingsList;
	}

	public int getAccountPageHeadingCount() {

		return eleutil.waitForElementsVisible(accountHeaders, AppConstants.MEDIUM_TIME_OUT).size();
	}
	
	public boolean isLogoutLinkExsits() {
			return eleutil.waitForElementPresence(logoutLink,AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		WebElement searchField = eleutil.waitForElementVisible(search, AppConstants.MEDIUM_TIME_OUT);
		searchField.clear();
		searchField.sendKeys(searchKey);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
