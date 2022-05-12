package Uibank_Uipath;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.magnitia.Batch29_AppModule.LaunchEnv;
import com.magnitia.Batch29_AppModule.LoginActions;
import com.magnitia.Batch29_Reports.Log;
import com.magnitia.Batch29_Reports.Reports;
import com.magnitia.Batch29_Utility.Utility;

// not provided any validation...
// without validation...  
public class LoginActions_Caller extends LoginActions {
	
@BeforeMethod
public static void launchEnv_Caller() throws InterruptedException, IOException {
	Reports.startTest("LoginActions", "description");
	Log.startTest("Login Actions");
	LaunchEnv.LaunchBrowser(prop_config.getProperty("Browser"));
	LaunchEnv.launURL(prop_config.getProperty("Uipath_Uibank_URL"));
}
	
  @Test
  public void LoignAcrtionsCaller() {
	  LoginActions.login(prop_config.getProperty("UserName"), prop_config.getProperty("Password"));
  }
  
  @AfterMethod
  public void closingBrowser() throws IOException {
	  Utility.closeBrowser();
	  
  }
}
