package threedsOFF;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.Brick_1v5;
import automation.project3ds.Brick_1v5_corel;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Driver.Browser;
import automation.project3ds.MailHog;
import automation.project3ds.Network;
import automation.project3ds.PS_gateway_brick_1v5;
import automation.project3ds.PS_gateway_brick_1v5_corel;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;
import automation.project3ds.Widget_p2_3;

public class PS_gateway_corel_p2_3_embarcadero1  extends BaseTest_Z2 {
	
	String shortcode = PS_shortcode.GATEWAY_CORAL_15;
//	String url = "http://feature-wid-176.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "99708";
	String widget = "p2_3";
	String host = AnnotationPage.WallapiUrl.host(url).uni(widget,shortcode).co_id(co_id).isUidTimeline().a_id(a_id).generate();
	
	int ps_id = 132;
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, false);
	}
	
	@Test
	public void v1() throws Exception {
		driver.get(host);
//		String cardNumber = "4012001037141112";
		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);;
		PS_gateway_brick_1v5_corel gateway_1v5 = (PS_gateway_brick_1v5_corel) object;
		Brick_1v5_corel brick = gateway_1v5.getBrick_1v5_corel();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		String email = brick.getEmail();
		Boolean isMailed = MailHog.isMailSentTo(email);
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.get().assertEquals(isMailed, true,"[isMailed]");
		Assertion.assertConverted(cl_id);
		
	}
	
	

}
