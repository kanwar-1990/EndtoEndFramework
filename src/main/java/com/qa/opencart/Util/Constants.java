package com.qa.opencart.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String Login_Page_Title = "Account Login123";
	public static final int TimeOut = 2;
	public static final String Login_Page_Url_Fraction = "route=account/login";
	public static final String Accounts_Page_Title = "My Account";
	public static final String Accounts_Page_Header = "Your Store";
	public static final String LOGIN_PAGE_ERR = "Warning: No match for E-Mail Address and/or Password.";

	public static List<String> getMatchingList() {

		 return new ArrayList<String>(
				Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter"));
		

		
		/*List<String> sectionList = new ArrayList<String>(
				Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter"));
		return sectionList;
*/
	}

}
