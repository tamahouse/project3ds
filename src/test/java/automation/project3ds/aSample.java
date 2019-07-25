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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.Reader;

import us.codecraft.xsoup.Xsoup;

public class aSample {
	String host = "https://kiemthutudong.com/blog/wp-login.php";
	String xpath = "user_login";
	String outer = "<input type=\"text\" name=\"log\" id=\"user_login\" class=\"input\" value=\"\" size=\"20\">";
	
	@Test
	public void sample() throws Exception {
		Driver driver = new Driver();
		driver.get(host);
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		Elements meo = doc.select(outer);
		System.out.println();
	}
}
