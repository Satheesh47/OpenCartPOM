package com.opencart.tests;

import com.aventstack.extentreports.Status;
import com.opencart.base.BaseTest;
import com.opencart.pages.HomePage;
import com.opencart.pages.SearchPage;
import com.opencart.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.opencart.utils.Constants.*;

public class SearchTest extends BaseTest {
    private SearchPage searchPage;
    private HomePage homePage;
    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(initializeBrowserAndOpen(BROWSER));
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct() {
        searchPage = homePage.searchForAProduct(VALID_PRODUCT);
        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"HP product is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Valid Product: "+VALID_PRODUCT+" is displayed in Search results!!");
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct() {
        searchPage = homePage.searchForAProduct(INVALID_PRODUCT);
        Assert.assertEquals(searchPage.retrieveNoProductMessageText(), NO_PRODUCT_SEARCH_RESULTS_MSG,"No product message in search results is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"Invalid Product: "+INVALID_PRODUCT+" is NOT displayed in Search results!!");
    }

    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct()  {
        searchPage = homePage.clickOnSearchButton();
        Assert.assertEquals(searchPage.retrieveNoProductMessageText(), NO_PRODUCT_SEARCH_RESULTS_MSG,"No product message in search results is not displayed");
        ExtentManager.getExtentTest().log(Status.INFO,"No product is searched!!");
    }
}