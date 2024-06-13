package com.opencart.pages;

import com.opencart.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ElementUtils {
    private WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddressTxtField;
    @FindBy(id = "input-password")
    private WebElement passwordTxtField;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement loginBtn;
    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
    private WebElement emailPasswordNotMatchingWarning;

    public LoginPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public AccountPage login(String emailText, String passwordText) {
        typeTextIntoElement(emailAddressTxtField,emailText,5);
        typeTextIntoElement(passwordTxtField,passwordText,5);
        clickOnElement(loginBtn,10);
        return new AccountPage(driver);
    }
    public void enterEmailAddress(String text) {
        typeTextIntoElement(emailAddressTxtField,text,5);
    }
    public void enterPassword(String text) {
        typeTextIntoElement(passwordTxtField,text,5);
    }
    public AccountPage clickLoginButton() {
        clickOnElement(loginBtn,10);
        return new AccountPage(driver);
    }
    public String retrieveEmailPasswordNotMatchingWarningMessageText() {
        return getTextFromElement(emailPasswordNotMatchingWarning,5);
    }
}