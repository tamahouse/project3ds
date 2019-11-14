import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.PS_Sofortbt;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_sofortbanktransfer_Test extends BaseTest{
	
	String shortcode = "sofortbanktransfer";
//	String url = "http://feature-pwl-2037.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).generate();
	
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
		widgetMulti.clickPrice(1);
		widgetMulti.clickBuyButton();
		PS_Sofortbt ps = new PS_Sofortbt(driver);
		ps.createPayment();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(shortcode);
		String reportUrl = url+ "/admin/payment-clicks/transaction-details?click="+cl_id;
		driver.get(reportUrl);
		Assertion.assertConverted(cl_id);
	}

}
