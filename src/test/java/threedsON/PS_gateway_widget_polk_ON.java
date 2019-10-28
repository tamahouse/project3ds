package threedsON;
import org.openqa.selenium.WebDriverException;
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
import automation.project3ds.PS_gateway_compact;
import automation.project3ds.Pslog;
import automation.project3ds.WallapiAPI;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_gateway_widget_polk_ON  extends BaseTest {
	
	String shortcode = "gateway";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).generate();
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	

	
	@Test
	public void polk() throws Exception {
		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_gateway_brick_1v5 gateway_1v5 = widgetPage.getMultiWidget().getPS_gateway_brick_1v5();
		Brick_1v5 brick = gateway_1v5.getBrick_1v5();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		brick.clickProcessButton();
		gateway_1v5 = widgetPage.getMultiWidget().getPS_gateway_brick_1v5();
		gateway_1v5.finish3dsPolkOFF();
		widgetPage.getMultiWidget().waitForThankYou();
	}
	

}
