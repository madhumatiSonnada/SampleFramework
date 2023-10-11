package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencard.base.BaseTest;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginpageNegativeTest extends BaseTest
{
	@DataProvider
	public Object[][] Logindata()
	{
	return	new Object[][]{
			{"Test123@gmail.com","Test!@#"},
	{"Madhumita@gmail.com","Mita1wer"},
	{" "," "}
	};
	}
	@Test(dataProvider = "Logindata")
	@Description("Login with invalid credentials")
	@Severity(SeverityLevel.NORMAL)
	public void LoginInvalidTest(String un,String pswd)
	{
		Assert.assertTrue(loginpage.DoInvalidLogin(un, pswd),Errors.LOGIN_PAGE_ERROR_MSG_NOT);
	}

}
