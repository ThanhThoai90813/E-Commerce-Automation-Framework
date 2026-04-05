package com.mystore.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
	
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static void setExtent() {
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport.html");
		try {
			sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//htmlReporter.config().setDocumentTitle("Automation Test Report");
		//htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
		//htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "E-Commerce Automation Framework");
		extent.setSystemInfo("Tester", "ThoaiDT");
		extent.setSystemInfo("OS", "Win11");
		extent.setSystemInfo("Browser", "Chrome");
	}
	public static void endReport() {
		extent.flush();
	}
}
