package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencard.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("JIRA-300- Design Account page")
@Story("201- Account page test")
public class AccountspageTest extends BaseTest
{
	
	@BeforeClass
	public void accountspagesetup()
	{
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	@Description("Get Account page title")
	@Severity(SeverityLevel.MINOR)
	public void getAccountpagetitle()
	{
		String Acttitle=accpage.getAccountpagetitle();
		Assert.assertEquals(Acttitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	@Test
	@Description("Testing Account header page exists")
	@Severity(SeverityLevel.TRIVIAL)
	public void isAccountpageheaderExistsTest()
	{
		Assert.assertTrue(accpage.isAccountpageheaderExists());
	}
	@Test
	@Description("Testing search button exists")
	@Severity(SeverityLevel.CRITICAL)
	public void issearchExistsTest()
	{
		Assert.assertTrue(accpage.issearchExists());
	}
	@Test
	@Description("Account list")
	@Severity(SeverityLevel.CRITICAL)
	public void getAccountlisttest()
	{
		List<String> actlist=accpage.getAccountpagesectionlist();
		Assert.assertEquals(actlist,Constants.ACCOUNT_SECTION_LIST);
	}
	@Test
	@Description("Search test")
	@Severity(SeverityLevel.TRIVIAL)
	public void searchTest()
	{
		searchresultpage=accpage.doSearch("Macbook");
		String Actualsearchheader=searchresultpage.getResultpageheader();
		Assert.assertEquals(Actualsearchheader, "Search - Macbook");
	}
	@Test
	@Description("Product test")
	@Severity(SeverityLevel.NORMAL)
	public void searchproductTest()
	{
		searchresultpage=accpage.doSearch("iMac");
		int count=searchresultpage.getProductResultsList().size();
		Assert.assertEquals(count, Constants.IMAC_COUNT);
	}
	@Test
	@Description("Click on search")
	@Severity(SeverityLevel.BLOCKER)
	public void getProductList()
	{
		searchresultpage=accpage.doSearch("Macbook");
		List<String> actuallist=searchresultpage.getProductResultsList();
		System.out.println(actuallist);
	}
}
