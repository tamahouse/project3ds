package threedsOFF;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_gateway_compact;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_gateway_widget_compact_polk_OFF  extends BaseTest_Z2 {
	
	String shortcode = PS_shortcode.GATEWAY_COMPACT;
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101707";
	int ps_id = 132;
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).isUidTimeline().generate();

	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, false);
	}
	

	
	@Test
	public void polk() throws Exception {
		driver.get(host);
		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);;
		PS_gateway_compact ps = (PS_gateway_compact) object;
		ps.setCoID(co_id);
		ps.setCardNumber(cardNumber);
		ps.createPayment();
		ps.finish3dsPolkOFF();
		widgetPage.waitForThankyou(widget);
		String unique = ps.getUnique();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(unique);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}

}
