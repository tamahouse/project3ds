package automation.project3ds;



import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import automation.project3ds.Driver.Browser;


public class BaseTest_Z2 extends BaseTest{

//	public Driver driver;
	public String url = "http://feature-brick-test.wallapi.bamboo.stuffio.com";
//	public String url = "http://feature-pwg-1162.wallapi.bamboo.stuffio.com";
	public static String browser = Browser.Chrome;
	
	
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
