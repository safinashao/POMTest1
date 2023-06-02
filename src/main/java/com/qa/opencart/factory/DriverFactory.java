package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	//this method is used to initialize the webdriver and return the driver
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	FileInputStream ip;
	
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * this method is initializing the browser
	 * @param prop
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		//String browserName = System.getProperty("browser").toLowerCase().trim();
		System.out.println("browser name is: " + browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}else if(browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			
		}else {
			System.out.println("please pass the correct browser: " + browserName);
			throw new FrameworkException("NO BROWSER FOUND EXCEPTION");
		}
		
		//driver.manage().window().fullscreen();
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	//get the local thread copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	//used to initialize the properties and will return prop reference
	//FileInputStream is used to interact with other files
	/**
	 * this method is reading the properties from .properties file
	 * @return
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		
		//maven clean install -Denv="qa" ---->>> "stage", "prod", "dev"
		//if no env passed, by deafult Tcs will run on qa env
		//Syntax used for environment variables is -D
		
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);
		
		try {
		if(envName == null) {
			System.out.println("no envirnment is passed....Running tests on QA env....");
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			
		}else {
			
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
				
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
				
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
				
	
			default:
				System.out.println("Wrong environment is passed....No need to run the test cases....");
				throw new FrameworkException("WRONG ENV IS PASSED");
			
			}
		}
		
		}catch(FileNotFoundException e) {
			
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}


}
