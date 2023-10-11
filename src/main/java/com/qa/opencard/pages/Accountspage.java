package com.qa.opencard.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class Accountspage
{
	private WebDriver driver;
	private ElementUtil elutil;
	private By search=By.name("search");
	private By header=By.cssSelector("div#logo a");
	private By accountlist=By.cssSelector("div#content h2");
	private By searchbtn=By.cssSelector("div#search button");
	private By searchnotfounderror=By.xpath("(//div[@class='row']//p)[3]");
	
	public Accountspage(WebDriver driver)
	{
		this.driver=driver;
		elutil=new ElementUtil(driver);
	}
	
	public String getAccountpagetitle()
	{
		return elutil.waitfortitle(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public boolean isAccountpageheaderExists()
	{
		return elutil.isDisplayed(header);
	}
	public boolean issearchExists()
	{
		return elutil.isDisplayed(search);
	}
	
	public SearchResultspage doSearch(String productname)
	{
		if(issearchExists())
		{
		 elutil.dosendKeys(search, productname);
		 elutil.doclick(searchbtn);
		 return new SearchResultspage(driver);
		}
		return null;
	}
	public List<String> getAccountpagesectionlist()
	{
		List<WebElement> seclist=elutil.getElements(accountlist);
		List<String> actuallist=new ArrayList<String>();
		for(WebElement e:seclist)
		{
			String text=e.getText();
			actuallist.add(text);
		}
		return actuallist;
	}
	public Productinfopage selectProduct(String mainProductname)
	{
		List<WebElement> productlist=elutil.getElements(accountlist);
		for(WebElement e:productlist)
		{
		String text=e.getText();
		if(e.equals(mainProductname))
		{
			e.click();
			break;
		}
		}
		return new Productinfopage(driver);
	}
}
