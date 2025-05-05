package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class SearchResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void setupPage() {
		accountsPage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void checkProductResultsCount() {
		//this we are not putting in before class though its repeated because 
		//this is a part of tc and is not part of the precondition.
		//Here we need to update the testdata everytime.
		//Today we are executing with macbook, tomorrow we need to run by iMac
		searchResultsPage = accountsPage.doSearch("macbook");
		int actualProductCount = searchResultsPage.getSearchProductResultsCount();
		Assert.assertEquals(actualProductCount, 3);
	}
	
	@Test()
	public void checkProductHeaderName() {
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeadervalue(),"MacBook Pro");
	}
}
