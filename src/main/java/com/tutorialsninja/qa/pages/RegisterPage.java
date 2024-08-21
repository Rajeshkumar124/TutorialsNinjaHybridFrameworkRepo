package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	public WebDriver driver;
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id ="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telePHoneNumberFiled;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicy;
	
	@FindBy(xpath="//input[@value=\"Continue\"]")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name=\"newsletter\"][@value=1]")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id=\"input-firstname\"]/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id=\"input-lastname\"]/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id=\"input-email\"]/following-sibling::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath="//input[@id=\"input-telephone\"]/following-sibling::div")
	private WebElement telePhoneWarning;
	
	@FindBy(xpath="//input[@id=\"input-password\"]/following-sibling::div")
	private WebElement passwordWarning;
	
	public String passwordWarning() {
		String passwordWarningMessage =passwordWarning.getText();
		return passwordWarningMessage;
	}
	
	public String telePhoneWarning() {
		String telePhoneWarningMessage =telePhoneWarning.getText();
		return telePhoneWarningMessage;
	}
	public String emailWarning() {
		String emailWarningMessage = emailAddressWarning.getText();
		return emailWarningMessage;
	}
	
	public String lastNameWarning() {
		String lastNameWarningMessgae=lastNameWarning.getText();
		return lastNameWarningMessgae;
	}
	
	public String firstNameWarning() {
		String firstNameWArningMessage = firstNameWarning.getText();
		return firstNameWArningMessage;
	}
	public String retrivePrivacyPolicyWarning() {
		String privacyPolicyWarningMessage = privacyPolicyWarning.getText();
		return privacyPolicyWarningMessage;
	}
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	public void enterEmail(String email) {
		emailAddressField.sendKeys(email);
	}
	
	public void enterTelePhoneNumber(String telePhoneNumber) {
		telePHoneNumberFiled.sendKeys(telePhoneNumber);
	}
	
	public void enterPasword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}
	public void selectPrivacyPolicy() {
		privacyPolicy.click();
	}
	public AccountSuccessPage clickOnContinueButon() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	public String retriveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstName,String lastName, String email, String telePhoneNumber, String passwordText, String confirmPasswordText ) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailAddressField.sendKeys(email);
		telePHoneNumberFiled.sendKeys(telePhoneNumber);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		privacyPolicy.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstName,String lastName, String email, String telePhoneNumber, String passwordText, String confirmPasswordText ) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailAddressField.sendKeys(email);
		telePHoneNumberFiled.sendKeys(telePhoneNumber);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		yesNewsLetterOption.click();
		privacyPolicy.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public boolean displatStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastNameWarning, String expectedEmailWarning,
			String expectedTelePhoneWarning , String expectedPasswordWarning) {
		boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus =firstNameWarning.getText().contains(expectedFirstNameWarning);
		boolean lastNameWarningStatus = lastNameWarning.getText().contains(expectedLastNameWarning);
		boolean emailWarningMessageStatus = emailAddressWarning.getText().contains(expectedEmailWarning);
		boolean telephoneWarningStatus = telePhoneWarning.getText().contains(expectedTelePhoneWarning);
		boolean passwordWarningStatus = passwordWarning.getText().contains(expectedPasswordWarning);
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningMessageStatus && telephoneWarningStatus && passwordWarningStatus;
	}

}
