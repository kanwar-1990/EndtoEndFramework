package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Util.Constants;
import com.qa.opencart.Util.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eutil;

	private By registerLink = By.linkText("Register");
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBttn = By.xpath("//input[@type='submit']");
	private By loginErrorMesg = By.cssSelector(".alert.alert-danger.alert-dismissible");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);

	}

	@Step("method to check page title......")
	public String getPageTittle() {
		return eutil.doGetTitle(Constants.Login_Page_Title, Constants.TimeOut);
	}

	@Step("method to check page Url")
	public boolean getPageUrl() {
		return eutil.waitForURLToContain(Constants.Login_Page_Url_Fraction, Constants.TimeOut);
	}

	@Step("method to check register link displayed or not")
	public boolean getregisterLink() {
		return eutil.doIsDisplayed(registerLink);
	}

	@Step("method to check with proper username {0} and password {1}")
	public AccountsPage DologinCheck(String userName, String password)

	{
		eutil.doSendKeys(emailId, userName);
		eutil.doSendKeys(this.password, password);
		eutil.doClick(loginBttn);
		// Page chaining Model

		return new AccountsPage(driver);
	}

	@Step("checking login functionality with wrong username {0} and password {1}")
	public boolean doLoginWrongCredentials(String userName, String password) {
		System.out.println("try to login with wrong credentials: " + userName + " : " + password);

		eutil.doSendKeys(emailId, userName);
		eutil.doSendKeys(this.password, password);
		eutil.doClick(loginBttn);
		String errorMesg = eutil.doGetText(loginErrorMesg);
		System.out.println("The error MESG IS ::" + errorMesg);
		if (errorMesg.trim().equals(Constants.LOGIN_PAGE_ERR)) {
			return false;
		}
		return true;
	}
	@Step("navigating to registeration page.....")
	public RegistrationPage goToRegisterPage() {
		eutil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
