package com.opencart.tests;

import com.opencart.base.Base;
import com.opencart.pages.AccountSuccessPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.opencart.utils.Constants.BROWSER;
import static com.opencart.utils.Constants.*;
import static com.opencart.utils.Utilities.generateEmailWithTimeStamp;

public class RegisterTest extends Base {
    public WebDriver driver;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpen(BROWSER);
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegisterPage();
    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields() {
        accountSuccessPage = registerPage.registerWithMandatoryFields(FIRST_NAME,LAST_NAME,generateEmailWithTimeStamp(),TELEPHONE,VALID_PASSWORD);
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), ACCOUNT_CREATED_MSG, "Account success page is not displayed");
    }

    @Test(priority = 2)
    public void verifyRegisteringAccountByProvidingAllFields() {
        accountSuccessPage = registerPage.registerWithAllFields(FIRST_NAME,LAST_NAME,generateEmailWithTimeStamp(),TELEPHONE,VALID_PASSWORD);
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), ACCOUNT_CREATED_MSG, "Account success page is not displayed");
    }

    @Test(priority = 3)
    public void verifyRegisteringAccountWithExistingEmailAddress() {
        registerPage.registerWithAllFields(FIRST_NAME,LAST_NAME,VALID_USERNAME,TELEPHONE,VALID_PASSWORD);
        Assert.assertEquals(registerPage.retrieveDuplicateEmailAddressWarning(), ACCOUNT_ALREADY_REGISTERED_MSG, "Warning Message is not matched");
    }

    @Test(priority = 4)
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {
        registerPage.clickContinueButton();
        Assert.assertTrue(registerPage.displayStatusOfWarningMessages(PRIVATE_POLICY_WARNING_MSG,FIRST_NAME_WARNING_MSG,LAST_NAME_WARNING_MSG,EMAIL_WARNING_MSG,TELEPHONE_WARNING_MSG,PASSWORD_WARNING_MSG));
    }

//    @Test(priority = 6)
//    public void failTest() {
//        Assert.assertTrue(false);
//    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
