import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;

public class Case20_24_WidgetBrick_1v6_Test_ValidateExpirationDate {

	String host;
	String type;
	
	static Driver driver;

	@Parameters({"type"})
	@BeforeClass
	public void setUp(String type) throws Exception {
		this.type = type;
		host = AnnotationPage.hostMap.get(type);
		Login.login(host);
	}


	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void pastValue() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String expDate = "0118";
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setExpirationDate(expDate);
		Element element = WidgetIframecc.getExpirationDateTextbox();
		Assertion.isInvalid(element);
		Assertion.end();
	}
	
	@Test
	public void todayValue() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date currentTime = new Date(stamp.getTime());
		SimpleDateFormat sdfDate = new SimpleDateFormat("MMYY");
		String expDate = sdfDate.format(currentTime);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setExpirationDate(expDate);
		Element element = WidgetIframecc.getExpirationDateTextbox();
		Assertion.isValid(element);
		Assertion.end();
	}
	
	@Test
	public void futureValueShort() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String expDate = "0129";
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setExpirationDate(expDate);
		Element element = WidgetIframecc.getExpirationDateTextbox();
		Assertion.isValid(element);
		Assertion.end();
	}
	
	@Test
	public void futureValueLong() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String expDate = "012029";
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setExpirationDate(expDate);
		Element element = WidgetIframecc.getExpirationDateTextbox();
		Assertion.isValid(element);
		Assertion.end();
	}
	
	

	
	
}
