package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class ,\"alert-dismissible\")]")
	private WebElement emailPasswordNotMatchingWarning;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailText(String emailID) {
		emailAddressField.sendKeys(emailID);
	}
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	public AccountPage login(String emailID, String password) {
		emailAddressField.sendKeys(emailID);
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEmailPasswordWarningMessageText() {
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	
	}
