package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productResult = By.cssSelector("div[class^='product-layout']");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public int getSearchProductResultsCount() {
		
		return elementUtil.waitForElementsVisible(productResult, AppConstants.MEDIUM_TIME_OUT).size();	
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		elementUtil.waitForElementAndClick(By.linkText(productName),AppConstants.MEDIUM_TIME_OUT);
		return new ProductInfoPage(driver);
	}

}
