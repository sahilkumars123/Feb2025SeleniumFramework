package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoBaseTest() {
		accountsPage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
		};
	}

	@Test(dataProvider = "productTestData")
	public void productHeaderTest(String productKey, String productName) {
		searchResultsPage = accountsPage.doSearch(productKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actProductHeader = productInfoPage.getProductHeadervalue();
		Assert.assertEquals(actProductHeader,productName);
	}
	
	@DataProvider
	public Object[][] productImagesTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
		};
	}
	
	@Test(dataProvider = "productImagesTestData")
	public void checkProductImagesCount(String productKey, String productName, int expectedImagesCount) {
		searchResultsPage = accountsPage.doSearch(productKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(),expectedImagesCount);
	}
	
	
	//AAA Pattern - Act, Arrange, Assert - It means we need to write only one assertion in the block.
	//But here we are writing 3 hard assertions in the method which is not the right approach.
	//So,we will not be to write hard assertions. We need to mention Soft Assertions.
	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductData = productInfoPage.getProductData();
		System.out.println(actualProductData);
		softAssert.assertEquals(actualProductData.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductData.get("price"), "$2,000.00");
		softAssert.assertEquals(actualProductData.get("Reward Points"), "800");
		//important to write the below statement, if not written it will not pass all the assertion without checking
		softAssert.assertAll();
	}
}
