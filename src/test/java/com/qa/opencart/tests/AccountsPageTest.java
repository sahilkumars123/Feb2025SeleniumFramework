package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup()  {
		accountsPage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountsPageTitleTest() {
		Assert.assertEquals(accountsPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void checkLogoutLinkExistsTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExsits());
	}
	
	@Test
	public void checkAccountPageHeadersCountTest() {
		int actAccPageHeadersCount = accountsPage.getAccountPageHeadingCount();
		System.out.println("Total Headers Count:: "+actAccPageHeadersCount);
		Assert.assertEquals(accountsPage.getAccountPageHeadingCount(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accountsPageHeadersList = accountsPage.getAccountPageHeaders();
		Assert.assertEquals(accountsPageHeadersList, AppConstants.EXPECTED_ACC_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}
	
	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey, int productCount) {
		
		searchResultsPage =  accountsPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductResultsCount(), productCount);
	}
	
	
}
