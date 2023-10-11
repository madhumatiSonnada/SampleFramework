package com.qa.opencard.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class Productinfopage {

private WebDriver driver;
private ElementUtil elutil;

private By infopageheader=By.cssSelector("div#content h1");
private By images=By.cssSelector("div#content a img");
private By productmetadata=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
private By pricedata=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
private By quantity=By.id("input-quantity");
private By addtocartbtn=By.id("button-cart");
private By successmsg=By.cssSelector("div.alert.alert-success.alert-dismissible");
private Map<String,String> productInfomap;

	public Productinfopage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver=driver;
		elutil=new ElementUtil(driver);
	}
	
	public String getProductheadertext()
	{
		return elutil.getText(infopageheader);
	}
	public int getProductimgcount()
	{
		return elutil.waitForElementsToBeVisible(images, Constants.DEFAULT_TIME_OUT).size();
	}
	public Map<String, String> getProductInfo()
	{
	productInfomap=new LinkedHashMap<String,String>();
	productInfomap.put("productname", getProductheadertext());
	productInfomap.put("imgcount", String.valueOf(getProductimgcount()));
	ProductmetaData();
	PricemetaData();
	return productInfomap;
	}
	
	
	public void ProductmetaData()
	{
		List<WebElement> metadatalist=elutil.waitForElementsToBeVisible(productmetadata, Constants.DEFAULT_TIME_OUT);
		for(WebElement e:metadatalist)
		{
			String text=e.getText().trim();
			String[] metadata=text.split(":");
		String metakey=metadata[0].trim();
		String metavalue=metadata[1].trim();
			productInfomap.put(metakey, metavalue);
	}
}
	public void PricemetaData()
	{
		List<WebElement> metapricelist=elutil.waitForElementsToBeVisible(pricedata, Constants.DEFAULT_TIME_OUT);
		String price=metapricelist.get(0).getText().trim();
		String exprice=metapricelist.get(1).getText().trim();
		productInfomap.put("price", price);
		productInfomap.put("extaxprice", exprice);
	}
}