package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtil eutil;
	private By productHeader = By.cssSelector("#content h1");
	private By productImageCount = By.cssSelector("#content img");
	private By productMetaData = By.xpath("//*[@id=\"content\"]/div/div[2]/ul[1]/li");
	private By productPriceMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By productQuantity = By.xpath("//*[@id=\"input-quantity\" and @name='quantity']");
	private By addToCart = By.xpath("//button[@type='button' and @id='button-cart']");
	private LinkedHashMap<String, String> productInfo;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return eutil.doGetText(productHeader);

	}

	public boolean getProductImageCount() {
		if (eutil.getElements(productImageCount).size() > 0)

		{
			return true;
		}
		return false;
	}

	public LinkedHashMap<String, String> getProductCompleteDetails() {
		productInfo = new LinkedHashMap<String, String>();
		productInfo.put("name", getProductHeader());
		getProductInfoData();
		getProductPriceData();
		return productInfo;
	}

	public void getProductInfoData() {

		List<WebElement> productList = eutil.getElements(productMetaData);
		for (WebElement e : productList) {
			String[] prdList = e.getText().split(":");
			String productKey = prdList[0].trim();
			String productValue = prdList[1].trim();
			productInfo.put(productKey, productValue);
		}

	}

	public void getProductPriceData() {
		List<WebElement> priceList = eutil.getElements(productPriceMetaData);
		String productPrice = priceList.get(0).getText().trim();
		String productExPrice = priceList.get(1).getText().trim();
		productInfo.put("Price ::", productPrice);
		productInfo.put("Ex--Price", productExPrice);
	}

}
