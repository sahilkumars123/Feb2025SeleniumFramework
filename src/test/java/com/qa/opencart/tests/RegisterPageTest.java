package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup()  {
		registerPage = loginPage.doRegister();
	}
	
	@Test
	public void verifyRegisterPageHeader() {
		Assert.assertEquals(registerPage.getRegisterPageHeader(),AppConstants.REGISTER_PAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] userData() {
		return new Object[][] {
			{"sahil","kumar","9876778283","sahil@765","yes"},
			{"rahul","kumar","9876778283","rahul@765","yes"}
		};
	}
	
	@Test(dataProvider = "userData")
	public void verifyRegisterationDetails(String firstName, String lastName, String phoneNo,String pass, String subscribe) {
		
		String email = "aa"+System.currentTimeMillis()+"@gmail.com";	
		Assert.assertTrue(registerPage.fillRegisterationDetails(firstName, lastName, email, phoneNo, pass, subscribe));	
	}
}
