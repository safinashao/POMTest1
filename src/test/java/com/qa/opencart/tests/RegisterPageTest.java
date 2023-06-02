package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeTest
	public void RegistrationPageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
		}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automation" + System.currentTimeMillis() + "@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegistrationTestData() {
		Object regData [][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
		
	@Test(dataProvider = "getRegistrationTestData")
	public void registerUserTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(
				registerPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
	
}
