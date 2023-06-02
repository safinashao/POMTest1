package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	
	//only used to perform action on this page
	//initialize the driver by creating a page constructor
	//by locators
	
	//1. declare private driver
	private WebDriver driver; 
	private ElementUtil eleUtil;
	
	//2. Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver); //when object of ElementUtil is created, a driver has to be passed 
	}
	
	//3. By locators:
	private By emailId = By.id("input-email");
	private By passWord = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	//4. Page Actions:
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_SHORT_TIME_OUT);
	}
	
	public boolean getLoginPageUrl() {
		return eleUtil.waitForTitleContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_SHORT_TIME_OUT);
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDispalyed(forgotPwdLink);
	}
	
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDispalyed(registerLink);
	}
	
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with : "+ un + ":" + pwd);
		eleUtil.doSendKeys(emailId, un, 5);
		eleUtil.doSendKeys(passWord, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver); //returns AccountsPage class reference
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	

}
