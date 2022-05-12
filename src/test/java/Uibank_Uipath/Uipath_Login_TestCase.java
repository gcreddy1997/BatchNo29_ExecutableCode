package Uibank_Uipath;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.magnitia.Batch29_AppModule.LaunchEnv;
import com.magnitia.Batch29_AppModule.LoginActions;
import com.magnitia.Batch29_AppModule.Login_Actions_DataDrivenTestCase;
import com.magnitia.Batch29_Reports.Log;
import com.magnitia.Batch29_Reports.Reports;
import com.magnitia.Batch29_Utility.ExcelUtils;
import com.magnitia.Batch29_Utility.Utility;

public class Uipath_Login_TestCase extends LoginActions {
	@BeforeMethod
	public static void launchEnv_Caller() throws InterruptedException, IOException {
		Reports.startTest("LoginActions", "description");
		Log.startTest("Login Actions");
		LaunchEnv.LaunchBrowser(prop_config.getProperty("Browser"));
		LaunchEnv.launURL(prop_config.getProperty("Uipath_Uibank_URL"));
	}
		
	//Attribute... 
@Test(dataProvider ="Login_Credentials")
  public void loginForExcelData(String rm, String un, String pw, String criteria, String ExpectedOutput) {
		try {
			Login_Actions_DataDrivenTestCase.Login_DataDrivenTestCase(rm, un, pw, criteria, ExpectedOutput);
			Reports.pass("Login", "Loign Actions done successfully....... ");
			Log.pass("Login", "Login actions for valid or invalid data executed successfully");
		}catch(Exception e) {
			Reports.fail("Login", "Login failed due to: "+e.toString(), "");
			Log.fail("Login", "Login actions failed due to : "+ e.fillInStackTrace());
		}
  }
	
	
	@AfterMethod
	  public void closingBrowser() throws IOException {
		  Utility.closeBrowser();
		  
	  }
	
	// Annotation  
	// DataProvider will execute no rows times and no of colums data will be provided to the test...   
	@DataProvider (name ="Login_Credentials")
	public Object[][] readingTheDataFromExcelSheet(){
		Object[][] excelData = null;
		excelData = ExcelUtils.readData("Login_Credentials");		
		return excelData;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
