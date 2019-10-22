package threedsON;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_gateway_brick_1v5;
import automation.project3ds.PS_gateway_old;
import automation.project3ds.Pslog;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class PS_gateway_widget_embarcalero2_ON {
	
	String shortcode = "gateway";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).generate();
	
	
	static Driver driver;

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
		CodeFeature.setCF(url, CodeFeature.CF_3DS_V2, true);
	}
	

	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}
	
	@BeforeMethod
	public void openBrick() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMulti.clickPaymentMethod(shortcode);
//		WidgetMulti.clickPrice(0);
		WidgetMulti.clickBuyButton();
	}
	
	
	@Test
	public void v2() throws Exception {
//		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		PS_gateway_brick_1v5 brick = new PS_gateway_brick_1v5();
		brick.setBrick();
		brick.setCardNumber(cardNumber);
		brick.createPayment();
		brick.clickProcessButton();
		brick.finish3dsV2ON();
		WidgetMainFrame.waitForThankYou();
	}

}
