package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver eventDriver;
	public static WebEventListener eventListener;
	public static File file= new File(System.getProperty("user.dir"));
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void browserInitialization(){
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){		
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath()+"/resources/chromedriver");
			driver = new ChromeDriver(options);  
		}
		else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath()+"/resources/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		
		
		eventDriver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		eventDriver.register(eventListener);
		driver = eventDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
		

}
