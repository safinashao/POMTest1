package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.util.Constants;

public class LoginPageTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void getLoginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
		
	}
	
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 4)
	public void registerLinkExistTest() {
		boolean actRegisterLink = loginPage.isRegisterLinkExist();
		Assert.assertTrue(actRegisterLink);
	}
	
	@Test(priority = 5)
	public void loginTest() {
		acctsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(acctsPage.doLogoutLinkExist());
		
	}
	
	

}
