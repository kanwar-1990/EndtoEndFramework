package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Util.ElementUtil;

public class SearchProductPage {

	private WebDriver driver;
	private ElementUtil eutil;
	private By itemList = By.cssSelector("#content h4");

	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public int getSearchList() {
		int productsize = eutil.getElementsTextList(itemList).size();

		return productsize;
	}

	public ProductInfoPage selectFromProductList(String mainproductName) {
		System.out.println("Main product Name is ::" + mainproductName);
		List<WebElement> elementList = eutil.waitForElementsToBeVisible(itemList, 10, 2000);
		for (WebElement e : elementList) {
			if (e.getText().equals(mainproductName)) {
				System.out.println("Main Product Name is :" + mainproductName);
				e.click();
			}
		}
		return new ProductInfoPage(driver);
	}

}
