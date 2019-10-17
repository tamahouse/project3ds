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
import automation.project3ds.PS_ccbrazil;
import automation.project3ds.Pslog;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class PS_ccbrazil_Test_3dsOFF {
	
	String shortcode = "ccbrazil";
	String url = "http://feature-pwg-1137.wallapi.bamboo.stuffio.com";
	String co_id = "30";
	String a_id = "101696";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).generate();
	
	
	static Driver driver;

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
		CodeFeature.setCF(url, CodeFeature.CF_3DS_V2, false);
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
	public void execute() throws Exception {
		PS_ccbrazil.createPayment();
		WidgetMainFrame.waitForThankYou();
		String email = PS_ccbrazil.email;
		String clickID = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(clickID);
	}

}
