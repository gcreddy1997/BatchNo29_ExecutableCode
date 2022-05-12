package Practice_runner_Classes;

import org.testng.annotations.Test;

import com.magnitia.Batch29_Reports.Log;

public class LogFile_RunnerClass {
  @Test
  public void LogFile_Calling() {
	  Log.startTest("Testing the LogFile");
	  Log.info("Step no. 1", "Step 1 is printing");
	  Log.pass("Step no. 2", "Step 2 is printing");
	  Log.fail("Step no. 3", "Step 3 is printing " );
	  Log.warn("Step no. 4", "Step 4 is printing");
	  Log.fatal("Step no. 5", "Step 5 is printing");
	  Log.endTest();  		
	  
  }
}
