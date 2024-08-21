package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public WebDriver driver;
	RegisterPage regPage;
	AccountSuccessPage accountSuccessPage;
	public RegisterTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		regPage = homePage.navigateToRegisterOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		accountSuccessPage =regPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualHeading = accountSuccessPage.retriveAccountSuccessPageHEading();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account success page is not displayed");
		
	}
	
	@Test(priority = 2)
 	public void verifyRegisteringAccountByProvdingAllFields() {
		accountSuccessPage= regPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		String actualHeading = accountSuccessPage.retriveAccountSuccessPageHEading();
		Assert.assertEquals(actualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account success page is not displayed");
		
	}
	@Test(priority = 3)
	public void verifyRegesteringAccountWithExistingEmailAddress() {
		 
		regPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		String actualWarning = regPage.retriveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarningMessage")), "Warning message regardng dupicate email is not displaying");
		
	}
	
	@Test(priority = 4)
	public void verifyReesteringAccountWithoutAnyMandatoryFields() {
	
		regPage.clickOnContinueButon();
		//String actualPrivacyPoicyWarning =regPage.retrivePrivacyPolicyWarning();
		Assert.assertTrue(regPage.retrivePrivacyPolicyWarning().contains(dataProp.getProperty("privacyWarning")), "privacy policy message is not displaying correctly");
		//String actualFirstNameWarning = regPage.firstNameWarning();
		Assert.assertEquals(regPage.firstNameWarning(), dataProp.getProperty("firstNameWarning"),"First name warning message not displaying");
		//String actualLastNameWarning = regPage.lastNameWarning();
		Assert.assertEquals(regPage.lastNameWarning(), dataProp.getProperty("lastNameWarning") , "last name warning message is not displaying");
		//String actualEmailWarningMessage = regPage.emailWarning();
		Assert.assertEquals(regPage.emailWarning(), dataProp.getProperty("emailWarning"), "email warning message is not displaying");
		//String actualTelephoneWarningMessage = regPage.telePhoneWarning();
		Assert.assertEquals(regPage.telePhoneWarning(), dataProp.getProperty("telePhoneWarning"), "telephone warning message is not displaying");
		//String actualPasswordWarningMessage = regPage.passwordWarning();
		Assert.assertEquals( regPage.passwordWarning(), dataProp.getProperty("passwordWarning"), "Password warning message is not displaying");
		
		/*Assert.assertEquals(regPage.displatStatusOfWarningMessages(dataProp.getProperty("privacyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning")
				, dataProp.getProperty("emailWarning"), dataProp.getProperty("telePhoneWarning"), dataProp.getProperty("passwordWarning")), "warning messages not displayed properly");
	*/
		
	}
	

}
