import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.Network;
import automation.project3ds.PS_Giropay;
import automation.project3ds.PS_Giropay2;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Sofortbt;
import automation.project3ds.PS_cbc;
import automation.project3ds.PS_polipayments;
import automation.project3ds.Pslog;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WallapiAPI;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_polipayments_Test extends BaseTest{
	
	String shortcode = "polipayments";
//	String url = "http://feature-pgp-485.wallapi.bamboo.stuffio.com";
	String co_id = "14";
	String host = AnnotationPage.WallapiUrl.host(url).isUidTimeline().co_id(co_id).generate();
	
//	static Driver driver;
//
//	@BeforeClass
//	public void setUp() throws Exception {
//		Login.login(host);
//	}
//	
//
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//		AnnotationPage.driver = null;
//	}
	
	
	@BeforeClass
	public void setUp() {
		this.driver = new Driver(browser);
		login(host);
	}
	
//	@BeforeMethod
//	public void openBrick() throws Exception {
//		driver.get(host);
//		ps = (PS_Neosurf) widgetMulti.createClick(shortcode);
//	}

	
	@Test
	public void execute() throws Exception {
		WidgetPage widgetPage = new WidgetPage(driver);
		WidgetMulti widgetMulti = widgetPage.getMultiWidget();
		widgetMulti.clickPaymentMethod(shortcode);
		widgetMulti.clickPrice("5.00", "US");
		widgetMulti.clickBuyButton();
		PS_polipayments ps = new PS_polipayments(driver);
		ps.createPayment();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(shortcode);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
//		String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//		Assertion.assertConverted(cl_id);
	}

}
