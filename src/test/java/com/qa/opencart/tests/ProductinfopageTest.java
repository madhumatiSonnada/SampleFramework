package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencard.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductinfopageTest extends BaseTest
{
	@BeforeClass
	public void Productpagesetup()
	{
	accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	//@DataProvider
//	public Object[][] getProductdata()
	//{
		//return new Object[][] {
			//{"MacBook","MacBook Pro"},
			//{"Apple","Apple Cinema 30\""}
		//};
	//}
	
	@DataProvider
	public Object[][] getProductdata()
	{
		Object proddata[][]=ExcelUtil.getTestData(Constants.PRODUCT_TEST_DATA);
		return proddata;
	}
	
	@Test(dataProvider="getProductdata")
	public void productInfoheaderTest(String Productname,String mainProduct)
	{
		
		searchresultpage=accpage.doSearch(Productname);
		productinfopage=searchresultpage.selectProduct(mainProduct);
		Assert.assertEquals(productinfopage.getProductheadertext(), mainProduct);
		
	}
	@DataProvider
	public Object[][] getProductinfodata()
	{
		return new Object[][] {
			{"MacBook","MacBook Air",Constants.PRODUCT_PAGE_IMG_COUNT}			
		};
	}
	@Test(dataProvider="getProductinfodata")
	public void productinfoimgcount(String Productname,String mainProduct,int count)
	{
		searchresultpage=accpage.doSearch(Productname);
		productinfopage=searchresultpage.selectProduct(mainProduct);
		Assert.assertTrue(productinfopage.getProductimgcount()==count);
	}
	@Test
	public void productinfotest()
	{
		searchresultpage=accpage.doSearch("MacBook");
		productinfopage=searchresultpage.selectProduct("MacBook Pro");
		Map<String,String> actproductinfo=productinfopage.getProductInfo();
		actproductinfo.forEach((k,v) -> System.out.println(k +":" +v));
		softAssert.assertEquals(actproductinfo.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actproductinfo.get("Brand"), "Apple");
		softAssert.assertEquals(actproductinfo.get("Reward Points"), "800");
		softAssert.assertEquals(actproductinfo.get("price"), "$2,000.00");
		softAssert.assertEquals(actproductinfo.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}
}
