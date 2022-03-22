package com.qa.opencart.Test;

import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoPageTest extends BaseTest{
	@BeforeClass
	public void ProductInfoPageSetup()
	{
		accountspage=loginpage.DologinCheck(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void checkProductImageCount()
	{
		
		searchproductpage = accountspage.checkSearchBox("MacBook");
		productinfopage = searchproductpage.selectFromProductList("MacBook Pro");
		Assert.assertTrue(productinfopage.getProductImageCount());
	}
	
	@Test(priority=2)
	public void checkProductInfoDetails()
	{
		searchproductpage = accountspage.checkSearchBox("MacBook");
		productinfopage = searchproductpage.selectFromProductList("MacBook Pro");
		LinkedHashMap<String,String> getList=productinfopage.getProductCompleteDetails();
	         
			Assert.assertEquals(getList.get("Brand"),"Apple");
			
	}
	
}
