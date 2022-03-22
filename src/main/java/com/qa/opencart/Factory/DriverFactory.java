package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager om;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * intializing driver and returning properties file
	 * 
	 * @param prop
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {

		String BrowserName = prop.getProperty("browser");
		om = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		System.out.println("Starting the Browser ::---> " + BrowserName);
		if (BrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(om.chromeOptions());
			// Intializing local Driver
			tlDriver.set(new ChromeDriver(om.chromeOptions()));

		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(om.firefoxOptions());
			tlDriver.set(new FirefoxDriver(om.firefoxOptions()));
		} else if (BrowserName.equalsIgnoreCase("Safari")) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please check the BrowserName :: " + BrowserName);
		}
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * Creating a method to return thread local driver copy
	 * 
	 * @return
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		FileInputStream ip = null;

		prop = new Properties();
		String envName = System.getProperty("env");
		try {
			if (envName == null) {

				System.out.println("Running on prod env......");
				ip = new FileInputStream("./src/main/resources/Config/Config.properties");

			} else {
				System.out.println("Running on below Env ::--->" + envName);
				switch (envName.toLowerCase()) {

				case "dev":
					ip = new FileInputStream("./src/main/resources/Config/dev.config.properties");

					break;
				case "stage":
					ip = new FileInputStream("./src/main/resources/Config/stage.config.properties");

					break;

				case "uat":
					ip = new FileInputStream("./src/main/resources/Config/uat.config.properties");
					break;
				default:
					System.out.println("Please check the env variable====>" + envName);
					break;
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * below is the TakeScreenshot method
	 */

	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}

}
