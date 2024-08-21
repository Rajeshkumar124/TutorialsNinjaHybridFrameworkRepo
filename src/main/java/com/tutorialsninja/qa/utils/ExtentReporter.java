package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReports() {
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreports.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorialninja test reports");
		sparkReporter.config().setDocumentTitle("TN automtion report ");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		Properties prop = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fisConfigProp;
		try {
			fisConfigProp = new FileInputStream(configPropFile);
			prop.load(fisConfigProp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application url", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser name", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Email", prop.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating system", System.getProperty("os.name"));
		extentReport.setSystemInfo("User name ", System.getProperty("user.name"));
		extentReport.setSystemInfo("java version", System.getProperty("java.version"));
		return extentReport;
	}

}
