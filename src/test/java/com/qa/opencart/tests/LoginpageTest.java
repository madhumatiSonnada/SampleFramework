package com.qa.opencart.tests;

import javax.management.DescriptorKey;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencard.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic -100-login page for Demo open cart")
@Story("Design login page for DemoCart")
public class LoginpageTest extends BaseTest
{
	@Test
	@Description("Login page title test...")
	@Severity(SeverityLevel.NORMAL)
	public void loginpagetitleTest()
	{
		String title=loginpage.getLoginpageTitle();
		System.out.println(title);
		Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
	}
	@Test
	@Description("Url test..")
	@Severity(SeverityLevel.CRITICAL)
	public void loginpageurl()
	{
		String url=loginpage.getLoginpageUrl();
		System.out.println(url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION));
	}
	@Test
	@Description("Forgot password")
	@Severity(SeverityLevel.NORMAL)
	public void isForgotpswdlinkExists()
	{
		Boolean flag=loginpage.isforgotpswdlinkexists();
		Assert.assertTrue(flag);
	}
	@Test
	@Description("Login test")
	@Severity(SeverityLevel.BLOCKER)
	public void udoLogin()
	{
		
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Assert.assertTrue(accpage.isAccountpageheaderExists());
	}
	@Test
	@Description("Registration link")
	@Severity(SeverityLevel.CRITICAL)
	public void isregistrationlinkexistsTest()
	{
		Assert.assertTrue(loginpage.isregistrationlinkexists());
	}
	@Test
	public void navigatetoRegisterpage()
	{
		loginpage.navigatetoRegisterpage();
	}
}
