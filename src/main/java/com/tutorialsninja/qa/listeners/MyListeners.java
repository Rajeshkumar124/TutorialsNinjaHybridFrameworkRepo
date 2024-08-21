package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;



public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	@Override
	public void onStart(ITestContext context) {
		 extentReport = ExtentReporter.generateExtentReports();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		 testName = result.getName();
		 extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, testName +" Started execution");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 
		extentTest.log(Status.INFO, result.getName() +" successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		 
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destinationScreenShotPath=Utilities.captureScreenShot(driver, result.getName());
		System.out.println(destinationScreenShotPath);
		//extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath, "Failed");
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.INFO, result.getName() +" got failed");	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() +" got skipped");
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreports.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
