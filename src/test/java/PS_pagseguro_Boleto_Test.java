import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;


public class PS_pagseguro_Boleto_Test extends BaseTest{

	String shortcode = PS_shortcode.PAGSEGURO;
	String co_id = "30";
	String url = "http://feature-pgp-494.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).widget(widget).isPrice(price, currency).generate();
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);
		PS_pagseguro ps = (PS_pagseguro) object;
		PS_Pagseguro2 ps2 = ps.createPayment();
		String email = ps.getEmail();
		String transactionCode = ps2.createBoletoPayment();
		PS_Pagseguro_API.setStatus(transactionCode, "3");
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
}
