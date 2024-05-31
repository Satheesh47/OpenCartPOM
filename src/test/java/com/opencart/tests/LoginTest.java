package com.opencart.tests;

import com.opencart.base.Base;
import com.opencart.pages.AccountPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
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

public class LoginTest extends Base {
    public WebDriver driver;
    LoginPage loginPage;
    AccountPage accountPage;

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpen(BROWSER);
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.navigateToLoginPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1,dataProvider = "validCredentialSupplier")
    public void verifyLoginWithValidCredentials(String email,String password) {
        accountPage = loginPage.login(email,password);
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
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        loginPage.login(generateEmailWithTimeStamp(),VALID_PASSWORD);
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_NO_MATCHING_MSG), "Warning message is not displayed");
    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        loginPage.login(VALID_USERNAME,generateTimeStamp());
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_NO_MATCHING_MSG), "Warning message is not displayed");
    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials() {
        loginPage.login("","");
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().
                contains(EMAIL_PASSWORD_NO_MATCHING_MSG), "Warning message is not displayed");
    }

//    @Test(priority = 6)
//    public void failTest() {
//        Assert.assertTrue(false);
//    }
}