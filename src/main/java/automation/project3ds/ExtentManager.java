package automation.project3ds;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReportImpl extent;
	private static String[] time;
	private static ExtentTest test;

	public synchronized static ExtentReportImpl getReporter() {
		if (extent == null) {
			time = getTime();
			String reportName = "Report "+time[0]+" "+time[1];
			String dateReportName = "ReportDate " + time[0];
			extent = new ExtentReportImpl(reportName, dateReportName);
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
	


	public static ExtentTest getTest() {
//		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		return test;
	}

	public static void endTest() {
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
		test = getReporter().createTest(testName);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
