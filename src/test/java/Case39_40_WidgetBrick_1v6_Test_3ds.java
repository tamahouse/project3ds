
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.Widget3dsNoIframe;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;

public class Case39_40_WidgetBrick_1v6_Test_3ds {

	
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
	public void optional3ds() throws Exception {
		String cardNumber = "4000000000000002";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("cardNumber", cardNumber).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		WidgetIframecc.clickProcessButton();
		Widget3dsNoIframe.success3ds();
		Boolean x = WidgetMainFrame.getCompleteMessage(type);
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
	@Test
	public void no3ds() throws Exception {
		String cardNumber = "4111111111111111";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("cardNumber", cardNumber ).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
//		WidgetIframecc.clickProcessButton();
//		Widget3dsNoIframe.success3ds();
		Boolean x = WidgetMainFrame.getCompleteMessage(type);
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
//	@Test
//	public void full3ds() throws Exception {
//		String cardNumber = "4444333322221111";
//		driver = AnnotationPage.getDriver();
//		driver.get(host);
//		WidgetMainFrame.clickBuyButton(type);
//		WidgetIframecc.clickUserDifferentCard();
//		WidgetIframecc.replace("cardNumber", cardNumber).create();
//		String cl_id = WidgetIframecc.getRefId();
//		System.out.println(cardNumber + " " +cl_id );
//		WidgetIframecc.clickProcessButton();
//		Widget3dsNoIframe.success3ds();
//		Boolean x = WidgetMainFrame.getCompleteMessage(type);
//		Assertion.isSuccess(x);
//		Assertion.end();
//	}
//	
	@Test
	public void failedOTP() throws Exception {
		String cardNumber = "4000000000000002";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("cardNumber", cardNumber).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		WidgetIframecc.clickProcessButton();
		Widget3dsNoIframe.failed3ds();
		ExtentManager.addScreenshot("failedOTP");
		Element formError = WidgetIframecc.getFormError();
		String value = "3D Secure verification failed.";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	
	
	
	

	
	
}
