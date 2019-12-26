
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_banktransfermexico;
import automation.project3ds.PS_banktransfermexico2;
import automation.project3ds.PS_boleto;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_epspayment;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;


public class PS_astropay_banktransfermexico_Test extends BaseTest{

	String shortcode = PS_shortcode.BANKTRANSFERMEXICO;
	String co_id = "132";
//	String price = "5.00";
//	String currency = "BRL";
//	String url = "http://feature-pwl-2048.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).co_id(co_id).isCustom().generate();
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
		PS_banktransfermexico ps = (PS_banktransfermexico) object;
		PS_banktransfermexico2 ps2 = ps.getNewWindows();
		ps2.clickBank();
		ps2.createPayment();
		String email = ps2.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
		Assertion.get().assertEquals(BasePage.isNumeric(cl_id), true,"[cl_id available]");
		Assertion.end();
//		Assertion.assertConverted(cl_id);
//		String print = email + " "+transactionCode;
//		System.out.println(print);
//		ExtentManager.logInfo(print);
	}
}
