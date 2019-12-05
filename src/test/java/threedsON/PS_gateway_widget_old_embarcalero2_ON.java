package threedsON;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_gateway_old;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_gateway_widget_old_embarcalero2_ON  extends BaseTest_Z2 {
	
	String shortcode = PS_shortcode.GATEWAY_OLD;
//	String url = "http://feature-ccg-843.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101677";
	int ps_id = 132;
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).a_id(a_id).co_id(co_id).generate();

	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	

	
	
	@Test
	public void v2() throws Exception {
		driver.get(host);
//		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
		String cardNumber = "5200000000001096";
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);;
		PS_gateway_old ps = (PS_gateway_old) object;
		ps.setCoID(co_id);
		ps.setCardNumber(cardNumber);
		ps.createPayment();
		String unique = ps.getUnique();
		ps.finish3dsV2ON();
		widgetPage.waitForThankyou(widget);
		String cl_id = Pslog.get_cl_id_email_Fasterpay(unique);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}

}
