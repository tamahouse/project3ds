package threedsOFF;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.BaseTest;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.PS_gateway_compact;
import automation.project3ds.WidgetPage;

public class PS_gateway_compact_embarcalero2_OFF  extends BaseTest{
	
	String shortcode = "gateway";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101707";
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).generate();
	
	

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
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_gateway_compact widget = widgetPage.getMultiWidget().getPS_gateway_compact();
		widget.co_id = co_id;
		widget.cardNumber = cardNumber;
		widget.createPayment();
		widgetPage.getMultiWidget().waitForThankYou();
	}

}
