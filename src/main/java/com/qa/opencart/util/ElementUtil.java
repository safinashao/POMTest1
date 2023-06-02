package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

   

public class ElementUtil {
	
	private WebDriver driver; //to initialize this webdriver, we create a constructor
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver; //this driver equals local driver we're passing through the constructor
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public static By getBy(String locatorType, String locatorValue) {
		By locator = null;
		
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;

		case "name":
			locator = By.name(locatorValue);
			break;
			
		case "classname":
			locator = By.className(locatorValue);
			break;
			
		case "xpath":
			locator = By.cssSelector(locatorValue);
			break;
			
		case "cssselector":
			locator = By.xpath(locatorValue);
			break;
			
		case "linktext":
			locator = By.linkText(locatorValue);
			break;	
			
		default:
			System.out.println("please pass the right locator type and value........");
			break;
		}
		return locator;
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			//jsUtil.flash(element);
		}
		return element;
	}
	
	public WebElement getElement(By locator, int timeOut) {
		return doPresenceOfElementLocated(locator, timeOut);	
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));	
	}
	
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}
	
	public void doSendKeys(By locator, String value, int timeOut) {
		doPresenceOfElementLocated(locator, timeOut).sendKeys(value);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}
	
	public void doClick(By locator, int timeOut) {
		doPresenceOfElementLocated(locator, timeOut ).click();
	}
	
	
	//getTagName returns a String
	public String getTextName(By locator) {
		return getElement(locator).getText();
	}
	
	public String getAttributeVal(By locator, String value) {
		 String attrVal = getElement(locator).getAttribute(value);
		 System.out.println(attrVal);
		 return attrVal;
	}

	public boolean doIsDispalyed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public boolean verifyElement(By locator) {
		int elementCount = getElementsCount(locator);
		System.out.println("total elements found............." + elementCount);
		if(elementCount >=0)  { //should be one as per training)
			System.out.println("element is found...." + locator);
		return true;
		}else {
			System.out.println("element not found...." + locator);
			return false;
		}
	}
	
	public static void printElementValues(List<String> imageList) {
		for(String e : imageList) {
			System.out.println(e);
		}
	}
		
	public List<String> getElementsTextList(By locator, String attrName) {
		List<WebElement> imageList = getElements(locator);
		List<String> attText = new ArrayList<String>();
		for(WebElement e : imageList) {
			String attrValue = e.getAttribute(attrName);
			if(!attrName.isEmpty()) {
					attText.add(attrValue);
				}
			}

		return attText;
	}
	
//	***********************************Drop Down Utils***************************************************
	
	
	public void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doDropDownSelectByValue(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByValue(text);
	}
	
	public void doDropDownSelectByText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
		
	}

	public void selectWithoutSelectMethod(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public void selectDropDownValueWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		for(WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public void googleSearchExample(By locator, String value) {
		List<WebElement> suggList = getElements(locator);
		System.out.println(suggList.size());
		for(WebElement e: suggList) {
			String text = e.getText();
			System.out.println(text);
			if(text.contains(value)) {
				e.click();
				break;
			}
			
		}
		
	}
	
	//*****************************Table util**********************************
	
	public void printTable(By rowLocator, By colLocator, String beforeXpath, String afterXpath) {
		int rowCount = getElements(rowLocator).size();
		int colCount = getElements(colLocator).size();
		for(int row = 2; row<=rowCount; row++ ) {
			for(int col = 1; col<=colCount; col++ ) {
				String xpath = beforeXpath+row+afterXpath+col+"]";
				String text = getElement(By.xpath(xpath)).getText();
				System.out.print(text+"   |   ");
				
			}
			System.out.println();
		}
	
	}
	
	//******************************Actions Util************************************
	
	public void doMoveToElement(By locator)  {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	
	}
	
	public void doClick(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		doClick(childMenuLocator);
	}
	
	public void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
		
	}
	public  void doActionSendKeysOnActiveElement(By locator, String value) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}
	
	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
		
	}
	
	public void doActionMoveToElementClick(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).click().build().perform();
		
	}
	
	//*********************************************Wait Utils**********************************************
	
	
	public WebElement doPresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
    	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement doPresenceOfElementLocated(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
    	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement isElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement isElementVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//what of number of elements, is it possible entire footer is loaded after sometime?
	
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	//not ideal to use in framework
	public WebElement waitForElementToBeVisibleWithWebElement(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut); 
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String eleText = e.getText();
			eleTextList.add(eleText);
		}
		return eleTextList;
	}
	
	public List<String> getElementsTextListWithWait(By locator, int timeOut, long intervalTime) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut, intervalTime); 
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String eleText = e.getText();
			eleTextList.add(eleText);
		}
		return eleTextList;
	}
	
	//**********************************Url Utils for Non WebElement********************************************
	
	public boolean urlContainsFraction(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 
		return wait.until(ExpectedConditions.urlContains(urlFraction));	
	}
	
	public boolean urlMatches(String urlToMatch, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 
		return wait.until(ExpectedConditions.urlMatches(urlToMatch));
    }
	
	public boolean urlToBe(String urlToBeExact, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 
		return wait.until(ExpectedConditions.urlToBe(urlToBeExact));
    }
	
	public boolean waitForTitleContains(String TitleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 
		return wait.until(ExpectedConditions.titleContains(TitleFraction));	
	}
	
	public String doGetTitle(String TitleFraction, int timeOut) {
    	if(waitForTitleContains(TitleFraction, timeOut)) {
    		return driver.getTitle();
    	}
		return null;
	}
	
	public boolean waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 
		return wait.until(ExpectedConditions.titleIs(title));	
	}
	
	public String doWaitForTitleIs(String title, int timeOut) {
		if(waitForTitleIs(title, timeOut )){
		return driver.getTitle();
	}
	return null;
   }

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	
	public void doAcceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}
	
	public void doDismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}
	
	public void enterAlertText(int timeOut, String text) {
		waitForAlert(timeOut).sendKeys(text);
	}
	
	
	
	
	
	
}
