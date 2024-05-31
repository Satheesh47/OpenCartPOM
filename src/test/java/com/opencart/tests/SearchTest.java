package com.opencart.tests;

import com.opencart.base.Base;
import com.opencart.pages.HomePage;
import com.opencart.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.opencart.utils.Constants.*;

public class SearchTest extends Base {
    public WebDriver driver;
    SearchPage searchPage;
    HomePage homePage;
    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpen(BROWSER);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct() {
        searchPage = homePage.searchForAProduct(VALID_PRODUCT);
        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"HP product is not displayed");
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct() {
        searchPage = homePage.searchForAProduct(INVALID_PRODUCT);
        Assert.assertEquals(searchPage.retrieveNoProductMessageText(), NO_PRODUCT_SEARCH_RESULTS_MSG,"No product message in search results is not displayed");
    }

    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct()  {
        searchPage = homePage.clickOnSearchButton();
        Assert.assertEquals(searchPage.retrieveNoProductMessageText(), NO_PRODUCT_SEARCH_RESULTS_MSG,"No product message in search results is not displayed");
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