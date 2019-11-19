import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_ebanx;
import automation.project3ds.PS_ebanx2;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetMainFrame.WidgetType;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;


public class PS_ebanx_banrisulbrazil_Test extends BaseTest{

	String shortcode = PS_shortcode.BANRISULBRAZIL;
	String co_id = "30";
//	String url = "http://feature-pwl-2048.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).isPrice("5", "BRL").generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		WidgetMulti widgetMulti = (WidgetMulti) widgetPage.getWidgetMainFrame(WidgetType.MULTI);
		widgetMulti.click(shortcode);
		PS_ebanx ps = (PS_ebanx) widgetMulti.getPS(shortcode);
		PS_ebanx2 ps2 = ps.createPayment();
		String email = ps.getEmail();
		ps2.finishPayment();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
		Assertion.get().assertEquals(BasePage.isNumeric(cl_id), true,"[cl_id available]");
		Assertion.end();
//		PS_Pagseguro_API.setStatus(transactionCode, "3");
//		String email = ps.getEmail();
//		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
//		Assertion.assertConverted(cl_id);
//		String print = email + " "+transactionCode;
//		System.out.println(print);
//		ExtentManager.logInfo(print);
	}
}
