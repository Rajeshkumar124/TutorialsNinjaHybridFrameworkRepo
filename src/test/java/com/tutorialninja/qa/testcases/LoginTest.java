package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public WebDriver driver;
	
	LoginPage loginPage;
	public LoginTest() {
		super();
	}
	@BeforeMethod
	public void seUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		//driver =initializeBrowserAndOpenApplication("chrome");
		HomePage homePage = new HomePage(driver);
			loginPage= homePage.navigateToLoginPage();
	}
	@AfterMethod
	public void earDown() {
		driver.quit();
	}
	
	@DataProvider(name ="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		//Object[][] data = {{"rajeshkumarginjupalli@gmail.com","Rajesh@123"},{"rajeshkumarginjupalli@gmail.com","Rajesh@123"}};
		Object[][] data = Utilities.getDataFromExcelSheet("Login");
		return data;
	}
	
	@Test(priority = 1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edt your information is not displayed");
		
	}
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {	
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPasword"));
		String actualWarningMEssage = loginPage.retriveEmailPasswordWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("expectedWarningMessage");
		Assert.assertTrue(actualWarningMEssage.contains(expectedWarningMessage), "actualessage is not equal to expected warning message");
	}
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		/*loginPage.enterEmailText(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();*/
		String actualWarningMEssage = loginPage.retriveEmailPasswordWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("expectedWarningMessage");
		Assert.assertTrue(actualWarningMEssage.contains(expectedWarningMessage), "actualessage is not equal to expected warning message");		
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPasswod() {
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPasword"));
	/*	loginPage.enterEmailText(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPasword"));
		loginPage.clickOnLoginButton(); */
		String actualWarningMEssage = loginPage.retriveEmailPasswordWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("expectedWarningMessage");
		Assert.assertTrue(actualWarningMEssage.contains(expectedWarningMessage), "actualessage is not equal to expected warning message");
	}
	@Test(priority = 5)
	public void verifyLoginWithEmptyEmailAndEmptyPassword() {
		
		loginPage.clickOnLoginButton();
		String actualWarningMEssage = loginPage.retriveEmailPasswordWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("expectedWarningMessage");
		Assert.assertTrue(actualWarningMEssage.contains(expectedWarningMessage), "actualessage is not equal to expected warning message");		
	}
	

}