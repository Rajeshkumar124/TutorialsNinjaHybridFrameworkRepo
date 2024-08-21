package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
	
		if(browserName.equalsIgnoreCase("chrome")){
			 driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
			driver.get(prop.getProperty("url"));
			driver.findElement(By.xpath("//span[text()='My Account']")).click();
			driver.findElement(By.linkText("Login")).click();
			return driver;
	}
	
	public  Base() {
		 prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e){
			e.printStackTrace();
		}
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream datFis = new FileInputStream(dataPropFile);
			dataProp.load(datFis);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
		
	

}
