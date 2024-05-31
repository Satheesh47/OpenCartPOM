package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement myAccountDropMenu;
    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginOption;
    @FindBy(xpath = "//a[text()='Register']")
    private WebElement registerOption;
    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchBoxField;
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchBtn;
    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public SearchPage clickOnSearchButton() {
        searchBtn.click();
        return new SearchPage(driver);
    }

    public SearchPage searchForAProduct(String text) {
        searchBoxField.sendKeys(text);
        searchBtn.click();
        return new SearchPage(driver);
    }

    public void clickOnMyAccount() {
        myAccountDropMenu.click();
    }

    public LoginPage navigateToLoginPage() {
        myAccountDropMenu.click();
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegisterPage() {
        myAccountDropMenu.click();
        registerOption.click();
        return new RegisterPage(driver);
    }

    public LoginPage selectLoginOption() {
        loginOption.click();
        return new LoginPage(driver);
    }
    public RegisterPage selectRegisterOption() {
        registerOption.click();
        return new RegisterPage(driver);
    }

    public void enterProductIntoSearchBoxField(String text) {
        searchBoxField.sendKeys(text);
    }


}
