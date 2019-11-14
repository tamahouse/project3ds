import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_przelewy24;
import automation.project3ds.PS_przelewy24_2;
import automation.project3ds.WidgetPage;


public class PS_przelewy24_NOTSelectBank_Test2 extends BaseTest{

	String shortcode = "przelewy24";
	String co_id = "164";
//	String url = "http://feature-pwl-1969.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).isUidTimeline().co_id(co_id).generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_przelewy24 ps = (PS_przelewy24) widgetPage.getMultiWidget().createClick(shortcode);
		PS_przelewy24_2 ps2 = ps.createPaymentWithoutSelectBank();
		ps2.finishPaymentWithoutSelectBank();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
}
