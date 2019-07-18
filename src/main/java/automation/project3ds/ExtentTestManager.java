package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;



public class ExtentTestManager {
//	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	private static ExtentTest test;
	static ExtentReportImpl extent = ExtentManager.getReporter();

	public static synchronized ExtentTest getTest() {
//		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		return test;
	}

	public static synchronized void endTest() {
		extent.flush();
		ExtentReports dateIndex = extent.getDateIndex();
		ExtentReports index = extent.getIndex();
		if(dateIndex != null) {
			dateIndex.flush();
		}
		if(index != null) {
			index.flush();
		}
	}

	public static synchronized ExtentTest startTest(String testName) {
		test = extent.createTest(testName);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
