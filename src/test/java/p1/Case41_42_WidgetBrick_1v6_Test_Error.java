package p1;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class Case41_42_WidgetBrick_1v6_Test_Error {

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
	public void authorizationFailed() throws Exception {
		String cardNumber = "4000000000000002";
		WidgetIframecc.payment("cardNumber", cardNumber).payment("cvv", "000").create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		Element formError = WidgetIframecc.getFormError();
		String value = "We were unable to authorize your card.";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void captureFailed() throws Exception {
		String cardNumber = "5555444433331111";
		WidgetIframecc.payment("cardNumber", cardNumber).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		Element formError = WidgetIframecc.getFormError();
		String value = "We were unable to authorize your card.";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	
	
	
	
	

	
	
}
