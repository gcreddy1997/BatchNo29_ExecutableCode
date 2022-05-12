package Practice_runner_Classes;

import org.testng.annotations.Test;

import com.magnitia.Batch29_Reports.Reports;



public class Reports_Class_Runner {
  @Test
  public void runner1() {
	  Reports.startTest("Start Test", "This start test method");
	  Reports.info("Info", "This test case defined about info level");
	  Reports.endTest();
	  }
  
  public void runner2() {
	  Reports.startTest("Start Test", "This start test method");
	  Reports.info("Info", "info");
	  Reports.pass("Pass", "This test case defined about Pass");
	  Reports.endTest();
  }
   
  public void runner3() {
	  Reports.startTest("Start Test", "This start test method");
	  Reports.info("Info", "This info");
	  Reports.pass("Pass", "This pass");
	  Reports.fail("Fail", "This test case defined about Fail ","screenshot.png");	  
	  Reports.endTest();
  }
  
  public void runner4() {
	  Reports.startTest("Start Test", "This start test method");
	  Reports.info("Info", "This info");	 
	  Reports.warn("warn", "This test case defined about warn ");	  
	  Reports.endTest();
  }

  public void runner5() {
	  Reports.startTest("Start Test", "This start test method");
	  Reports.info("Info", "This info");	 
	  Reports.fatal("fatal", "This test case defined about fatal ");	  
	  Reports.endTest();
  }
  @Test
  public void flush() {
	  Reports.flush();
  }
}
