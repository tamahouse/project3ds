package automation.project3ds;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import automation.project3ds.Driver.Browser;


public class BaseTest {

	public Driver driver;
	public String url = "http://develop.wallapi.bamboo.stuffio.com";
	public static String browser = Browser.Chrome;
	
//	@BeforeClass
//	public void setUp() {
//		this.driver = new Driver();
//	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void login(String url) {
		this.url = url;
		ExtentManager.logInfo(url);
		Login login =  new Login(driver);
		login.login(url);
	}
}
