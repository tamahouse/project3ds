package p2_need_whitelist_pingback;
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
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_mercadopago;
import automation.project3ds.PS_mercadopago2;
import automation.project3ds.PS_mercadopago3;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetPage;


public class PS_mercadopago_accountOption_Test extends BaseTest{

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
		PS_mercadopago3 ps3 = ps2.getLoginPage();
		ps3.login();
		driver.switchToWindows("test-offerwall",true, 30000);
		widgetPage = new WidgetPage(driver);
		ps2 = (PS_mercadopago2) widgetPage.reGetPS(widget, PS_shortcode.MERCADOPAGO2, logo);
		ThankyouPage tk = ps2.finishPayment_AccountOption();
		String url = tk.getCurrentUrl();
		String cl_id = BasePage.getBetweenText(url, "external_reference=d", "&");
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
}
