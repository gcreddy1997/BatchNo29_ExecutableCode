package com.magnitia.Batch29_BaseClass;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.magnitia.Batch29_Constatns.Constant;
import com.magnitia.Batch29_Reports.Reports;
import com.magnitia.Batch29_Utility.ExcelUtils;
import com.magnitia.Batch29_Utility.Utility;



public class BaseClass {
	
	public static Properties prop_config;
	public static Properties prop_loct;
	
	@BeforeSuite
	public void intiliaseFiles()
	{		
		DOMConfigurator.configure("log4j.xml");
		prop_config = Utility.loadProperty(Constant.config_path);
		prop_loct =   Utility.loadProperty(Constant.locaters_path);
		ExcelUtils.invokeExcel(Constant.TestData_excel_path);	
	
	}
	
	@AfterTest
	public void FLushReports()
	{
		Reports.flush();
	}  

}
