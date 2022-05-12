package com.magnitia.Batch29_AppModule;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.magnitia.Batch29_BaseClass.BaseClass;
import com.magnitia.Batch29_Constatns.Constant;
import com.magnitia.Batch29_Reports.Log;
import com.magnitia.Batch29_Reports.Reports;
import com.magnitia.Batch29_Utility.Utility;

	public class LaunchEnv extends BaseClass  {
		
	public static WebDriver driver;
	public static boolean flag;

	public static boolean LaunchBrowser(String Browser) throws InterruptedException {
		   flag = false;
		try {
		//	Browser = "chrome";
			
			Reports.info("Launching Browser","Launching browser : chrome");
			System.out.println("abc");
			System.out.println("xyx");
			if (Browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constant.chromepath);
				driver = new ChromeDriver();				
			}else if(Browser.equalsIgnoreCase("Firefox")){
				 System.setProperty("webdriver.gecko.driver", "filepath");
				 driver = new FirefoxDriver();				
			}
			driver.manage().window().maximize();
			Utility.waitImplicit();
			flag = true;
			Reports.pass("LAunching browser chrome", "Successfully LAunched");
		} catch (Exception e) {
			Log.info("","failing launching Browser");
				e.printStackTrace();
			Reports.fail("Broswer Invocation", "Broser invocation failed", "");	
		}	
		
		return flag;
	}	
	
//-----------------------------------------------------------------------------------------------		
		
	public static boolean launURL(String URL) throws InterruptedException, IOException {
		flag = false;
		try {
			driver.get(URL);
			Thread.sleep(1000);
			Log.info("","Navigating to URL " + URL);
			Reports.pass("abc","abd");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			Utility.closeBrowser();
		}
		
		return flag;
	}
	
	
	public static void closeSession() {
		Log.info("","Closing session");
	Reports.info("Session closed","Session Successfully closed");
		driver.close();
	}
}
