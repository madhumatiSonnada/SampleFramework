package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsutil;
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsutil=new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
				{
				jsutil.flash(element);
				}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void dosendKeys(By locator, String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doclick(By locator) {
		getElement(locator).click();
	}

	public String getText(By locator) {
		return getElement(locator).getText();
	}
	public boolean isElementpresent(By locator)
	{
		if(getElements(locator).size()>0)
		{
			return true;
		}
		return false;
	}
	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public List<String> getlinkText(By locator) {
		List<WebElement> elelist = getElements(locator);
		List<String> linktext = new ArrayList<String>();
		for (WebElement e : elelist) {
			String text = e.getText();
			if (!text.isEmpty()) {
				linktext.add(text);
			}
		}
		return linktext;
	}

	public List<String> getAttribute(By locator, String atrname) {
		List<WebElement> el = getElements(locator);
		List<String> atrlist = new ArrayList<String>();
		for (WebElement e : el) {
			String atrvalue = e.getAttribute(atrname);
			System.out.println(atrvalue);
			atrlist.add(atrvalue);
		}
		return atrlist;
	}

	// *********************Dropdown*******************//
	public void doSelectByVisibleText(By locator, String text) {

		Select select = new Select(getElement(locator));

		select.selectByVisibleText(text);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));

		select.selectByIndex(index);

	}

	public void doSelectByValue(By locator, String value) {

		Select select = new Select(getElement(locator));

		select.selectByValue(value);

	}
	//Getoptions

	public List<String> getOptionvalue(By locator) {
		Select se = new Select(getElement(locator));
		List<WebElement> options = se.getOptions();
		List<String> optionsvalue = new ArrayList<String>();
		System.out.println(optionsvalue.size());
		for (WebElement e : options) {
			String text = e.getText();
			optionsvalue.add(text);

		}
		return optionsvalue;
	}
	//Select using option method
	public void doselectbyoptionvalue(By locator,String value)
	{
		Select sel=new Select(getElement(locator));
		List<WebElement> optvalue=sel.getOptions();
		for(WebElement e:optvalue)
		{
			String text=e.getText();
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	//Select without using Select class and using xpath
	public void doselectbyxpath(By locator,String val)
	{
		
		List<WebElement> optvalue=driver.findElements(locator);
		for(WebElement e:optvalue)
		{
			String text=e.getText();
			if(text.equals(val))
			{
				e.click();
				break;
			}
		}
	}
	//Jquery dropdown select--multiple selection and single selection
	public void selectchoices(By locator, String... value) {

		List<WebElement> choices = driver.findElements(locator);
		if (value[0].equalsIgnoreCase("all"))
		{
			for (WebElement e : choices) 
			{
				String text = e.getText();
				// System.out.println(text);
				for (int i = 0; i < value.length; i++)
				{
					if (text.equals(value[i])) 
					{
						e.click();
						break;
					}
				}

			}
		}
		else
		{
			try
			{
				for(WebElement e:choices)
				{
					e.click();
				}
			}
			catch(ElementNotInteractableException e)
			{
				System.out.println("choices are over");
			}
		}
}
	//footerlist
	public List<String> getfooterlist(By locator)
	{
		List<WebElement> footerlist=driver.findElements(locator);
		List<String> list=new ArrayList<String>();
		for(WebElement e:footerlist)
		{
			String text=e.getText();
			list.add(text);
		}
		return list;
	}
	
	/************************************Mouseoveraction****************/
	//Leverl1
	public void selectsubmenu(By parentmenu,By submenu) throws InterruptedException
	{
		WebElement parent=getElement(parentmenu);
		Actions ac=new Actions(driver);
		ac.moveToElement(parent).perform();
		Thread.sleep(2000);
		getElement(submenu).click();
		
	}
	//Level2
	public void selectsubmenulevel2(By main,By sub1,By sub2) throws InterruptedException
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(getElement(main)).perform();
		Thread.sleep(2000);
		ac.moveToElement(getElement(sub1)).perform();
		getElement(sub2).click();
		
	}
	//Level3
	public void selectsubmenulevel3(By main,By sub1,By sub2,By sub3) throws InterruptedException
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(getElement(main)).perform();
		Thread.sleep(2000);
		ac.moveToElement(getElement(sub1)).perform();
		Thread.sleep(2000);
		ac.moveToElement(getElement(sub2)).perform();
		Thread.sleep(2000);
		getElement(sub3).click();
		
	}
	// Right click /Context click utility
		public void docontextclick(By locator)
		{
			Actions ac = new Actions(driver);
			ac.contextClick(getElement(locator)).perform();
		}
		
		//count
		public  int getrightclickoptionscount(By rightclick,By rightclickoptions)
		{
		return getrightclickoptions(rightclick,rightclickoptions).size();
		
		}

		// Get rightclickoptions
		public  List<String> getrightclickoptions(By rightclick,By rightclickoptions)
		{
			docontextclick(rightclick);
		List<WebElement> optionslist=getElements(rightclickoptions);
		List<String> optionsitems=new ArrayList<String>();
		for(WebElement e:optionslist)
		{
			String text=e.getText();
			//System.out.println(text);
			optionsitems.add(text);
		}
		return optionsitems;
		}
		//click on option
		public  void contextclickvalue(By rightclick,By options,String value)
		{
			docontextclick(rightclick);
			List<WebElement> items=getElements(options);
			for(WebElement e:items)
			{
				String text=e.getText();
				System.out.println(text);
				if(text.equals(value))
				{
					e.click();
					break;
				}
			}
			
		}
		//Actions sendkeys
		//use when you get element not interactable
		public  void doActionssendkey(By locator,String value)
		{
			Actions ac=new Actions(driver);
			ac.sendKeys(getElement(locator),value).perform();
			
		}
		public void doActionsclick(By locator)
		{
			Actions ac1=new Actions(driver);
			ac1.click(getElement(locator)).perform();
		}

		//***************************Wait utils****************************************************//
		public WebElement waitforElementPresent(By locator,int timeout)
		{
			
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				
			
		}
		/**
		 * An expectation for checking that an element is present on the DOM of a page and 
		 * visible.Visibility means that the element is not only displayed but also has a 
		 * height and width that isgreater than 0.
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public WebElement waitforElementToBeVisible(By locator,int timeout)
		{
			
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				
			}
		
		
		//******************************Wait with polling time*******************************
		public WebElement waitforElementPresent(By locator,int timeout,long pollingtime)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout),Duration.ofMillis(pollingtime));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		public WebElement waitForVisibilityofElement(By locator,int timeout,long pollingtime)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout),Duration.ofMillis(pollingtime));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		
		//*********************************wait for alert********************************
		
		public  Alert waitforAlertPresent(int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
			
		}
		public void AcceptAlert(int timeout)
		{
			waitforAlertPresent(timeout).accept();
		}
		public void DismissAlert(int timeout)
		{
			waitforAlertPresent(timeout).dismiss();
		}
		public String getAlerttext(int timeout)
		{
			return waitforAlertPresent(timeout).getText();
		}
		
		//************************Wait for url**********************************
		
		public  String waitForurl(int timeout,String urlFraction)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			if(wait.until(ExpectedConditions.urlContains(urlFraction)))
			{
				return driver.getCurrentUrl();
			}
			return null;
			
		}
		public  String waitForUrlToBe(int timeout,String urlval)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			if(wait.until(ExpectedConditions.urlToBe(urlval)))
			{
				return driver.getCurrentUrl();
			}
			return null;
		}
		//***************Wait for title ***************
		
		public String waitfortitlecontains(int timeout,String titleFraction)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
					if(wait.until(ExpectedConditions.titleContains(titleFraction)))
					{
						return driver.getTitle();
					}
			return null;
		}
		public String waitfortitle(int timeout,String title)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			if(wait.until(ExpectedConditions.titleIs(title)))
			{
				return driver.getTitle();
			}
			return null;
		}
		
		//******************************Wait for frame***************************************//
		
		public WebDriver waitForFrameByLocator(By framelocator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
		}
		
		public WebDriver waitForFrameIndex(int index,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		}
		
		public WebDriver waitForFrameByString(String frame,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		}
		public WebDriver waitForByelement(WebElement frameelement,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameelement));
		}
		
		//*********************wait for element to be clickable***********************//
		
		public  void clickwhenReady(By locaor,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(locaor)).click();
		}
		public void clickElementwhenReady(By locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
			
		}
		
		//******************************WAit for Elements******************************//
		
		public  List<WebElement> waitforElements(By locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		public  void printAllText(By locator,int timeout)
		{
		List<WebElement> list=	waitforElements(locator,timeout);
		for(WebElement e:list)
		{
			String text=e.getText();
			System.out.println(text);
		}
		}
		public List<String> getElementtextwithwait(By locator,int timeout)
		{
			List<WebElement> list=	waitforElements(locator,timeout);
			List<String> footerlist=new ArrayList<String>();
			for(WebElement e:list)
			{
				String text=e.getText();
				footerlist.add(text);
			}
			return footerlist;
		}
		
		public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		//*************************Custom Wait*******************************************//
		
		public WebElement retryingElement(By locator,int timeout) 
		{
			WebElement element=null;
			int attempts=0;
			while(attempts<timeout)
			{
				try
				{
				element=getElement(locator);
				break;
			}
				catch(NoSuchElementException e)
				{
					System.out.println("Element is not found in first attempt" +attempts);
					try
					{
					Thread.sleep(500);
					}
					catch(InterruptedException e1)
					{
						e1.printStackTrace();
					}
				}
				attempts++;
			}
			if(element==null)
			{
				try {
				throw new Exception ("element not found");
				}
				catch(Exception e)
				{
					System.out.println("Element not found "+timeout+ "with interval of "+500+"ms");
				}
			}
			return element;
		}
		
		public WebElement retryingElement(By locator,int timeout,int intervaltime) 
		{
			WebElement element=null;
			int attempts=0;
			while(attempts<timeout)
			{
				try
				{
				element=getElement(locator);
				break;
			}
				catch(NoSuchElementException e)
				{
					System.out.println("Element is not found in first attempt" +attempts);
					try
					{
					Thread.sleep(intervaltime);
					}
					catch(InterruptedException e1)
					{
						e1.printStackTrace();
					}
				}
				attempts++;
			}
			if(element==null)
			{
				try {
				throw new Exception ("element not found");
				}
				catch(Exception e)
				{
					System.out.println("Element not found "+timeout+ "with interval of "+intervaltime+"ms");
				}
			}
			return element;
		}
		
		
		//********************************wait for complete page load********************
		public void waitForPageLoad(int timeout)
		{
			long endtime=System.currentTimeMillis() +timeout;
				while(System.currentTimeMillis()<endtime)
				{
					JavascriptExecutor js=(JavascriptExecutor)driver;
				String state=	js.executeScript("return document.readyState").toString();
				System.out.println(state);
				if(state.equals("complete"))
				{
					break;
				}
				}
		}
		//******************Fluent wait*********************************//
		
		public  WebElement waitForElementLocatedwithFluentwait(By locator,int timeout,long pollingtime)
		{
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout)).
					pollingEvery(Duration.ofSeconds(pollingtime)).ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public WebElement waitForElementvisibilitydwithFluentwait(By locator,int timeout,long pollingtime)
		{
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout)).
					pollingEvery(Duration.ofSeconds(pollingtime)).
					ignoring(NoSuchElementException.class,ElementNotInteractableException.class);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public  Alert waitforAlertPresentwithFluentwait(int timeout,long pollingtime)
		{
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout)).
					pollingEvery(Duration.ofSeconds(pollingtime)).
					ignoring(NoSuchElementException.class,NoAlertPresentException.class);
		return wait.until(ExpectedConditions.alertIsPresent());
			
		}
		public void AcceptAlertFluent(int timeout,long pollingtime)
		{
			waitforAlertPresentwithFluentwait(timeout,pollingtime).accept();
		}
		public void DismissAlertFluent(int timeout,long pollingtime)
		{
			waitforAlertPresentwithFluentwait(timeout,pollingtime).dismiss();
		}
		public String getAlerttextFluent(int timeout,long pollingtime)
		{
			return waitforAlertPresentwithFluentwait(timeout,pollingtime).getText();
		}
		
		public  WebDriver waitforFramewithFluentwait(By framelocator,int timeout,long pollingtime)
		{
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout)).
					pollingEvery(Duration.ofSeconds(pollingtime)).
					ignoring(NoSuchElementException.class,NoSuchFrameException.class);
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));

}

		
}