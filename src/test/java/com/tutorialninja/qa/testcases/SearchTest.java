package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

//Updated comment
public class SearchTest extends Base {
	
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		 homePage = new HomePage(driver);
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		//homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		//searchPage = homePage.clickOnSearchButton();
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatsOfHPProduct(),"valid product not displayed");
	}
	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("inValidProduct"));
		homePage.searchForAProduct(dataProp.getProperty("inValidProduct"));
		//String actualSearchText = searchPage.retriveNoProductMessageText();
		Assert.assertEquals(searchPage.retriveNoProductMessageText(), "abcd" , "No product in search result is not ddisplayed");
		}
	
	@Test(priority =3, dependsOnMethods = {"verifySearchWithInValidProduct"} )
	public void verifySearchWithoutAnyProduct() {
		
		searchPage = homePage.clickOnSearchButton();
		String actualSearchText = searchPage.retriveNoProductMessageText();
		Assert.assertEquals(actualSearchText, dataProp.getProperty("noProductText") , "No product in search result is not ddisplayed");
		}

}
