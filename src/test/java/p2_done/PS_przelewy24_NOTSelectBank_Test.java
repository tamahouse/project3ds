package p2_done;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_przelewy24;
import automation.project3ds.PS_przelewy24_2;
import automation.project3ds.PS_shortcode;
import automation.project3ds.ThankyouPage;
import automation.project3ds.WidgetPage;


public class PS_przelewy24_NOTSelectBank_Test extends BaseTest{

	String shortcode = PS_shortcode.PRZELEWY24;
	String co_id = "164";
//	String url = "http://feature-pwg-1166.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).isUidTimeline().co_id(co_id).generate();
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
		PS_przelewy24 ps = (PS_przelewy24) object;
		PS_przelewy24_2 ps2 = ps.createPaymentWithoutSelectBank();
		ThankyouPage tk = ps2.finishPaymentWithoutSelectBank();
		String cl_id = tk.getClickID("refId/d");
		Assertion.assertConverted(cl_id);
	}
}
