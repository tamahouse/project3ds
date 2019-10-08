package p1;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class Case20_24_WidgetBrick_1v6_Test_ValidateExpirationDate {

	String host = AnnotationPage.hostMap.get("p1");
	
	static Driver driver;

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
	}


	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}
	
	@BeforeMethod
	public void openBrick() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
	}

	@Test
	public void pastValue() throws Exception {
		String expDate = "0118";
		WidgetIframecc.setExpirationDate(expDate);
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	@Test
	public void todayValue() throws Exception {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date currentTime = new Date(stamp.getTime());
		SimpleDateFormat sdfDate = new SimpleDateFormat("MMYY");
		String expDate = sdfDate.format(currentTime);
		WidgetIframecc.setExpirationDate(expDate);
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	@Test
	public void futureValueShort() throws Exception {
		String expDate = "0129";
		WidgetIframecc.setExpirationDate(expDate);
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	@Test
	public void futureValueLong() throws Exception {
		String expDate = "012029";
		WidgetIframecc.setExpirationDate(expDate);
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	

	
	
}
