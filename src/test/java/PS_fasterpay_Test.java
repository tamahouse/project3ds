
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_allthegate;
import automation.project3ds.PS_allthegate2;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_cherrycredits;
import automation.project3ds.PS_cherrycredits2;
import automation.project3ds.PS_fasterpay;
import automation.project3ds.PS_fasterpay2;
import automation.project3ds.PS_mollie;
import automation.project3ds.PS_netbanking;
import automation.project3ds.PS_netbanking2;
import automation.project3ds.PS_paypal;
import automation.project3ds.PS_paypal2;
import automation.project3ds.PS_shortcode;
import automation.project3ds.PS_webmoney;
import automation.project3ds.PS_webmoney2;
import automation.project3ds.PS_yandexmoney;
import automation.project3ds.PS_yandexmoney2;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_fasterpay_Test extends BaseTest{
	
	String shortcode = PS_shortcode.FASTERPAY;
//	String url = "http://feature-pwl-2060.wallapi.bamboo.stuffio.com";
	String co_id = "215";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).isUidTimeline().co_id(co_id).isCustom(AnnotationPage.WallapiUrl.SUCCESS_URL, "https%3A%2F%2Fwww.spam4.me").generate();
	
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
		PS_fasterpay ps = (PS_fasterpay) object;
		PS_fasterpay2 ps2 = ps.getNewWindows();
		ps2.finishPayment();
		String email = ps2.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.assertConverted(cl_id);
//		String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//		Assertion.assertConverted(cl_id);
	}

}
