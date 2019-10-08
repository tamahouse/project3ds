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
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.Widget3dsNoIframe;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class Case39_40_WidgetBrick_1v6_Test_3ds {

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
	

	@Test
	public void optional3ds_on() throws Exception {
		String newhost = AnnotationPage.WallapiUrl.host().isBrick16().generate();
		driver = AnnotationPage.getDriver();
		driver.get(newhost);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
		String cardNumber = "4000000000000002";
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.payment("cardNumber", cardNumber).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		WidgetIframecc.clickProcessButton();
		Widget3dsNoIframe.success3ds();
		Boolean x = WidgetMulti.getCompleteMessage();
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
	@Test
	public void optional3ds_off() throws Exception {
		String newhost = AnnotationPage.WallapiUrl.host().a_id("100952").isBrick16().generate();
		driver = AnnotationPage.getDriver();
		driver.get(newhost);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
		String cardNumber = "4000000000000002";
		WidgetIframecc.payment("cardNumber", cardNumber).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		Boolean x = WidgetMulti.getCompleteMessage();
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
	@Test
	public void no3ds_on() throws Exception {
		String newhost = AnnotationPage.WallapiUrl.host().a_id("100952").isBrick16().generate();
		driver = AnnotationPage.getDriver();
		driver.get(newhost);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
		String cardNumber = "4111111111111111";
		WidgetIframecc.payment("cardNumber", cardNumber ).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		WidgetIframecc.clickProcessButton();
		Widget3dsNoIframe.success3ds();
		Boolean x = WidgetMulti.getCompleteMessage();
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
	@Test
	public void no3ds_off() throws Exception {
		String newhost = AnnotationPage.WallapiUrl.host().a_id("100952").isBrick16().generate();
		driver = AnnotationPage.getDriver();
		driver.get(newhost);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
		String cardNumber = "4111111111111111";
		WidgetIframecc.payment("cardNumber", cardNumber ).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		Boolean x = WidgetMulti.getCompleteMessage();
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
//	@Test
//	public void full3ds_on() throws Exception {
//		String newhost = AnnotationPage.WallapiUrl.host().isBrick16(true).generate();
//		driver = AnnotationPage.getDriver();
//		driver.get(newhost);
//		WidgetMulti.clickPaymentMethod("gateway");
//		WidgetMulti.clickBuyButton();
//		String cardNumber = "4444333322221111";
//		WidgetIframecc.payment("cardNumber", cardNumber).create();
//		String cl_id = WidgetIframecc.getRefId();
//		System.out.println(cardNumber + " " +cl_id );
//		WidgetIframecc.clickProcessButton();
//		Widget3dsNoIframe.success3ds();
//		Boolean x = WidgetMulti.getCompleteMessage();
//		Assertion.isSuccess(x);
//		Assertion.end();
//	}
//	
//	@Test
//	public void full3ds_off() throws Exception {
//		String newhost = AnnotationPage.WallapiUrl.host().a_id("100952").isBrick16(true).generate();
//		driver = AnnotationPage.getDriver();
//		driver.get(newhost);
//		WidgetMulti.clickPaymentMethod("gateway");
//		WidgetMulti.clickBuyButton();
//		String cardNumber = "4444333322221111";
//		WidgetIframecc.payment("cardNumber", cardNumber).create();
//		String cl_id = WidgetIframecc.getRefId();
//		System.out.println(cardNumber + " " +cl_id );
//		Element formError = WidgetIframecc.getFormError();
//		String value = "3D Secure verification failed.";
//		Assertion.isFormError(formError, value);
//		Assertion.end();
//	}
	
	@Test
	public void failedOTP() throws Exception {
		String newhost = AnnotationPage.WallapiUrl.host().isBrick16().generate();
		driver = AnnotationPage.getDriver();
		driver.get(newhost);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		String cardNumber = "4000000000000002";
		WidgetIframecc.payment("cardNumber", cardNumber).create();
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