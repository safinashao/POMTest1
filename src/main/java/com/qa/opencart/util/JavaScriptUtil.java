package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	//15 is the number of times it'll execute
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for(int i = 0; i < 2; i++) {
			changeColor("rgb(0,200,0)", element);//1
			changeColor(bgcolor, element); //2
		}
	}
	
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {	
		}
	}
	
	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}
		
	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}
		
	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("alert('" + message + "')");
	}
	
	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeAsyncScript("return document.documentElement.innerText;").toString();
	}
	
	//not recommended, indirectly clicking in/from the DOM, using backdoor
	//ideal when elementNotInteractible on the WebPage
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
	
	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); //object used to scroll up and down
	}
	
	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}
	
	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border= '3px solid red'", element);
	}
	
	public void waitForPageLoaded() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String loadingStatus = js.executeScript("return document.readyState;").toString();
		
		if(loadingStatus.equals("complete")) {
			System.out.println("page is fully loaded");
		}
		else {
			for(int i = 0; i <=20; i++) { //20 seconds is ideal for the page to have loaded in every industry
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				loadingStatus = js.executeScript("return document.readyState;").toString();
				System.out.println("current page loading status: " + loadingStatus);
				if(loadingStatus.equals("complete")) {
					break;
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
