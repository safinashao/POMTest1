package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {
	
	//1. declare private webdriver:
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//2. declare accounts constructor:
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. private By locators:
	//List<WebElement> contentList = driver.findElements(By.xpath("//div[@id='content']/h2"));
	private By logoutLinkExist = By.linkText("Logout");
	private By accountsHeaders = By.cssSelector("div#content h2"); 
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("#search button");
	
	//4. Page Actions:
	
	public String getAccountsPageTitle() {
		String title = eleUtil.doGetTitle(Constants.LOGIN_ACCOUNTS_TITLE, Constants.DEFAULT_MEDIUM_TIME_OUT);
		return title;
	}
	
	public String getAccountsPageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("Url is: " + url);
		return url;
	}
	
	public boolean doLogoutLinkExist() {
		return eleUtil.getElement(logoutLinkExist).isDisplayed();
	}
	
	public boolean doSearchFieldExist() {
		return eleUtil.getElement(searchField).isDisplayed();
	}
	
	public List<String> getAccountsPageHeadersList() {
		
		List<WebElement> accHeadersList = eleUtil.waitForElementsToBeVisible(accountsHeaders, Constants.DEFAULT_SHORT_TIME_OUT);
		List<String> accHeadersValList = new ArrayList<String>();
		
		for(WebElement e : accHeadersList) {
			String text = e.getText();
			System.out.println(text);
			accHeadersValList.add(text);
//			if(accHeadersValList.contains("My Account")) {
//				System.out.println("Accounts Header is correct.......");
//			}
		}
		return accHeadersValList;
	}
	
	/**
	 * Whenever you come inside the Else part, return is null
	 * @param searchKey example macbook, samsung, imac
	 * @return
	 */
	public SearchPage performSearch(String searchKey) {
		
		if(doSearchFieldExist()) {
			eleUtil.doSendKeys(searchField, searchKey);
			eleUtil.doClick(searchButton);
			return new SearchPage(driver);
			
		}else {
			System.out.println("Search field is not present on the current page...");
			return null;
		}
	}
	
	
	

}
