package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class OrderPage {
	
	//assume its V machine
	
	By loc = By.id("order");
	By email = By.id("email");
	
	public void getOrder() {
		System.out.println("get order");
	}

	public void getEmail() {
		System.out.println("get email id");
	}
}
