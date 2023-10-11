package com.qa.opencard.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencard.pages.Accountspage;
import com.qa.opencard.pages.Loginpage;
import com.qa.opencard.pages.Productinfopage;
import com.qa.opencard.pages.Registrationpage;
import com.qa.opencard.pages.SearchResultspage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest 
{
	public DriverFactory df;
	public WebDriver driver;
	public Loginpage loginpage;
	public Properties prop;
	public Accountspage accpage;
	public SearchResultspage searchresultpage;
	public Productinfopage productinfopage;
	public Registrationpage registrationpage;
	public SoftAssert softAssert;
	@BeforeTest
	public void setup()
	{
		df=new DriverFactory();
		prop=df.init_prop();
		driver =df.init_driver(prop);
		loginpage=new Loginpage(driver);
		
		softAssert=new SoftAssert();
		
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
}
