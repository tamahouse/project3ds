package p2_done;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_epspayment;
import automation.project3ds.PS_giropay;
import automation.project3ds.PS_giropay2;
import automation.project3ds.PS_epspayment2;
import automation.project3ds.PS_shortcode;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetPage;

public class PS_epspayment_Test extends BaseTest{
	
	String shortcode = PS_shortcode.EPSPAYMENT;
//	String url = "http://feature-pgp-494.wallapi.bamboo.stuffio.com";
	String co_id = "15";
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
		PS_epspayment ps = (PS_epspayment) object;
		PS_epspayment2 ps2 = ps.getNewWindows();
		ps2.createPayment();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
//		String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//		Assertion.assertConverted(cl_id);
	}

}
