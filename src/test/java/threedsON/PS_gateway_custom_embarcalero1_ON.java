package threedsON;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.BaseTest;
import automation.project3ds.Brick_1v5;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_gateway_brick_1v5;
import automation.project3ds.PS_gateway_custom;
import automation.project3ds.PS_gateway_html;
import automation.project3ds.PS_gateway_compact;
import automation.project3ds.Pslog;
import automation.project3ds.WallapiAPI;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class PS_gateway_custom_embarcalero1_ON  extends BaseTest {
	
	String shortcode = "gateway";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101696";
	String host = url+"/test-staging-brick/brick-custom-new-widget.html";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(url+"/admin/test-offerwall");
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	
	
	@Test
	public void v1() throws Exception {
//		String cardNumber = "4012001037141112";
		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		PS_gateway_custom custom = new PS_gateway_custom(driver);
		custom.setCardNumber(cardNumber);
		custom.createPayment();
		custom.finish3dsV1ON();
	}
	
	

}
