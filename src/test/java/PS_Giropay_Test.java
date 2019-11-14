import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_Giropay;
import automation.project3ds.PS_Giropay2;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetPage;

public class PS_Giropay_Test extends BaseTest{
	
	String shortcode = "giropay";
//	String url = "http://feature-pgp-485.wallapi.bamboo.stuffio.com";
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
		PS_Giropay ps = (PS_Giropay) widgetPage.getMultiWidget().createClick(shortcode);
		PS_Giropay2 ps2 = ps.submitBic();
		ThankyouPage tk = ps2.createPayment();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
//		String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//		Assertion.assertConverted(cl_id);
	}

}
