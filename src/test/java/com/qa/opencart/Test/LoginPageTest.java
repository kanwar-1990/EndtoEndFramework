package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart App - Login Page")
@Story("US 101: Open Cart Login Design with multiple features")
public class LoginPageTest extends BaseTest {
	@Description(" login page title check")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void checkPageTitleTest() {
		String title = loginpage.getPageTittle();
		System.out.println("Login Page title is :: " + title);
		Assert.assertEquals(title, Constants.Login_Page_Title);

	}

	@Description("login page title check")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void checkPageUrlTest() {
		Assert.assertTrue(loginpage.getPageUrl());
	}

	@Test(priority = 3)
	public void checkRegisterLinkTest() {
		Assert.assertTrue(loginpage.getregisterLink());
	}

	@Description("Checking do login  with right username and password :: ")
	@Test(priority = 4)
	@Severity(SeverityLevel.BLOCKER)
	public void checkLoginTest()

	{
		accountspage = loginpage.DologinCheck(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String getTitle = accountspage.getAccountsPageTitle();
		Assert.assertEquals(getTitle, Constants.Accounts_Page_Title);

	}

}
