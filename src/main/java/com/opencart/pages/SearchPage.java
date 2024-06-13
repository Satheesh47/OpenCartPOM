package com.opencart.pages;

import com.opencart.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends ElementUtils {
    @FindBy(linkText = "HP LP3065")
    private WebElement validHPProduct;
    @FindBy(xpath = "//h2[contains(text(),'Products meeting the search criteria')]/following-sibling::p")
    private WebElement noProductMessage;
    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public boolean displayStatusOfHPValidProduct() {
        return validHPProduct.isDisplayed();
    }
    public String retrieveNoProductMessageText() {
        return noProductMessage.getText();
    }
}