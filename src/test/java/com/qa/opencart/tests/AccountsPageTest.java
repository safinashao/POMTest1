package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accsPageSetup() {
		 acctsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accountsPageTitleTest() {
		String actTitle = acctsPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, Constants.LOGIN_ACCOUNTS_TITLE);
	}
	
	@Test
	public void accountsPageUrlTest() {
		String actUrl = acctsPage.getAccountsPageUrl();
		System.out.println("Actual url is: " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void islogoutLinkExistTest() {
		Assert.assertTrue(acctsPage.doLogoutLinkExist());
	}
	
	@Test
	public void fieldSearchLinkExistTest() {
		Assert.assertTrue(acctsPage.doSearchFieldExist());
	}

	@Test
	public void accPageHeadersTest() {
		List<String> actualAccPageHeadersList = acctsPage.getAccountsPageHeadersList();
		System.out.println("Account Page Headers List: " + actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), Constants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	//collection.sort()
	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccPageHeadersList = acctsPage.getAccountsPageHeadersList();
		System.out.println("Actual Account Page Headers List: " + actualAccPageHeadersList);
		System.out.println("Expected Account Page Headers List: " + Constants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccPageHeadersList, Constants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	//Safina is for negative testing
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
			{"Safina"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchproductCountTest(String searchKey) {
		searchPage = acctsPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object [][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
		};
		
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		 searchPage = acctsPage.performSearch(searchKey);
		 if(searchPage.getSearchProductCount()>0) {
			 productInfoPage = searchPage.selectProduct(productName);
			 String actProductHeader = productInfoPage.getProductHeaderValue();
			 Assert.assertEquals(actProductHeader, productName);
		 }
	}

	
	

}
