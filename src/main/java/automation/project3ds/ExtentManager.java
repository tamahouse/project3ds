package automation.project3ds;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.MediaType;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static String[] time = getTime();
	private static String folderPath = "test-output\\ExtendReport\\";
	private static String folderPathDaily = folderPath +"Report"+time[0]+"\\";;

	public synchronized static ExtentReports getReporter(String suiteName) {
		if (extent == null) {
			String reportName = suiteName +"_"+ time[0]+"_"+time[1] + ".html" ;
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(folderPathDaily+ reportName);
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}
	
	
	public synchronized static ExtentReports getReporter() {
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
			Arrays.sort(directories, new Comparator<File>() {
			    public int compare(File f1, File f2) {
			        return Long.compare(f1.lastModified(), f2.lastModified());
			    }
			});
			for(int i = directories.length-1; i > -1; i-- ) {
				File directory = directories[i];
				String dateName = directory.getName();
				String datePath = directory.getPath();
				ExtentTest indexTest = index.createTest(dateName);
				File[] files = new File(datePath).listFiles(File::isFile);
				Arrays.sort(files, new Comparator<File>() {
				    public int compare(File f1, File f2) {
				        return Long.compare(f1.lastModified(), f2.lastModified());
				    }
				});
				for(int ii = files.length-1; ii > -1; ii-- ) {
					File file = files[ii];
					String timeName = file.getName();
					String timePath = file.getPath().replace(folderPath, "");
					indexTest.info("<a href =\"" + timePath + "\">" + timeName + "</a>");
				}
				}
			index.flush();
			
	}
	
	public static void logInfo(String info) {
		try {
			ExtentManager.getTest().info(info);
		}catch(Exception ignore) {
			
		}
	}
	
	public static void addScreenshot(String imageName) {
		addScreenshot(imageName, imageName);
	}
	
	public static void addScreenshot(String title, String imageName) {
		try {
			String imagePath = "image\\"+ Driver.timestamp() + imageName + ".png";
			String createdPath = folderPathDaily + imagePath;
			AnnotationPage.screenShot(createdPath);
			ScreenCapture media = new ScreenCapture();
			media.setPath(imagePath);
			MediaEntityModelProvider provider = new MediaEntityModelProvider(media);
			ExtentManager.getTest().info(title, provider);
		}catch(Exception ignore) {
		}
	}

	public static synchronized ExtentTest startTest(String suiteName, String testName) {
		test = getReporter(suiteName).createTest(testName);
//		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
