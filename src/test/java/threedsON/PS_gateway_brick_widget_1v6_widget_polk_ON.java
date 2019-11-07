package threedsON;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Brick_1v5;
import automation.project3ds.Brick_1v6;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.Network;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_gateway_brick_1v5;
import automation.project3ds.PS_gateway_brick_1v6;
import automation.project3ds.PS_gateway_compact;
import automation.project3ds.Pslog;
import automation.project3ds.WallapiAPI;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_gateway_brick_widget_1v6_widget_polk_ON  extends BaseTest {
	
	String shortcode = "gateway";
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String url = "http://feature-brick-test.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).isBrick16().isUidTimeline().generate();
	int ps_id = 132;
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	

	
	@Test
	public void polk() throws Exception {
		driver.get(host);
		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		WidgetPage widgetPage = new WidgetPage(driver);
		widgetPage.getMultiWidget().click(shortcode);
		PS_gateway_brick_1v6 gateway_1v6 = widgetPage.getMultiWidget().getPS_gateway_brick_1v6();
		Brick_1v6 brick = gateway_1v6.getBrick_1v6();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		brick.clickProcessButton();
		gateway_1v6 = widgetPage.getMultiWidget().getPS_gateway_brick_1v6();
		gateway_1v6.finish3dsPolkOFF();
		widgetPage.getMultiWidget().waitForThankYou();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
	

}
