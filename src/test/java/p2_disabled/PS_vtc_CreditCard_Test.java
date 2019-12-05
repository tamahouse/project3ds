package p2_disabled;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_shortcode;
import automation.project3ds.PS_vtc;
import automation.project3ds.PS_vtc2;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

@Listeners(automation.project3ds.ExtentListener.class)
public class PS_vtc_CreditCard_Test extends BaseTest{

	String shortcode = PS_shortcode.VTC;
	String co_id = "220";
	String url = "http://feature-pgp-494.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).isCustom().generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);
		PS_vtc ps = (PS_vtc) object;
		PS_vtc2 ps2 = ps.getNewWindows();
		ps2.createCreditCardPayment();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(shortcode);
		Assertion.assertConverted(cl_id);
	}
}
