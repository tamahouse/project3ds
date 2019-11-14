package threedsOFF;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.BaseTest;
import automation.project3ds.Brick_1v5;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.PS_gateway_brick_1v5;
import automation.project3ds.WidgetPage;

public class PS_gateway_widget_embarcalero1_OFF  extends BaseTest{
	
	String shortcode = "gateway";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).generate();
	
	

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
		widgetPage.getMultiWidget().click(shortcode);
		PS_gateway_brick_1v5 gateway_1v5 = widgetPage.getMultiWidget().getPS_gateway_brick_1v5();
		Brick_1v5 brick = gateway_1v5.getBrick_1v5();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		brick.clickProcessButton();
		gateway_1v5 = widgetPage.getMultiWidget().getPS_gateway_brick_1v5();
		gateway_1v5.finish3dsV1OFF();
		widgetPage.getMultiWidget().waitForThankYou();
	}
	
}
