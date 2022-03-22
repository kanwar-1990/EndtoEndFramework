package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] negativeLoginTestData() {

		return new Object[][] { { "xyz@gmail.com", "Kanwar@77" }, { "knwrpl.singh@gmail.com", "xyz@123" }, { "", "" },
				{ "", "Kanwar@77" }, { "knwrpl.singh@gmail.com", "" }

		};
	}

	@Test(dataProvider = "negativeLoginTestData")
	public void checkNegativeScenerioInDoLogin(String username, String password) {

		Assert.assertFalse(loginpage.doLoginWrongCredentials(username, password));

	}

}
