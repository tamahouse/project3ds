package automation.project3ds;



import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import automation.project3ds.Driver.Browser;
import automation.project3ds.WidgetObject.WidgetType;


public class BaseTest_Z2 extends BaseTest{

//	public Driver driver;
//	public String url = "http://feature-brick-test.wallapi.bamboo.stuffio.com";
//	public String url = "http://feature-pwg-1162.wallapi.bamboo.stuffio.com";
//	public String url = "http://release-8.1.53.wallapi.bamboo.stuffio.com";
	public String url = "https://feature-https-test.wallapi.bamboo.stuffio.com";
	public static String widget = WidgetType.MULTI;
	public static String browser = Browser.IE;
	
	
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//		String command = "taskkill /f /im chromedriver.exe";
//		try {
//			Runtime.getRuntime().exec(command);
//		} catch (IOException e) {
//		}
//	}
//	
//	public void login(String url) {
//		ExtentManager.logInfo(url);
//		Login login =  new Login(driver);
//		login.login(url);
//	}
}
