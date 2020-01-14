package p2_done;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_pagseguro;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_mercadopago;
import automation.project3ds.PS_mercadopago2;
import automation.project3ds.PS_mercadopago3;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetPage;


public class PS_mercadopago_NOT_login_Test extends BaseTest{

	String shortcode = PS_shortcode.MERCADOPAGO;
	String co_id = "30";
//	String url = "http://feature-pwl-2070.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).co_id(co_id).generate();
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);
		PS_mercadopago ps = (PS_mercadopago) object;
		PS_mercadopago2 ps2 = ps.createPayment();
		ps2.finishPayment_NOT_login();
		widgetPage.waitForThankyou(widget);
		String email = ps.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.assertConverted(cl_id);
	}
}
