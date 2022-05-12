package com.magnitia.Batch29_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magnitia.Batch29_AppModule.LaunchEnv;
import com.magnitia.Batch29_Reports.Log;
import com.magnitia.Batch29_Reports.Reports;



public class Utility extends LaunchEnv  {
	public static FileInputStream file;
	public static Properties prop;
	public static Properties locaters_Repository = Utility.loadProperty("C:\\Users\\User\\eclipse-workspace\\com.magnitia.Selenium_GcReddy_Batch29\\src\\test\\resources\\ObjectRepository\\Locator.properties");
    public static Properties Config_Repository = Utility.loadProperty("C:\\Users\\User\\eclipse-workspace\\com.magnitia.Selenium_GcReddy_Batch29\\src\\test\\resources\\ObjectRepository\\Config.properties");
    public static String locvalue;
    public static String filepath;
    
	// ***************************************************************************
	/**
	 * This function loads the property file in buffer
	 * 
	 * @param filelocation
	 *            Enter the file location of the property file as parameter.
	 * @return Properties. This function will return the object of the loaded
	 *         property file.
	 * @exception FileNotFoundException,
	 *                IOException
	 */
	// ***************************************************************************
	public static Properties loadProperty(String filelocation) {
		prop = new Properties();
	
		try {
			 file = new FileInputStream(filelocation);
			//Log.info("Location for Property file :"+filelocation);
		} catch (FileNotFoundException e) {
		//	Reports.fail("properties file invocaiton", "Properotes file invoked", "  ");
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			Reports.fail("",e.toString(),"");
			e.printStackTrace();
		}
		return prop;
	}

	/************************************************************************************************
	 * This function will define specific locater type for keys in locaters
	 * property file
	 * 
	 * @param key
	 *            Pass Locater key from Locaters property file
	 * @return Webelement This function will return WebElement based on specific
	 *         locater type.
	 * @see Key should exist in locaters file ending with specfic locater type
	 *      eg. _xpath, _id, _lnktxt.
	 * @exception ElementNotFoundException
	 *************************************************************************************************/
	public static WebElement getLocater(String key) {
		 WebElement loct= null;
		try {
			
			//Login_UserName_txtbox_id = email
			
			locvalue = prop_loct.getProperty(key);
			
			Log.info(" ", "Locator is   :"+key +"Loc value is :  " +locvalue);
			Thread.sleep(1000);	
		    //	login_button_xpath
			if (key.endsWith("_id")) {
				loct = driver.findElement(By.id(locvalue));
				ElementHighlight(loct);
				Log.info("","Searching locater '"+locvalue+"' for locater Key '"+key+"'");
			} else if (key.endsWith("_xpath")) {
				loct = driver.findElement(By.xpath(locvalue));
				ElementHighlight(loct);
				Log.info("","Searching locater '"+locvalue+"' and locater type '"+key+"'");
			} else if (key.endsWith("_lnktxt")) {
				loct = driver.findElement(By.linkText(locvalue));
				ElementHighlight(loct);
				Log.info("","Searching locater '"+locvalue+"' and locater type '"+key+"'");
			} else if (key.endsWith("_name")) {
				loct = driver.findElement(By.name(locvalue));
				Log.info("", "Searching locater '"+locvalue+"' and locater type '"+key+"'");
				ElementHighlight(loct);
				
			} else if (key.endsWith("_partlnktxt")) {
				Log.info("","Searching locater '"+locvalue+"' and locater type '"+key+"'");
				ElementHighlight(loct);
				Log.info("","Searching locater '"+locvalue+"' and locater type '"+key+"'");
			} else if (key.endsWith("css")) {
				loct = driver.findElement(By.cssSelector(locvalue));
				ElementHighlight(loct);
				Log.info("","Searching locater '"+locvalue+"' and locater type '"+key+"'");
			} else if (key.endsWith("tagname")) {
				loct = driver.findElement(By.tagName(locvalue));
				ElementHighlight(loct);
				Log.info("","Searching locater '"+locvalue+"' and locater type '"+key+"'");
			} else {
				Log.info("","Locaters not matched");
			}
		} catch (Exception e) {
			Reports.fail("","",e.toString());
			e.printStackTrace();
	
		}
		return loct;
	
	}

	/***************************************************************************************
	 * This function is used to highlight element on GUI where the current and
	 * previous action has been performed.
	 * 
	 * @param element
	 **************************************************************************************/
	public static void ElementHighlight(WebElement element) {
		try {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", element);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Reports.fail("","",e.toString());
			e.printStackTrace();
		}
		//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: solid 2px green ');",
		//		element);
	}

	/***************************************************************************************
	 * This function will close the current session
	 * 
	 * @throws IOException
	 **************************************************************************************/
	public static void closeBrowser() throws IOException {
		try {
			Log.info("","Closing Browser");
			driver.quit();
	
		} catch (Exception e) {
			Reports.fail("","",e.toString());
			e.printStackTrace();
	
		}
	
	}

	/*****************************************************************************************
	 * This function will dynamically wait for pageload.
	 * 
	 * @exception Exception
	 ****************************************************************************************/
	public static void Elementwait() throws Exception {
	
		Log.info("","Waiting for page load");
		Thread.sleep(1000);
		WebDriverWait explicitwait = new WebDriverWait(driver, 120);
		explicitwait.withTimeout(80, TimeUnit.SECONDS);
		explicitwait.pollingEvery(2, TimeUnit.SECONDS);
		explicitwait.ignoring(NoSuchElementException.class);
	//	explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img.timer.center-block")));
	}	
	//----------------------------------------------------------------------------------------	
		public static void waitForElementClickable(String Key) throws InterruptedException {
	
			Thread.sleep(2000);
			Log.info("","Waiting for the Element to be clickable");
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.withTimeout(60, TimeUnit.SECONDS);
			wait.pollingEvery(2, TimeUnit.SECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(InvalidSelectorException.class);
			wait.ignoring(WebDriverException.class);
			wait.until(ExpectedConditions.elementToBeClickable(Utility.getLocater(Key)));	
	}

	/*****************************************************************************************
	 * This function will dynamically wait for text on element to be present.
	 * 
	 * @param Key
	 * @param text
	 * @throws InterruptedException 
	 * @exception Exception
	 ****************************************************************************************/
	public static void waitForTextToBeDisplayed(String Key) throws InterruptedException {
			Thread.sleep(1000);
			Log.info("","Waiting for the Element to be visible");
			WebDriverWait wait = new WebDriverWait(driver, 200);
			wait.withTimeout(90, TimeUnit.SECONDS);
			wait.pollingEvery(2, TimeUnit.SECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(InvalidSelectorException.class);
			wait.until(ExpectedConditions.visibilityOf(Utility.getLocater(Key)));	
	
	}

	/*****************************************************************************************
	 * This function wait implicitly for mentioned time duration.
	 * 
	 * @exception Exception
	 ****************************************************************************************/
	public static void waitImplicit() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
   //--------------------------------------------------------------------------------------------
	public static List<WebElement> ElementCollection(String xpath)
	{
		List<WebElement> collections = driver.findElements(By.xpath(xpath));
		return collections;
	}
	//---------------------------------------------------------------------------------

	public static void getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		Date date = new Date();
		System.out.println(date);		
	}

	/***************************************************************************************
	 * This function will give you System date time in string format
	 * 
	 * @return This function will return date time in String format.
	 **************************************************************************************/
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		String[] parts = founddate.split(" ");
		String[] appenderpart1 = parts[0].split("/");
		String appender = appenderpart1[1] + "-" + appenderpart1[2] + "-" + appenderpart1[0];
		return appender;
	}

	/***************************************************************************************
	 * This function will give you System date time in string format
	 * 
	 * @return This function will return date time in String format.
	 **************************************************************************************/
	public static String getDatetime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		Log.info("",dateFormat.format(date));    // 2014/08/06 15:59:48
	
		String[] parts = founddate.split(" ");
		// String part1 = parts[0]; // 004
		String[] appenderpart1 = parts[0].split("/");
		String[] appenderpart2 = parts[1].split(":");
		String appender = appenderpart1[1] + appenderpart1[2] + appenderpart2[0] + appenderpart2[1] + appenderpart2[2];
		Log.info("",appender);
		return appender;
	}

	/***************************************************************************************
	 * This function will take screenshot on faliure of test cases.
	 * 
	 * @return filepath
	 * @see Use this function on negative secnarios.
	 * 
	 **************************************************************************************/
	
	public static String getfailScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\FaliureScreenshots\\" + System.currentTimeMillis()
					+ ".png";
			FileUtils.copyFile(file, new File(filepath));
		} catch (IOException e) {
			Reports.fail("","",e.toString());
			e.printStackTrace();
			Log.info("",e.fillInStackTrace().toString() );
		}
		return filepath;
	}

	/*************************************************************************************
	 * This function will get screenshot on Success of executed Test Cases.
	 * 
	 * @return filepath This function will return the String path of the
	 *         screenshot.
	 * @exception IOException
	 **************************************************************************************/
	public static String getSuccessScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\SuccessScreenshots\\"
					+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(file, new File(filepath));
		} catch (IOException e) {
			Reports.fail("","",e.toString());
			Log.info("", e.fillInStackTrace().toString());
			e.printStackTrace();
		}
		return filepath;
	}

	/*******************************************************************************************
	 * This function will clean the framework and will delete the files and
	 * folder for specific mentioned time duration
	 * 
	 * @param daysBack
	 * @param dirWay
	 ******************************************************************************************/
	public static void FrameworkcleanUp(int daysBack, String dirWay) {
	
		File directory = new File(dirWay);
		if (directory.exists()) {
	
			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						System.err.println("Unable to delete file: " + listFile);
					}
				}
			}
		}
	}
//-----------------------------------------------------------------------------------------------------
	public static void InitialiseExtentReport(String testcaseName, String testcaseDesc)
	{
		Reports.startTest(testcaseName, testcaseDesc);
	}
	
	//------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	

	

}
