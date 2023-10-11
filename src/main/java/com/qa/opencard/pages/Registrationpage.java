package com.qa.opencard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;


public class Registrationpage
{
	private WebDriver driver;
	private ElementUtil elutil;
	private By firstname=By.id("input-firstname");
	private By lastname=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By pswd=By.id("input-password");
	private By confpswd=By.id("input-confirm");
	private By newsletteryes=By.xpath("(//input[@type='radio'])[2]");
	private By newsletterno=By.xpath("(//input[@type='radio'])[3]");
	private By policycheck=By.name("agree");
	private By continuebtn=By.xpath("//input[@value='Continue' and @type='submit']");
	private By successmsg=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerlink=By.linkText("Register");
	
		
	
	public Registrationpage(WebDriver driver)
	{
		this.driver=driver;
		elutil=new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstname,String lastname,String email,String telephone,
			String pswd,String newsletter)
	{
		elutil.waitforElementToBeVisible(this.firstname, Constants.DEFAULT_TIME_OUT).sendKeys(firstname);
		elutil.dosendKeys(this.lastname, lastname);
		elutil.dosendKeys(this.email, email);
		elutil.dosendKeys(this.telephone, telephone);
		elutil.dosendKeys(this.pswd, pswd);
	elutil.dosendKeys(this.confpswd, pswd);
	if(newsletter.equalsIgnoreCase("yes"))
	{
		elutil.doclick(newsletteryes);
	}
	else
	{
		elutil.doclick(newsletterno);
	}
	
	elutil.doclick(policycheck);
	elutil.doclick(continuebtn);
	if(getsuccessmessage().contains(Constants.REGISTER_SUCCESS_MESSAGE))
{
		gotoRegisterpage();
	return true;
}
return false;
	}
	
	public String getsuccessmessage()
	{
	return elutil.waitforElementToBeVisible(successmsg, Constants.DEFAULT_TIME_OUT).getText();
	}
	public void gotoRegisterpage()
	{
		elutil.doclick(logoutLink);
		elutil.doclick(registerlink);
	}
}
