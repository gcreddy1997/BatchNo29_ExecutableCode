package com.magnitia.Batch29_Reports;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Log {
	
//	public static Logger logger = Logger.getLogger(Reports.class.getName());
	public static Logger logger = Logger.getLogger(Reports.class.getName());
	
	public static void startTest(String testCaseName) {
		DOMConfigurator.configure("Log4j.xml");
		logger.info("*******************   started  "+ testCaseName +"********************** here");
	    logger.info("----------------------------------------------------------------------------");
	}
	
	public static void info(String stepName, String message) {
		logger.info(stepName+"    "+ message);
	}
	
	public static void pass(String stepName, String message) {
		logger.info(stepName+"    "+ message);
	}
	
	public static void warn(String stepName, String message) {
		logger.info(stepName+"    "+ message);
	}
	
	public static void fail(String stepName, String message) {
		logger.info(stepName+"    "+ message);
	}
	
	public static void fatal(String stepName, String message) {
		logger.info(stepName+"    "+ message);
	}
	
	public static void endTest() {
	logger.info("#######################################################################################");
	logger.info("                                                                                       ");
	}

}
