package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.name("quantity");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String,String> productData;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeadervalue() {
		return elementUtil.doGetElementText(productHeader);
	}

	public int getProductImagesCount() {
		int totalProductImagesCount = elementUtil.waitForElementsVisible(productImages, AppConstants.MEDIUM_TIME_OUT)
				.size();
		System.out.println("Total Number of images for product:: " + getProductHeadervalue() + " are:: "
				+ totalProductImagesCount);
		return totalProductImagesCount;
	}

	private void getProductMetaData() {
		List<WebElement> productMetaDataList = elementUtil.waitForElementsVisible(productMetaData,AppConstants.MEDIUM_TIME_OUT);
		//Map<String, String> productMetaDataMap = new HashMap<String, String>();
		for (WebElement e : productMetaDataList) {
			String text = e.getText();
			String key = text.split(":")[0].trim();
			String value = text.split(":")[1].trim();
			
			productData.put(key, value);
		}	
	}
	
	private void getProductMetaPriceData() {
		List<WebElement> productMetaPriceDataList = elementUtil.waitForElementsVisible(productPricingData,AppConstants.MEDIUM_TIME_OUT);
		//Map<String, String> productMetaPriceDataMap = new HashMap<String, String>();
		
		String actPrice = productMetaPriceDataList.get(0).getText().trim();
		
		String exPriceLabel = productMetaPriceDataList.get(1).getText().split(":")[0].trim();
		String exPriceValue = productMetaPriceDataList.get(1).getText().split(":")[1].trim();

		productData.put("price", actPrice);
		productData.put(exPriceLabel, exPriceValue);
		
	}
	
	
	//Map does not maintain the order, whereas the List maintains the order.
	//But if you really want to store the data in key value pair and also wants to store in the insertion order.
	//Then use LinkedHashMap
	//But if you want to use the sorting order - Then use TreeMap
	
	public Map<String, String> getProductData() {
		
		productData = new HashMap<String, String>();
		productData.put("productHeader", getProductHeadervalue());
		productData.put("productImagesCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductMetaPriceData();
		
		return productData;
	}

}
