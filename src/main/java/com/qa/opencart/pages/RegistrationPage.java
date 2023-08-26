package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
	
	private WebDriver driver;
	
	public RegistrationPage() {
		this.driver = driver;	
	}
	
	By firstName = By.id("first_name");
	By lastName = By.name("last_name");
	
	

}
