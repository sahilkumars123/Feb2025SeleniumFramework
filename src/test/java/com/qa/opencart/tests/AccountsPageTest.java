package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup()  {
		accountsPage = loginPage.doLogin("sahil987@yopmail.com", "Sahil@123");
	}
	
	
	@Test
	public void accountsPageTitleTest() {
		Assert.assertEquals(accountsPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void checkLogoutLinkExists() {
		Assert.assertTrue(accountsPage.isLogoutLinkExsits());
	}
}
