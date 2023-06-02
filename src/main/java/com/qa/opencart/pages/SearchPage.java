package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//by locators
	private By searchCriteriaText = By.xpath("//label[text()='Search Criteria']");
	private By searchProductResults = By.xpath("//div[@class='row']/child::div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']");
	//private By searchProductResults = By.cssSelector("div#content div.product-layout");
	
	
	//Page Actions/Methods
	
	public boolean doSearchCriteriaTextExist() {
		return eleUtil.doIsDispalyed(searchCriteriaText);
	}
	
	public String getSearchCriteriaText() {
		String text = eleUtil.getTextName(searchCriteriaText);
		System.out.println(text);
		return text;
	}
	
	public int getSearchProductCount() {
		int productCount = eleUtil.waitForElementsToBeVisible(searchProductResults, Constants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Count for search page: " + productCount);
		return productCount;
		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.isElementVisible(productLocator, Constants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
		
	}
	
	
	
	

}
