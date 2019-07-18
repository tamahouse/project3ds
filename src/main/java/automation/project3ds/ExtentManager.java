package automation.project3ds;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReportImpl extent;
	private static String[] time;

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
}
