package com.magnitia.Batch29_Reports;

import java.util.HashMap;
import java.util.Map;

import com.magnitia.Batch29_Constatns.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	// Variables
	public static ExtentReports  reports = new ExtentReports(Constant.ReportsPath,false);
	public static ExtentTest test;
	
	//startTest
	public static void startTest(String testCaseName, String desc) {
		 test = reports.startTest(testCaseName, desc);
		 test.assignAuthor("Padmaja");
		 test.assignCategory("Smoke test");
		 addSystemInfo();		
	}

	public static void addSystemInfo() {
		Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("Selenium Verison", "3.141.0");
		reports.addSystemInfo(sysInfo);		
	}
	
	public static void info(String StepName,String details) {
		test.log(LogStatus.INFO,StepName, details);
	}
	
	public static void warn(String StepName,String details) {
		test.log(LogStatus.WARNING,StepName, "<span stype ='font-weight:bold; red'>"+details+"</span>");
	}
	
	public static void pass(String StepName,String details) {
		test.log(LogStatus.PASS,StepName, details);
	}
	
	public static void fail(String StepName,String details, String imagePath) {
		test.log(LogStatus.FAIL,StepName, details + test.addScreenCapture(imagePath));
	}
	
	public static void fatal(String StepName, String details) {
		test.log(LogStatus.FATAL,StepName, details);
	}
	
	public static void endTest() {
	reports.endTest(test);
	}
	
	
	public static void flush() {
		reports.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
