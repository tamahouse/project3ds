package p2_done;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_kcb;
import automation.project3ds.PS_kcb2;
import automation.project3ds.PS_mollie;
import automation.project3ds.PS_shortcode;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_mollie_kbc_Test extends BaseTest{
	
	String shortcode = PS_shortcode.KBC;
//	String url = "http://feature-pgp-485.wallapi.bamboo.stuffio.com";
	String a_id = "99590";
	String co_id = "22";
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).widget(widget).isPrice(price, currency).isUidTimeline().co_id(co_id).generate();
	
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
		PS_kcb ps = (PS_kcb) object;
		PS_kcb2 ps2 = ps.getNewWindows();
		ps2.createPayment();
		String url = driver.getCurrentUrl();
		String cl_id = url.substring(url.indexOf("ref_id=d")+8, url.length());
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
//		String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//		Assertion.assertConverted(cl_id);
	}

}
