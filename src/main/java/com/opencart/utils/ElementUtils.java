package com.opencart.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils {
    WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementDisplayed(WebElement element, long durationInSeconds) {
        try {
            WebElement webElement = waitForVisibilityOfElement(element,durationInSeconds);
            return webElement.isDisplayed();
        }catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public WebElement waitForVisibilityOfElement(WebElement element,long durationInSeconds) {
        WebElement webElement = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
            webElement = wait.until(ExpectedConditions.visibilityOf(element));
        }catch (Throwable e){
            e.printStackTrace();
        }
        return webElement;
    }

}