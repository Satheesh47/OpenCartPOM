package com.opencart.tests;

import com.aventstack.extentreports.Status;
import com.opencart.base.BaseTest;
import com.opencart.pages.AccountSuccessPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.RegisterPage;
import com.opencart.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.opencart.utils.Constants.BROWSER;
import static com.opencart.utils.Constants.*;
import static com.opencart.utils.Utilities.generateEmailWithTimeStamp;

public class RegisterTest extends BaseTest {
    private RegisterPage registerPage;
    private AccountSuccessPage accountSuccessPage;

    @BeforeMethod
    public void setUp() {
        HomePage homePage = new HomePage(initializeBrowserAndOpen(BROWSER));
        registerPage = homePage.navigateToRegisterPage();
    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields() {
        accountSuccessPage = registerPage.registerWithMandatoryFields(FIRST_NAME,LAST_NAME,generateEmailWithTimeStamp(),TELEPHONE,VALID_PASSWORD);
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), ACCOUNT_CREATED_MSG, "Account success page is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Account Registration is successful!!");
    }

    @Test(priority = 2)
    public void verifyRegisteringAccountByProvidingAllFields() {
        accountSuccessPage = registerPage.registerWithAllFields(FIRST_NAME,LAST_NAME,generateEmailWithTimeStamp(),TELEPHONE,VALID_PASSWORD);
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), ACCOUNT_CREATED_MSG, "Account success page is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Account Registration is successful!!");
    }

    @Test(priority = 3)
    public void verifyRegisteringAccountWithExistingEmailAddress() {
        registerPage.registerWithAllFields(FIRST_NAME,LAST_NAME,VALID_USERNAME,TELEPHONE,VALID_PASSWORD);
        Assert.assertEquals(registerPage.retrieveDuplicateEmailAddressWarning(), ACCOUNT_ALREADY_REGISTERED_MSG, "Warning Message is not matched");
        ExtentManager.getExtentTest().log(Status.INFO,"Account Registration is NOT successful due to already existing Email address !!");
    }

    @Test(priority = 4)
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {
        registerPage.clickContinueButton();
        Assert.assertTrue(registerPage.displayStatusOfWarningMessages(PRIVATE_POLICY_WARNING_MSG,FIRST_NAME_WARNING_MSG,LAST_NAME_WARNING_MSG,EMAIL_WARNING_MSG,TELEPHONE_WARNING_MSG,PASSWORD_WARNING_MSG));
        ExtentManager.getExtentTest().log(Status.INFO,"Account Registration is NOT successful as NO DETAILS entered !!");
    }
}
