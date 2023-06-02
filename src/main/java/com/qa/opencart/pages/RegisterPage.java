package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//Constructor
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//By locators
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephNum = By.id("input-telephone");
	private By password = By.id("input-password");
	private By pwdConfirm = By.id("input-confirm");
	
	////label[normalize-space()='Yes']/input[@type='radio']
	private By subscribeYes = By.xpath("(//div[@class='col-sm-10']/label/input)[position()=1]");
	private By subscribeNo = By.xpath("(//div[@class='col-sm-10']/label/input)[position()=2]");
	private By checkBox = By.xpath("//input[@name='agree']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	
	private By successMsg = By.cssSelector("div#content h1");
	
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");
	
	//page actions
	
	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		eleUtil.isElementVisible(this.firstName, Constants.DEFAULT_SHORT_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(emailId, email);
		eleUtil.doSendKeys(telephNum, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.pwdConfirm, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doActionClick(checkBox);
		eleUtil.doClick(continueBtn);
		
		String sucessMessage = eleUtil.isElementVisible(successMsg, Constants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println("Registration success message: " + sucessMessage);
		
		if(sucessMessage.contains(Constants.USER_REG_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			
			return true;
		}
		return false;
	}
	
	/*
	 * public void signOutAndRegister() {
	 * 
	 * eleUtil.doClick(logout); eleUtil.doClick(register);
	 * 
	 * }
	 */
	
	
	
	

}
