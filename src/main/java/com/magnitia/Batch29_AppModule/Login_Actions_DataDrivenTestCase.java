package com.magnitia.Batch29_AppModule;

import org.testng.Assert;

import com.magnitia.Batch29_Reports.Log;
import com.magnitia.Batch29_Reports.Reports;
import com.magnitia.Batch29_Utility.Utility;

public class Login_Actions_DataDrivenTestCase {
	public static String actValue;
	public static boolean flag;

	public static boolean Login_DataDrivenTestCase(String rmode, String un, String pw, String criteria,	String ExpOutput) throws InterruptedException {
		flag = false;
		try {
		if (rmode.equalsIgnoreCase("YES")) {
			Assert.assertTrue(LoginActions.login(un, pw) );
			Thread.sleep(1000);

			if (criteria.equals("Valid")) {
				try {
				actValue = Utility.getLocater("Home_welcome_message_xpath").getText();
				System.out.println(actValue);
				Assert.assertEquals(actValue, ExpOutput);
				flag = true;
				Reports.pass("Login Data Driven TestCase", "Data Driven test case executed successfully");
				Log.pass("Logain Data driven", "Login Data Driven Test Case Executed successfully");
				} catch (Exception e) {
					e.printStackTrace();
					Reports.fail("Login_DataDriven", "Data driven test case failded due to : " + e.toString(), "");
					Log.fail("Login Data driven", " Login data driven test case failed duel to " + e.fillInStackTrace());
				}
			} else if ( (criteria.equals("invalid")) && (ExpOutput.equals("username or email is required"))) {
				try {
				        String actValue = Utility.getLocater("Login_errMessage_message_xpath").getText();
				        System.out.println(actValue);
						Assert.assertEquals(actValue, ExpOutput);
						flag = true;
						Reports.pass("Login Data Driven TestCase", "Data Driven test case executed successfully");
						Log.pass("Logain Data driven", "Login Data Driven Test Case Executed successfully");
				} catch (Exception e) {
						e.printStackTrace();
						Reports.fail("Login_DataDriven", "Data driven test case failded due to : " + e.toString(), "");
						Log.fail("Login Data driven", " Login data driven test case failed duel to " + e.fillInStackTrace());
				}

			} else {
				try {
					   Thread.sleep(2000);
						String actValue = Utility.getLocater("Login_LoginFailed_message_xapth").getText();
						System.out.println(actValue);
						Assert.assertEquals(actValue, ExpOutput);
						flag = true;
						Reports.pass("Login Data Driven TestCase", "Data Driven test case executed successfully");
						Log.pass("Logain Data driven", "Login Data Driven Test Case Executed successfully");
				} catch (Exception e) {
						e.printStackTrace();
						Reports.fail("Login_DataDriven", "Data driven test case failded due to : " + e.toString(), "");
						Log.fail("Login Data driven", " Login data driven test case failed duel to " + e.fillInStackTrace());
				 }
			}
			}else {
				Reports.info("Login with no run Mode", "Login with no runmode is executing here...");
				Log.info("Login with no run Mode", "Login with no runmode is executing here...");
				
			}	
			
		}catch(Exception e1){
			e1.printStackTrace();
		}			
	
	return flag;

}

}
