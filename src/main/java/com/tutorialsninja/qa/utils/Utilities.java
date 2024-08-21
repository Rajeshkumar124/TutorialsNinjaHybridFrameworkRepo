package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT = 15;
	public static final int PAGELOAD_TIME = 10;
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp=  date.toString().replace(" ", "_").replace(":", "_");
		return "rajeshkumar"+timeStamp+"@gmail.com";
	}
	
	public static Object[][] getDataFromExcelSheet(String sheetName) {
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\Tutorialsninja.xlsx");
		XSSFWorkbook workBook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workBook = new XSSFWorkbook(fisExcel);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workBook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data =new  Object[rows][cols];
		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType dataType= cell.getCellType();
			switch (dataType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] =Integer.toString( (int)(cell.getNumericCellValue()));
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
					}
				}				
			}
		return data;
		}
	
	public static String captureScreenShot(WebDriver driver, String testName) {
		File srcSourceScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenShotPath =System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png";
		try {
			FileHandler.copy(srcSourceScreenShot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenShotPath;
	}
		
}
