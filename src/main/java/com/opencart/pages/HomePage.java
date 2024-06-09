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
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public SearchPage clickOnSearchButton() {
        clickOnElement(searchBtn,10);
        return new SearchPage(driver);
    }

    public SearchPage searchForAProduct(String text) {
        typeTextIntoElement(searchBoxField,text,10);
        clickOnElement(searchBtn,10);
        return new SearchPage(driver);
    }
    public void clickOnMyAccount() {
        myAccountDropMenu.click();
    }

    public LoginPage navigateToLoginPage() {
        clickOnElement(myAccountDropMenu,10);
        clickOnElement(loginOption,10);
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegisterPage() {
        clickOnElement(myAccountDropMenu,10);
        clickOnElement(registerOption,10);
        return new RegisterPage(driver);
    }
    public LoginPage selectLoginOption() {
        clickOnElement(loginOption,10);
        return new LoginPage(driver);
    }
    public RegisterPage selectRegisterOption() {
        clickOnElement(registerOption,10);
        return new RegisterPage(driver);
    }

    public void enterProductIntoSearchBoxField(String text) {
        typeTextIntoElement(searchBoxField,text,10);
    }
}
