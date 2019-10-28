package threedsON;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.BaseTest;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_ccbrazil;
import automation.project3ds.Pslog;
import automation.project3ds.WallapiAPI;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_ccbrazilhipercard_Test_3dsON extends BaseTest {
	
	String shortcode = "ccbrazilhipercard";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "30";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).generate();
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
		PS_ccbrazil ps = (PS_ccbrazil) widgetPage.getMultiWidget().createClick(shortcode);
		ps.setCardNumber(cardNumber);
		ps.createPayment();
		widgetPage.getMultiWidget().waitForThankYou();
//		String email = PS_ccbrazil.email;
//		String clickID = Pslog.get_cl_id_email_Fasterpay(email);
//		System.out.println(clickID);
	}

}
