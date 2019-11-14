package automation.project3ds;



import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import automation.project3ds.Driver.Browser;


public class BaseTest {

	public Driver driver;
	public String url = "http://develop.wallapi.bamboo.stuffio.com";
	public static String browser = Browser.Chrome;
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		String command = "taskkill /f /im chromedriver.exe";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
		}
	}
	
	public void login(String url) {
		ExtentManager.logInfo(url);
		Login login =  new Login(driver);
		login.login(url);
	}
}
