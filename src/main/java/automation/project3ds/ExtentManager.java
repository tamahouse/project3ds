package automation.project3ds;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			String[] time = getTime();
			String reportPath = "C:\\Workspace\\project3ds\\test-output\\ExtendReport\\ExtentReportResults"+time[0]+"\\ReportTime"+time[1]+".html";
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(reportPath);
			extent = new ExtentReports();
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
}
