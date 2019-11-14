package threedsOFF;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.PS_ccbrazil_compact;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_ccbrazil_Test_3dsOFF extends BaseTest{
	
	String shortcode = "ccbrazil";
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "30";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).generate();
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(url);
		CodeFeature.setCodeFeature(driver, url, CodeFeature.CF_3DS_V2, false);
	}
	

	
	@Test
	public void execute() throws Exception {
		driver.get(host);
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_ccbrazil_compact ps = (PS_ccbrazil_compact) widgetPage.getMultiWidget().createClick(shortcode);
		ps.createPayment();
		widgetPage.getMultiWidget().waitForThankYou();
		String email = ps.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.assertConverted(cl_id);
	}

}