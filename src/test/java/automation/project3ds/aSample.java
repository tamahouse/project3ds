package automation.project3ds;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class aSample {
	
	private static ExtentReports extent;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	private static String[] time;

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			time = getTime();
			String reportPath = "C:\\Workspace\\project3ds\\test-output\\ExtendReport\\index.html";
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(reportPath);
			extent = new ExtentReports();
			reporter.setAppendExisting(true);
			extent.attachReporter(reporter);
		}
		return extent;
	}
	
	private static String[] getTime() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date currentTime = new Date(stamp.getTime());
		SimpleDateFormat sdfDate = new SimpleDateFormat("YYYY-MM-dd");
		String date = sdfDate.format(currentTime);
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH-mm-ss");
		String time = sdfTime.format(currentTime);
		return new String[] {date, time};
	}
	
	@Test
	public void sample() throws Exception {
		ExtentReports extent = getReporter();
		startTest(time[0]);
		getTest().info("<a href = \"C:\\Workspace\\project3ds\\test-output\\ExtendReport\\2019-07-19.html>2019-07-19</a> ");
		extent.flush();
	}

}
