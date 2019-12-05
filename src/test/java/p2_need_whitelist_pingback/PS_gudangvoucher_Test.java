package p2_need_whitelist_pingback;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_giropay;
import automation.project3ds.PS_gudangvoucher;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.PS_gudangvoucher.GudangIframe;
import automation.project3ds.WidgetPage;

public class PS_gudangvoucher_Test extends BaseTest{
	
	String shortcode = PS_shortcode.GUDANGVOUCHER;
//	String url = "http://feature-pwl-2013.wallapi.bamboo.stuffio.com";
	String co_id = "94";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).co_id(co_id).generate();
	
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
		Object object = widgetPage.getPS(widget, shortcode,logo);
		PS_gudangvoucher ps = (PS_gudangvoucher) object;
		GudangIframe ps2 = ps.createPayment();
		String email = ps.getEmail();
		ps2.finishPayment();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
//		String reportUrl = url+ "/admin/payment-clicks/transaction-details?click="+cl_id;
//		driver.get(reportUrl);
		Assertion.assertConverted(cl_id);
//		String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//		Assertion.assertConverted(cl_id);
	}

}
