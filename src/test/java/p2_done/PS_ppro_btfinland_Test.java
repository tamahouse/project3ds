package p2_done;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_btfinland;
import automation.project3ds.PS_ppro;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;


public class PS_ppro_btfinland_Test extends BaseTest{

	String shortcode = PS_shortcode.BTFINLAND;
	String co_id = "70";
//	String url = "http://feature-pgp-494.wallapi.bamboo.stuffio.com";
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
		PS_btfinland ps = (PS_btfinland) object;
		PS_ppro ps2 = ps.createPayment();
		ThankyouPage tk = ps2.finishPayment();
		String unique = tk.getUnique("txid=");
		String cl_id = Pslog.get_cl_id_email_Fasterpay(unique);
		Assertion.assertConverted(cl_id);
//		Assertion.get().assertEquals(BasePage.isNumeric(cl_id), true,"[cl_id available]");
//		Assertion.end();
//		PS_Pagseguro_API.setStatus(transactionCode, "3");
//		String email = ps.getEmail();
//		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
//		Assertion.assertConverted(cl_id);
//		String print = email + " "+transactionCode;
//		System.out.println(print);
//		ExtentManager.logInfo(print);
	}
}
