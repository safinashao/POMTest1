package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchPageTest extends BaseTest {
	
	@BeforeClass
	public void searchPageSetup() {
		 acctsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		 searchPage = acctsPage.performSearch("MacBook");
		 //productInfoPage = searchPage.selectProduct("MacBook Pro"); //this has an effect on search page...revisit for clarity
		 
	}
	
	@Test(priority = 1)
	public void searchproductCountTest() {
		Assert.assertTrue(searchPage.getSearchProductCount()>0);
	}
	
	@Test(priority = 2)
	public void searchCriteriaTextExistTest() {
		Assert.assertTrue(searchPage.doSearchCriteriaTextExist());
	}
	
	@Test(priority = 3)
	public void getSearchCriteriaTextTest() {
		String text = searchPage.getSearchCriteriaText();
		System.out.println(text);
		
	}

	@Test(priority = 4)
	public void productHeaderValTest() { 
	    productInfoPage = searchPage.selectProduct("MacBook Pro"); 
	    String actProductHeader = productInfoPage.getProductHeaderValue();
	    Assert.assertTrue(actProductHeader.contains("Pro")); 
	  
	}
	 
	
	

}

