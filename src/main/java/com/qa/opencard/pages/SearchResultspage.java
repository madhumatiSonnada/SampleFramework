package com.qa.opencard.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultspage 
{
	private WebDriver driver;
	private ElementUtil elutil;
	
	private By searchheader=By.cssSelector("div#content h1");
	private By products=By.cssSelector("div.caption a");
	
	public SearchResultspage(WebDriver driver)
	{
		this.driver=driver;
		elutil=new ElementUtil(driver);
	}
	
	public String getResultpageheader()
	{
		if(elutil.isDisplayed(searchheader))
		{
			return elutil.getText(searchheader);
		}
		return null;
	}
	public int getsearchresultcount()
	{
		return elutil.waitforElements(products, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public List<String> getProductResultsList() 
	{
		List<WebElement> productList = elutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		List<String> productValList = new ArrayList<String>();
		for (WebElement e : productList)
		{
			String text = e.getText();
			productValList.add(text);
		}
		return productValList;
	}
	
	public Productinfopage selectProduct(String mainProductName) {
		//System.out.println("main product name : " + mainProductName);
		List<WebElement> productList = elutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		for(WebElement e : productList) {
			String text = e.getText();
				if(text.equals(mainProductName)) {
					e.click();
					break;
				}
		}
		return new Productinfopage(driver);
	}
	

}
