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
import automation.project3ds.PS_ccbrazil_old;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_ccbrazil_old_widget_Test_3dsON2 extends BaseTest_Z2{
	
	String shortcode = "ccbrazil";
	String co_id = "30";
	String a_id = "101693";
//	String url = "http://feature-bt2-116.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).generate();
	

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
		widgetPage.getMultiWidget().click(shortcode, "1.00", "US");
		PS_ccbrazil_old ps = new PS_ccbrazil_old(driver);
		ps.createPayment();
		widgetPage.getMultiWidget().waitForThankYou();
		String email = ps.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.assertConverted(cl_id);
	}

}
