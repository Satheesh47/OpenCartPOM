package com.opencart.pages;

import com.opencart.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends ElementUtils {
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
    public HomePage(final WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public SearchPage clickOnSearchButton() {
        clickOnElement(searchBtn,5);
        return new SearchPage(driver);
    }
    public SearchPage searchForAProduct(String text) {
        typeTextIntoElement(searchBoxField,text,5);
        clickOnElement(searchBtn,5);
        return new SearchPage(driver);
    }
    public void clickOnMyAccount() {
        clickOnElement(myAccountDropMenu,5);
    }
    public LoginPage navigateToLoginPage() {
        clickOnElement(myAccountDropMenu,5);
        clickOnElement(loginOption,5);
        return new LoginPage(driver);
    }
    public RegisterPage navigateToRegisterPage() {
        clickOnElement(myAccountDropMenu,5);
        clickOnElement(registerOption,5);
        return new RegisterPage(driver);
    }
    public LoginPage selectLoginOption() {
        clickOnElement(loginOption,5);
        return new LoginPage(driver);
    }
    public RegisterPage selectRegisterOption() {
        clickOnElement(registerOption,5);
        return new RegisterPage(driver);
    }
    public void enterProductIntoSearchBoxField(String text) {
        typeTextIntoElement(searchBoxField,text,5);
    }
}
