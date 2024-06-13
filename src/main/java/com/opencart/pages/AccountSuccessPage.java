package com.opencart.pages;

import com.opencart.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage extends ElementUtils {
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement accountSuccessPageHeading;
    public AccountSuccessPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public String retrieveAccountSuccessPageHeading() {
        return getTextFromElement(accountSuccessPageHeading,10);
    }
}
