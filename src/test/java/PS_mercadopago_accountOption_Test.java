import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.PS_mercadopago;
import automation.project3ds.PS_mercadopago2;
import automation.project3ds.PS_mercadopago3;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetPage;


public class PS_mercadopago_accountOption_Test extends BaseTest{

	String shortcode = "mercadopago";
	String co_id = "30";
	String url = "http://feature-pwl-2070.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).generate();
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		widgetPage.getMultiWidget().click(shortcode);
		PS_mercadopago ps = new PS_mercadopago(driver);
		PS_mercadopago2 ps2 = ps.createPayment();
		ThankyouPage tk = ps2.finishPayment_AccountOption();
		String url = tk.getCurrentUrl();
		String cl_id = BasePage.getBetweenText(url, "external_reference=d", "&");
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
}
