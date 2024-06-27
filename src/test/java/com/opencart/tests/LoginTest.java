package com.opencart.tests;

import com.aventstack.extentreports.Status;
import com.opencart.base.BaseTest;
import com.opencart.pages.AccountPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
import com.opencart.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.opencart.utils.Utilities.*;
import static com.opencart.utils.Constants.*;
import static com.opencart.utils.Utilities.generateTimeStamp;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private AccountPage accountPage;

    @BeforeMethod
    public void setUp() {
        HomePage homePage = new HomePage(initializeBrowserAndOpen(BROWSER));
        loginPage = homePage.navigateToLoginPage();
    }
    @Test(priority = 1,dataProvider = "validCredentialSupplier")
    public void verifyLoginWithValidCredentials(String email,String password) {
        accountPage = loginPage.login(email,password);
        ExtentManager.getExtentTest().log(Status.INFO,"Logged in successfully with Email: "+email+"; Password: "+password);
        Assert.assertTrue(accountPage.getDisplayedStatusOfEditYourAccountInformationOption(),
                "Edit your account information is not displayed");
    }

    @DataProvider(name="validCredentialSupplier")
    public Object[][] supplyTestData() throws IOException {
//        Object[][] data = {{"satheesh.it47@gmail.com","Tester@2024"},
//                {"satheesh.it48@gmail.com","Tester@2024"},
//                {"satheesh.it49@gmail.com","Tester@2024"}};
        Object[][] data = getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.login(generateEmailWithTimeStamp(),generateTimeStamp());
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_NO_MATCHING_MSG), "Warning message is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Log is not successful!!");
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        loginPage.login(generateEmailWithTimeStamp(),VALID_PASSWORD);
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_NO_MATCHING_MSG), "Warning message is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Log is not successful!!");
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        loginPage.login(VALID_USERNAME,generateTimeStamp());
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_NO_MATCHING_MSG), "Warning message is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Log is not successful!!");
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {
        loginPage.login("","");
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_BLANK_WARNING_MSG), "Warning message is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Log is not successful!!");
    }
}