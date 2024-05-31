package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;
    @FindBy(id = "input-firstname")
    private WebElement firstnameTxtField;
    @FindBy(id = "input-lastname")
    private WebElement lastnameTxtField;
    @FindBy(id = "input-email")
    private WebElement emailAddressTxtField;
    @FindBy(id = "input-telephone")
    private WebElement telephoneTxtField;
    @FindBy(id = "input-password")
    private WebElement passwordTxtField;
    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmTxtField;
    @FindBy(xpath = "//*[@name='agree']")
    private WebElement privacyPolicyChkBox;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement continueBtn;
    @FindBy(xpath = "//input[@name='newsletter' and @value='1']")
    private WebElement yesNewsLetterOption;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement duplicateEmailAddressWarning;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement privacyPolicyWarning;
    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;
    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;
    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailAddressWarning;
    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;
    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String retrievePasswordWarning() {
        return passwordWarning.getText();
    }
    public String retrieveTelephoneWarning() {
        return telephoneWarning.getText();
    }
    public String retrieveEmailAddressWarning() {
        return emailAddressWarning.getText();
    }
    public String retrieveLastNameWarning() {
        return lastNameWarning.getText();
    }
    public String retrieveFirstNameWarning() {
        return firstNameWarning.getText();
    }

    public String retrievePrivacyPolicyWarning() {
        return privacyPolicyWarning.getText();
    }
    public void enterFirstName(String text) {
        firstnameTxtField.sendKeys(text);
    }
    public void enterLastName(String text) {
        lastnameTxtField.sendKeys(text);
    }
    public void enterEmailAddress(String text) {
        emailAddressTxtField.sendKeys(text);
    }
    public void enterTelephone(String text) {
        telephoneTxtField.sendKeys(text);
    }
    public void enterPassword(String text) {
        passwordTxtField.sendKeys(text);
    }
    public void enterConfirmPassword(String text) {
        passwordConfirmTxtField.sendKeys(text);
    }
    public void selectPrivacyPolicyCheckBox() {
        privacyPolicyChkBox.click();
    }
    public AccountSuccessPage clickContinueButton() {
        continueBtn.click();
        return new AccountSuccessPage(driver);
    }
    public void selectYesNewsLetterOption() {
        yesNewsLetterOption.click();
    }
    public String retrieveDuplicateEmailAddressWarning() {
        return duplicateEmailAddressWarning.getText();
    }

    public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
        firstnameTxtField.sendKeys(firstNameText);
        lastnameTxtField.sendKeys(lastNameText);
        emailAddressTxtField.sendKeys(emailText);
        telephoneTxtField.sendKeys(telephoneText);
        passwordTxtField.sendKeys(passwordText);
        passwordConfirmTxtField.sendKeys(passwordText);
        privacyPolicyChkBox.click();
        continueBtn.click();
        return new AccountSuccessPage(driver);
    }

    public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
        firstnameTxtField.sendKeys(firstNameText);
        lastnameTxtField.sendKeys(lastNameText);
        emailAddressTxtField.sendKeys(emailText);
        telephoneTxtField.sendKeys(telephoneText);
        passwordTxtField.sendKeys(passwordText);
        passwordConfirmTxtField.sendKeys(passwordText);
        yesNewsLetterOption.click();
        privacyPolicyChkBox.click();
        continueBtn.click();
        return new AccountSuccessPage(driver);
    }

    public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarningText,String expectedFirstNameWarningText,String expectedLastNameWarningText,String expectedEmailWarningText,String expectedTelephoneWarningText,String expectedPasswordWarningText) {
        boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarningText);
        boolean firstNameWarningStatus = firstNameWarning.getText().contains(expectedFirstNameWarningText);
        boolean lastNameWarningStatus = lastNameWarning.getText().contains(expectedLastNameWarningText);
        boolean emailAddressWarningStatus = emailAddressWarning.getText().contains(expectedEmailWarningText);
        boolean telephoneWarningStatus = telephoneWarning.getText().contains(expectedTelephoneWarningText);
        boolean passwordWarningStatus = passwordWarning.getText().contains(expectedPasswordWarningText);
        return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailAddressWarningStatus && telephoneWarningStatus && passwordWarningStatus;
    }
}