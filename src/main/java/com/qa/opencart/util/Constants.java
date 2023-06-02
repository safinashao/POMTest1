package com.qa.opencart.util;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	//variable name declared with constants:
	//a) everything should be in caps separated with underscore format
	//application specific values
	
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	public static final int ACCOUNT_PAGE_HEADERS_COUNT = 4;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "Account";
	
	public static final String LOGIN_ACCOUNTS_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";
	
	public static final List<String> EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			                                                                             "My Affiliate Account", "Newsletter");
	public static final String USER_REG_SUCCESS_MESSAGE = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "registar";
	
	
	
//TEST DATA: Excel/DB
//ENV DATA/CONFIGURATION DATA: PROPERTIES FILE
//CONSTANTS: STATIC FINAL 
	
	
	
	
	
	
	
	
	
	
}
