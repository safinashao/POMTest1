package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	//all references will be maintained in the BaseTest
	
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage acctsPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;
	
	
	@Parameters({"browser", "browserversion", "testcasename"})
	@BeforeTest
	public void setup(String browserName, String browserVersion, String testCaseName) {
		df = new DriverFactory();
		prop = df.init_prop(); //stored here
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testcasename", testCaseName);
		}
	
		driver = df.init_driver(prop); //same value given to the init_driver 
		loginPage = new LoginPage(driver); //first page for this ptoject
		productInfoPage = new ProductInfoPage(driver);
		softAssert = new SoftAssert();
		//registerPage = new RegisterPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
