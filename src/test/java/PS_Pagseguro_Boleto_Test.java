import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.WidgetPage;


public class PS_Pagseguro_Boleto_Test extends BaseTest{

	String shortcode = "pagseguro";
	String co_id = "30";
//	String url = "http://feature-pwl-1969.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).isCustom().generate();
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_Pagseguro ps = (PS_Pagseguro) widgetPage.getMultiWidget().createClick(shortcode);
		PS_Pagseguro2 ps2 = ps.createPayment();
		String transactionCode = ps2.createBoletoPayment();
		PS_Pagseguro_API.setStatus(transactionCode, "3");
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
}
