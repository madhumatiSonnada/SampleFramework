package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencard.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class registrationpageTest extends BaseTest
{
	@BeforeClass
	public void registersetup()
	{
		registrationpage=loginpage.navigatetoRegisterpage();
	}
	public String getRandomEmail()
	{
		Random random=new Random();
	String email="madhu"+random.nextInt(1000)+"@gmail.com";
	return email;
	}
	//@DataProvider
	//public Object[][] getRegisterData()
	//{
		//return new Object[][]
		//{
			//{"maya","s","89876545","maya@123","Yes"},
			//{"Hanu","B","898786545","Hanu@123","no"}
		//};
	//}
	@DataProvider
	public Object[][] getRegisterData()
	{
		Object regdata[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regdata;
	}
	@Test(dataProvider="getRegisterData")
	public void accRegistrationTest(String firstname,String lastname,String telephone,String pswd,String newsletter)
	{
		Assert.assertTrue(registrationpage.accountRegistration(firstname,lastname,getRandomEmail(),telephone,pswd,newsletter));
		
	}
}
