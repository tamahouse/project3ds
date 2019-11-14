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
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_gateway_old_widget_embarcalero1_ON  extends BaseTest_Z2 {
	
	String shortcode = "gateway";
	String url = "http://feature-ccg-843.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101677";
	int ps_id = 132;
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).isCustom("email", "meo+meo%40spam4.me").generate();

	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	

	
	@Test
	public void v1() throws Exception {
		driver.get(host);
//		String cardNumber = "4012001037141112";
		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
		WidgetPage widgetPage = new WidgetPage(driver);
		widgetPage.getMultiWidget().click(shortcode);
		PS_gateway_old ps = widgetPage.getMultiWidget().getPS_gateway_old();
		ps.setCoID(co_id);
		ps.setCardNumber(cardNumber);
		ps.createPayment();
		String unique = ps.getUnique();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(unique);
		System.out.println(cl_id);
		ps.finish3dsV1ON();
		widgetPage.getMultiWidget().waitForThankYou();
		Assertion.assertConverted(cl_id);
	}
	

}
