package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		 acctsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"iMac", "iMac", 3},
			{"Apple", "Apple Cinema 30\"", 6},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
		
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		 searchPage = acctsPage.performSearch(searchKey);
		 productInfoPage = searchPage.selectProduct(productName);
		 int actImagesCount = productInfoPage.getProductImagesCount();
		 Assert.assertEquals(actImagesCount, imagesCount);
		 
	}
	
	@Test
	public void productDescriptionTextTest() {
		searchPage = acctsPage.performSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		List<String> actProdText = productInfoPage.productDescriptionText();
		System.out.println(actProdText);
		Assert.assertTrue(actProdText.contains("Latest Intel mobile architecture"));
	}
	
	@Test
	public void productInfoTest() {
		searchPage = acctsPage.performSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");	
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
	//Assert is hard assertion
	//in SoftAssert, the methods are non static, need to first create an object
	//validation of multiple functions/features in a Map
	//assert vs verify (soft assertion) in testNG and not selenium
	
	//Success: You have added MacBook Pro to your shopping cart!

	@DataProvider
	
	public Object[][] addToCartTestData() {
		return new Object [][] {
			{"Macbook"},
			{"MacBook Pro"}
		};
	}
	
	
	@Test(dataProvider = "addToCartTestData")
	public void addProductToCartTest(String searchKey) {
		searchPage = acctsPage.performSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actCartMesg = productInfoPage.addProductToCart();
		
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf("MacBook Pro")>=0);
		
		//softAssert.assertTrue(actCartMesg.contains("Success"));
		//softAssert.assertTrue(actCartMesg.contains("MacBook Pro"));
		
		softAssert.assertTrue(actCartMesg.equals("Success: You have added MacBook Pro to your shopping cart!"));
		softAssert.assertAll();
	}
	
}
