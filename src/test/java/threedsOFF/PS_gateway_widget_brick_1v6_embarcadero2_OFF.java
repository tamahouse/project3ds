package threedsOFF;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.Brick_1v6;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_gateway_brick_1v6;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_gateway_widget_brick_1v6_embarcadero2_OFF  extends BaseTest_Z2 {
	
	String shortcode = PS_shortcode.GATEWAY_BRICK_16;
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).a_id(a_id).co_id(co_id).isBrick16().isUidTimeline().generate();
	int ps_id = 132;
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, false);
	}
	

	
	
	@Test
	public void v2() throws Exception {
		driver.get(host);
//		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);;
		PS_gateway_brick_1v6 gateway_1v6 = (PS_gateway_brick_1v6) object;
		Brick_1v6 brick = gateway_1v6.getBrick_1v6();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		String email = brick.getEmail();
//		brick.clickProcessButton();
//		gateway_1v6 = (PS_gateway_brick_1v6)widgetPage.reGetPS(widget, shortcode, logo);
//		gateway_1v6.finish3dsV2ON();
		widgetPage.waitForThankyou(widget);
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}

}
