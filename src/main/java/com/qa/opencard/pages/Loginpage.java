package com.qa.opencard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Step;

public class Loginpage {
	private WebDriver driver;
	private ElementUtil elutil;
	// private by locators
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//input[@value='Login']");
	private By forgotpass = By.linkText("Forgotten Password");
	private By register = By.linkText("Register");
	private By Errormsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 2.public page constructor
	public Loginpage(WebDriver driver) {
		this.driver = driver;
		elutil = new ElementUtil(driver);
	}
	// 3.page actions
	@Step("Getting login page title")
	public String getLoginpageTitle() {
		return elutil.waitfortitle(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}
@Step("getting login page url")
	public String getLoginpageUrl() {
		return elutil.waitForurl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION);
	}
@Step("Forgot pswd link")
	public boolean isforgotpswdlinkexists() {
		return elutil.isDisplayed(forgotpass);
	}
@Step("Login to application with user {0} and password {1}")
	public Accountspage doLogin(String un, String pswd) {
		elutil.waitforElementPresent(emailid, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		elutil.dosendKeys(password, pswd);
		elutil.doclick(login);
		return new Accountspage(driver);
	}
@Step("login to application with Incorrect username {0} and password {1}")
	public boolean DoInvalidLogin(String un,String pswd)
	{
	WebElement email_ele=elutil.waitforElementToBeVisible(emailid, Constants.DEFAULT_TIME_OUT);
		email_ele.clear();
	email_ele.sendKeys(un);
	elutil.dosendKeys(password, pswd);
		elutil.doclick(login);
		String Actualerrmsg=elutil.getText(Errormsg);
		System.out.println(Actualerrmsg);
		if(Actualerrmsg.contains(Errors.LOGIN_PAGE_ERROR_MSG))
		{
			return true;
		}
		return false;
	}

	@Step("Registration link exists")
	public boolean isregistrationlinkexists() {
		return elutil.isDisplayed(register);
	}
@Step("Navigate to Registration")
	public Registrationpage navigatetoRegisterpage() {
		if (isregistrationlinkexists()) {
			elutil.doclick(register);
			return new Registrationpage(driver);
		}
		return null;
	}
}
