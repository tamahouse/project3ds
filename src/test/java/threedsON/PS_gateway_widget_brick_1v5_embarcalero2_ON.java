package threedsON;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.Brick_1v5;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_gateway_brick_1v5;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_gateway_widget_brick_1v5_embarcalero2_ON  extends BaseTest_Z2 {
	
	String shortcode = PS_shortcode.GATEWAY_BRICK_15;
//	String url = "http://feature-bt2-116.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).a_id(a_id).co_id(co_id).isUidTimeline().generate();
	int ps_id = 132;
	

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
//		String cardNumber = "5555555555554477";
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);
		PS_gateway_brick_1v5 gateway_1v5 = (PS_gateway_brick_1v5) object;
		Brick_1v5 brick = gateway_1v5.getBrick_1v5();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		brick.clickProcessButton();
		String email = brick.getEmail();
		gateway_1v5 = (PS_gateway_brick_1v5)widgetPage.reGetPS(widget, shortcode, logo);
		gateway_1v5.finish3dsV2ON();
		widgetPage.waitForThankyou(widget);
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}

}
