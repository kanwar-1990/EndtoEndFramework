package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Util.Constants;
import com.qa.opencart.Util.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eutil;
	private By accountsHeader = By.cssSelector("#logo h1 a");
	private By sectionsHeader = By.cssSelector("#content h2");
	private By searchText = By.xpath("//input[@type='text' and  @name='search']");
	private By searchBttn = By.xpath("//span//button[@type='button']");
	private By logoutLink = By.linkText("Logout");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		return eutil.doGetTitle(Constants.Accounts_Page_Title, Constants.TimeOut);
	}

	public Boolean getAccountsPageHeader() {
		String header = eutil.doGetText(accountsHeader);
		if (header.equalsIgnoreCase(Constants.Accounts_Page_Header)) {
			return true;
		}
		return false;
	}

	public List<String> getSectionsHeader() {
		List<String> sectionsList = eutil.getElementsTextListWithWait(sectionsHeader, 3);
		List<String> accList = new ArrayList<String>();
		accList.addAll(sectionsList);
		System.out.println(accList);
		return accList;

	}

	public boolean getLogoutLink() {
		return eutil.doIsDisplayed(logoutLink);
	}

	public SearchProductPage checkSearchBox(String productName) {
		System.out.println("Product Name is ::" + productName);
		eutil.doSendKeys(searchText, productName);
		eutil.doClick(searchBttn);
		return new SearchProductPage(driver);
		// This is also know as Page Chaining and TDD , Bases on the User Stories or fly
		// we are ready
		// creating next page object class.....

	}

}
