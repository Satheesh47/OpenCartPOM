package com.opencart.pages;

import com.opencart.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends ElementUtils {
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
    public RegisterPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String retrievePasswordWarning() {
        return getTextFromElement(passwordWarning,5);
    }
    public String retrieveTelephoneWarning() {
        return getTextFromElement(telephoneWarning,5);
    }
    public String retrieveEmailAddressWarning() {
        return getTextFromElement(emailAddressWarning,5);
    }
    public String retrieveLastNameWarning() {
        return getTextFromElement(lastNameWarning,5);
    }
    public String retrieveFirstNameWarning() {
        return getTextFromElement(firstNameWarning,5);
    }
    public String retrievePrivacyPolicyWarning() {
        return getTextFromElement(privacyPolicyWarning,5);
    }
    public void enterFirstName(String text) {
        typeTextIntoElement(firstnameTxtField,text,5);
    }
    public void enterLastName(String text) {
        typeTextIntoElement(lastnameTxtField,text,5);
    }
    public void enterEmailAddress(String text) {
        typeTextIntoElement(emailAddressTxtField,text,5);
    }
    public void enterTelephone(String text) {
        typeTextIntoElement(telephoneTxtField,text,5);
    }
    public void enterPassword(String text) {
        typeTextIntoElement(passwordTxtField,text,5);
    }
    public void enterConfirmPassword(String text) {
        typeTextIntoElement(passwordConfirmTxtField,text,5);
    }
    public void selectPrivacyPolicyCheckBox() {
        clickOnElement(privacyPolicyChkBox,10);
    }
    public AccountSuccessPage clickContinueButton() {
        clickOnElement(continueBtn,10);
        return new AccountSuccessPage(driver);
    }
    public void selectYesNewsLetterOption() {
        clickOnElement(yesNewsLetterOption,10);
    }
    public String retrieveDuplicateEmailAddressWarning() {
        return getTextFromElement(duplicateEmailAddressWarning,5);
    }

    public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
        typeTextIntoElement(firstnameTxtField,firstNameText,5);
        typeTextIntoElement(lastnameTxtField,lastNameText,5);
        typeTextIntoElement(emailAddressTxtField,emailText,5);
        typeTextIntoElement(telephoneTxtField,telephoneText,5);
        typeTextIntoElement(passwordTxtField,passwordText,5);
        typeTextIntoElement(passwordConfirmTxtField,passwordText,5);
        clickOnElement(privacyPolicyChkBox,10);
        clickOnElement(continueBtn,10);
        return new AccountSuccessPage(driver);
    }

    public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
        typeTextIntoElement(firstnameTxtField,firstNameText,5);
        typeTextIntoElement(lastnameTxtField,lastNameText,5);
        typeTextIntoElement(emailAddressTxtField,emailText,5);
        typeTextIntoElement(telephoneTxtField,telephoneText,5);
        typeTextIntoElement(passwordTxtField,passwordText,5);
        typeTextIntoElement(passwordConfirmTxtField,passwordText,5);
        clickOnElement(yesNewsLetterOption,5);
        clickOnElement(privacyPolicyChkBox,10);
        clickOnElement(continueBtn,10);
        return new AccountSuccessPage(driver);
    }
    public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarningText,String expectedFirstNameWarningText,String expectedLastNameWarningText,String expectedEmailWarningText,String expectedTelephoneWarningText,String expectedPasswordWarningText) {
        boolean privacyPolicyWarningStatus = getTextFromElement(privacyPolicyWarning,5).contains(expectedPrivacyPolicyWarningText);
        boolean firstNameWarningStatus = getTextFromElement(firstNameWarning,5).contains(expectedFirstNameWarningText);
        boolean lastNameWarningStatus = getTextFromElement(lastNameWarning,5).contains(expectedLastNameWarningText);
        boolean emailAddressWarningStatus = getTextFromElement(emailAddressWarning,5).contains(expectedEmailWarningText);
        boolean telephoneWarningStatus = getTextFromElement(telephoneWarning,5).contains(expectedTelephoneWarningText);
        boolean passwordWarningStatus = getTextFromElement(passwordWarning,5).contains(expectedPasswordWarningText);
        return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailAddressWarningStatus && telephoneWarningStatus && passwordWarningStatus;
    }
}