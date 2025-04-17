package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: design open cart login page")
@Feature("Feature 101: login feature")
@Story("US 120: All the features related to open cart login page")
@Owner("Naveen Automation Labs")
@Link(name = "LoginPage", url = "https://naveenautomationlabs.com/opencart/index.php?route=account/login")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.MINOR)
	@Description("login page title test....")
	@Feature("Feature 400: title test feature")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		Assert.assertTrue(loginPage.getLogiPageTitle());
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("login page url testing....")
	@Feature("Feature 401: title test feature")	
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Test(priority = 3, enabled = false)
	public void isForgotPasswordLinkExistsTest() {
		Assert.assertTrue(loginPage.checkForgotPasswordLinkExists());
	}

	@Test(priority = 4)
	public void loginTest() throws InterruptedException {
		accountsPage =  loginPage.doLogin("sahil987@yopmail.com", "Sahil@123");
		Assert.assertEquals(accountsPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
}
