package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//'ul.thumbnails img'
	private By productHeader = By.tagName("h1") ;
	private By productImagesCount = By.xpath("//ul[@class='thumbnails']//a");
	private By productDescription = By.xpath("//div[@class='cpt_product_description ']//b");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.xpath("//button[text()='Add to Cart']");
	private By cartSuccessMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public String getProductHeaderValue() {
		String productHeaderVal = eleUtil.getTextName(productHeader);
		System.out.println("product header: " + productHeaderVal);
		return productHeaderVal;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsToBeVisible(productImagesCount, Constants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product images count: " + imagesCount);
		return imagesCount;
	}
	
	public void enterQuantity(int qty) {
		System.out.println("product quantity: " + qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMessg = eleUtil.isElementVisible(cartSuccessMessage, Constants.DEFAULT_SHORT_TIME_OUT).getText();
		
		StringBuilder sb = new StringBuilder(successMessg);
		String mesg = sb.substring(0, successMessg.length()-1).replace("\n", "");
		
		//String mesg = successMessg.substring(0, successMessg.length()-1).replace("\n", "");
		System.out.println("Cart Success Message: " + mesg );
		return mesg;
	}
	
	
	public List<String> productDescriptionText() {
		List<WebElement> productDescr = eleUtil.getElements(productDescription);
		List<String> descrText = new ArrayList<String>();
		System.out.println(productDescr.size());
		for(WebElement e : productDescr) {
			String text = e.getText();
			descrText.add(text);
		}
		return descrText;
	}
	
	
//	  Brand: Apple Product 
//	  Code: Product 18 Reward 
//	  Points: 800 
//	  Availability: In Stock
	 
	public Map<String, String> getProductInfo() {
		//productInfoMap = new HashMap<String, String>();
		productInfoMap = new LinkedHashMap<String, String>(); //maintains the exact order as displayed on page
		//productInfoMap = new TreeMap<String, String>(); //maintains alphabet order...sort the keys
		
		//header:
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}

	//fetching metadata
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
		    String meta = e.getText();
			String metaInfo[]  = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	//fetching price data
	private void getProductPriceData() {
		List<WebElement> priceList =  eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String exTaxVal = exTax.split(":")[1].trim();
	
		productInfoMap.put("productprice", price);
		productInfoMap.put("ExTax", exTaxVal);
	}
	

	
	
	
}
