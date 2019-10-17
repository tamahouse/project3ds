import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetMulti;

public class PS_Neosurf_Test {
	
	String shortcode = "neosurf";
	String url = "http://feature-pwl-2007.wallapi.bamboo.stuffio.com";
	String co_id = "2";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).generate();
	
	
	static Driver driver;

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
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
		WidgetMulti.clickPrice(0);
		WidgetMulti.clickBuyButton();
	}
	
	@Test
	public void execute() throws Exception {
		PS_Neosurf.setPin();
		PS_Neosurf.clickContinueButton();
		PS_Neosurf.waitForCompleted();
	}

}
