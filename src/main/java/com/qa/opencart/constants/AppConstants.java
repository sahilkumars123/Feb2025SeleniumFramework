package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	public static final String REGISTER_PAGE_HEADER="Register Account";
	
	
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final List<String> EXPECTED_ACC_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	
	//***************** DEFAULT TIMEOUT VALUES **********************//
	
	public static final int SHORT_TIME_OUT = 5;
	public static final int MEDIUM_TIME_OUT = 10;
	public static final int LONG_TIME_OUT = 15;

}
