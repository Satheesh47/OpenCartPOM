package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddressTxtField;
    @FindBy(id = "input-password")
    private WebElement passwordTxtField;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement loginBtn;
    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
    private WebElement emailPasswordNotMatchingWarning;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AccountPage login(String emailText, String passwordText) {
        emailAddressTxtField.sendKeys(emailText);
        passwordTxtField.sendKeys(passwordText);
        loginBtn.click();
        return new AccountPage(driver);
    }

    public void enterEmailAddress(String text) {
        emailAddressTxtField.sendKeys(text);
    }

    public void enterPassword(String text) {
        passwordTxtField.sendKeys(text);
    }

    public AccountPage clickLoginButton() {
        loginBtn.click();
        return new AccountPage(driver);
    }

    public String retrieveEmailPasswordNotMatchingWarningMessageText() {
        return emailPasswordNotMatchingWarning.getText();
    }


}
