package threedsON;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.PS_ccbrazil_compact;
import automation.project3ds.PS_shortcode;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_ccbrazilhipercard_compact_widget_Test_3dsON extends BaseTest_Z2 {
	
	String shortcode = PS_shortcode.CCBRAZILHIPERCARD_COMPACT;
	String co_id = "30";
	String a_id = "101695";
//	String url = "http://feature-bt2-116.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).widget(widget).isPrice(price, currency).co_id(co_id).a_id(a_id).generate();
	
	String cardNumber = "6062825624254001";
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	
	
	@Test
	public void execute() throws Exception {
		driver.get(host);
		WidgetPage widgetPage = new WidgetPage(driver);
		Object object = widgetPage.getPS(widget, shortcode,logo);
		PS_ccbrazil_compact ps = (PS_ccbrazil_compact) object;
		ps.setCardNumber(cardNumber);
		ps.createPayment();
		widgetPage.waitForThankyou(widget);
		String email = ps.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.assertConverted(cl_id);
	}

}
