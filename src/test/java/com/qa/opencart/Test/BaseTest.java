package com.qa.opencart.Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchProductPage;

 
public class BaseTest {
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	LoginPage loginpage;
	AccountsPage accountspage;
	SearchProductPage searchproductpage;
	ProductInfoPage productinfopage;
	RegistrationPage registrationPage;
	
	@BeforeTest
	public void setup()
	{
		df=new DriverFactory();
		prop=df.init_prop();
		driver=df.init_driver(prop);
		loginpage=new LoginPage(driver);
		
		
	}
	
	@AfterTest
	public void tear_down()
	{
		driver.quit();
	}

}
