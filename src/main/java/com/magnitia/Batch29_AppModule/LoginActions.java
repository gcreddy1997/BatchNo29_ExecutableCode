package com.magnitia.Batch29_AppModule;

import com.magnitia.Batch29_Reports.Log;
import com.magnitia.Batch29_Reports.Reports;
import com.magnitia.Batch29_Utility.Utility;

public class LoginActions extends Utility{
	
	public static boolean login(String username, String password) {
		boolean flag = false;
		
		try {
		Log.info("LoginActions", "Peforming Login actions");
		Utility.getLocater("Login_userName_txtbox_id").sendKeys(username);
		Utility.getLocater("Login_password_txtbox_xpath").sendKeys(password);
		Utility.getLocater("Login_submit_button_xpath").click();
		flag = true;
		Log.info("LoginActions", "Login Actions done successfully");
		Reports.pass("Login Actions", "Login Actions done successfully");	
		
		}catch(Exception e) { 
			Log.info("Login Actions", "Login actions faild due to "+e.fillInStackTrace().toString());
			Reports.fail("Login Actions", "Login Actions failed due to :" +e.toString(), "");
		}
		
		return flag;	
	}

}
