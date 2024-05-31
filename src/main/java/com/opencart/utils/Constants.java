package com.opencart.utils;

public class Constants {

    public static final String BASE_URL = "https://awesomeqa.com/ui/";
    public static final String BROWSER = "Chrome";
    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 10;

    // Login Data
    public static final String EMAIL_PASSWORD_NO_MATCHING_MSG = "Warning: No match for E-Mail Address and/or Password.";
    public static final String VALID_USERNAME = "satheesh.it47@gmail.com";
    public static final String VALID_PASSWORD = "Tester@2024";

    // Registration Data
    public static final String FIRST_NAME = "Abcdef";
    public static final String LAST_NAME = "Fedcba";
    public static final String TELEPHONE = "1234567890";
    public static final String ACCOUNT_CREATED_MSG = "Your Account Has Been Created!";
    public static final String ACCOUNT_ALREADY_REGISTERED_MSG = "Warning: E-Mail Address is already registered!";
    public static final String PRIVATE_POLICY_WARNING_MSG = "Warning: You must agree to the Privacy Policy!";
    public static final String FIRST_NAME_WARNING_MSG = "First Name must be between 1 and 32 characters!";
    public static final String LAST_NAME_WARNING_MSG = "Last Name must be between 1 and 32 characters!";
    public static final String EMAIL_WARNING_MSG = "E-Mail Address does not appear to be valid!";
    public static final String TELEPHONE_WARNING_MSG = "Telephone must be between 3 and 32 characters!";
    public static final String PASSWORD_WARNING_MSG = "Password must be between 4 and 20 characters!";

    // Search Product data
    public static final String VALID_PRODUCT = "HP";
    public static final String INVALID_PRODUCT = "Honda";
    public static final String NO_PRODUCT_SEARCH_RESULTS_MSG = "There is no product that matches the search criteria.";

    // Extent Report
    public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport_";

}
