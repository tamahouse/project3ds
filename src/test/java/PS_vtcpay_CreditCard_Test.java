import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.PS_vtc;
import automation.project3ds.Pslog;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetPage;

@Listeners(automation.project3ds.ExtentListener.class)
public class PS_vtcpay_CreditCard_Test extends BaseTest{

	String a_id = "99894";
	String shortcode = "vtc";
	String co_id = "220";
//	String url = "http://feature-pwl-1969.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).isCustom().generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_vtc ps = (PS_vtc) widgetPage.getMultiWidget().createClick(shortcode);
		ps.createCreditCardPayment();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(shortcode);
		Assertion.assertConverted(cl_id);
	}
}
