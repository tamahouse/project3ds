package automation.project3ds;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static String folderPath;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			String[] time = getTime();
			folderPath = "test-output\\ExtendReport\\";
			String folderName = "Report"+time[0];
			String reportName = folderName+"_"+time[1] ;
			String reportPath = folderPath +folderName+"\\" + reportName + ".html";
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(reportPath);
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}
	
	public static ExtentTest getTest() {
//		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		return test;
	}

	public static void endTest() {
		extent.flush();
		createIndexPage();
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
	
	private static void createIndexPage() {
		String indexReportPath = folderPath +"index.html";
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(indexReportPath);
			ExtentReports index = new ExtentReports();
			index.attachReporter(reporter);
			File[] directories = new File(folderPath).listFiles(File::isDirectory);
			for(int i = directories.length-1; i > -1; i-- ) {
				File directory = directories[i];
				String dateName = directory.getName();
				String datePath = directory.getPath();
				ExtentTest indexTest = index.createTest(dateName);
				File[] files = new File(datePath).listFiles(File::isFile);
				for(int ii = files.length-1; ii > -1; ii-- ) {
					File file = files[ii];
					String timeName = file.getName();
					String timePath = file.getPath().replace(folderPath, "");
					indexTest.info("<a href =\"" + timePath + "\">" + timeName + "</a>");
				}
				}
			index.flush();
			
	}

	public static synchronized ExtentTest startTest(String testName) {
		test = getReporter().createTest(testName);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
