package automation.project3ds;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.Reader;

public class aSample {
	
	
	@Test
	public void sample() throws Exception {
		File[] directories = new File("test-output\\ExtendReport").listFiles(File::isDirectory);
		for(File directory: directories) {
			String meo = directory.getPath();
			System.out.println(meo);
			File[] files = new File(meo).listFiles(File::isFile);
			for(File file : files) {
				String gau = file.getPath();
				System.out.println(gau);
			}
		}
		
		
	}
}
