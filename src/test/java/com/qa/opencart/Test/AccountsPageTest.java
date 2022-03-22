package com.qa.opencart.Test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Util.Constants;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accountsPageSetup() {
		accountspage = loginpage.DologinCheck(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 1)
	public void checkAccPageTittle() {
		String AccTittle = accountspage.getAccountsPageTitle();
		System.out.println("Accounts Page title is :: " + AccTittle);
		Assert.assertEquals(AccTittle, Constants.Accounts_Page_Title);
	}

	@Test(priority = 2)
	public void checkAccPageHeader() {
		Assert.assertTrue(accountspage.getAccountsPageHeader());
	}

	@Test(priority = 3)
	public void checkAccPageSectionsHeadersTest() {
		List<String> headerslist = accountspage.getSectionsHeader();
		Assert.assertEquals(headerslist, Constants.getMatchingList());
	}

	@Test(priority = 4)
	public void checkLogoutLinkTest() {
		Assert.assertTrue(accountspage.getLogoutLink());
	}

	/**
	 * Create test Data for products
	 */
	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "MacBook"}, { "Apple" },{"Imac" }};
	}

	@Test(priority = 5, dataProvider = "productData")
	public void checkSearchResultTest(String productName) {
		searchproductpage = accountspage.checkSearchBox(productName);
		Assert.assertTrue(searchproductpage.getSearchList() > 0);

	}

	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { {"MacBook","MacBook Pro"}, {"Apple","Apple Cinema 30\""},
				{ "Samsung","Samsung Galaxy Tab 10.1"} };
	}

	@Test(priority = 6, dataProvider = "getProductName")
	public void selectProductFromList(String productName, String mainProductName) {
		searchproductpage = accountspage.checkSearchBox(productName);
		productinfopage = searchproductpage.selectFromProductList(mainProductName);

		Assert.assertEquals(productinfopage.getProductHeader(), mainProductName);
	}

}
