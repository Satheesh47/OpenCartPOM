package com.opencart.pages;

import com.opencart.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends ElementUtils {
    WebDriver driver;
    @FindBy(linkText = "Edit your account information")
    private WebElement editYourAccountInformationOption;
    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public boolean getDisplayedStatusOfEditYourAccountInformationOption() {
        return isElementDisplayed(editYourAccountInformationOption,10);
    }
}
