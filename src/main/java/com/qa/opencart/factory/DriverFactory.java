package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.Browser;
import com.qa.opencart.utils.Errors;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optmanager;
	public static ThreadLocal<WebDriver> tldriver=new ThreadLocal<WebDriver>();
	
	public static final Logger log=Logger.getLogger(DriverFactory.class);
	public WebDriver init_driver(Properties prop)
	{
		//String browsername=prop.getProperty("browser");
		String browsername=System.getProperty("browser");
		System.out.println("Browser name is " +browsername);
		log.info("Browser name is " +browsername);
		highlight=prop.getProperty("highlight").trim();
		optmanager=new OptionsManager(prop);
		if(browsername.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE))
		{
			log.info("Chrome Browser");
			WebDriverManager.chromedriver().setup();
			//or 
			//System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY, Browser.CHROME_DRIVER_PATH);
			//driver=new ChromeDriver(optmanager.getChromeOptions());
			tldriver.set(new ChromeDriver(optmanager.getChromeOptions()));
		}
		else if(browsername.equals(Browser.FIREFOX_BROWSER_VALUE))
		{
			WebDriverManager.chromedriver().setup();
			System.setProperty(Browser.FIREFOX_DRIVER_BINARY_KEY, Browser.FIREFOX_DRIVER_PATH);
			//driver=new FirefoxDriver(optmanager.getFirefoxoptions());
			tldriver.set(new FirefoxDriver(optmanager.getFirefoxoptions()));
		}
		else if(browsername.equals(Browser.SAFARI_BROWSER_VALUE))
		{
			WebDriverManager.safaridriver();
			driver=new SafariDriver();
		}
		else
		{
			log.error("Please enter the proper browser");
			log.warn("Please enter the proper browser name");
			System.out.println(Errors.BROWSER_ERROR_MSG +browsername);
			
		}
		getThreadDriver().manage().deleteAllCookies();
		getThreadDriver().manage().window().maximize();
		getThreadDriver().get(prop.getProperty("url"));
		return getThreadDriver();
		
	}
	
	public static WebDriver getThreadDriver()
	{
	return tldriver.get();
	}
	/**
	 * This method is used to initilize the properties on the basis of given env
	 * QA,test env,productions env
	 * @return prop
	 */
	
	public Properties init_prop()
	{
		prop=new Properties();
		FileInputStream ip=null;
		
		//mvn clean install -Denv="qa"
	String envname=	System.getProperty("env");
	System.out.println("Running tests on enviroment  "+envname);
	log.info("Running in the test environment");
	if(envname==null)
	{
		System.out.println("No env is given hence running it on QA env");
		try {
			ip=new FileInputStream("./src/test/resources/Config/qa.config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
		else
		{
			try
			{
		switch (envname.toLowerCase()) {
		case "qa":
			
				ip=new FileInputStream("./src/test/resources/Config/qa.config.properties");
			break;
			
		case "preprod":
			
				ip=new FileInputStream("./src/test/resources/Config/preprod.config.properties");
				break;
		default:
			System.out.println("please pass the correct env");
			break;
		}
	}
	catch(Exception e)
	{
		System.out.println("Exception ");
		}
	}
	try {
		prop.load(ip);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;
	}
	
		public static String getScreenshot() 
		{
		File srcfile=((TakesScreenshot)getThreadDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

		}
}
