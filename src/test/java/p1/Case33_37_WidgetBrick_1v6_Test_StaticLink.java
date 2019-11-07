package p1;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.Widget3dsNoIframe;
import automation.project3ds.Brick_1v6;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class Case33_37_WidgetBrick_1v6_Test_StaticLink {

	String host = AnnotationPage.hostMap.get("p1");
	
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
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
	}

	
	@Test
	public void terms() throws Exception {
		Brick_1v6.clickTerms();
		driver.switchToWindows("test-offerwall", false);
		String url = driver.getCurrentUrl();
		Assertion.get().assertEquals(url, "https://www.paymentwall.com/en/terms", "[Term]");
		Assertion.end();
	}
	
	
	@Test
	public void tryAgain() throws Exception {
		String cardNumber = "4000000000000002";
		Brick_1v6.payment("cardNumber", cardNumber).create();
		for(int i = 0; i< 5; i++) {
			Brick_1v6.clickTryAgain();
			ExtentManager.addScreenshot("tryAgain");
			Brick_1v6.clickBuyButton();
		}
	}
	
	@Test
	public void storedCardSwitch() throws Exception {
		String newhost = AnnotationPage.WallapiUrl.host(AnnotationPage.host).widget("p1").isBrick16().generate();
		driver.get(newhost);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
		for(int i = 0; i< 5; i++) {
			Brick_1v6.clickUserDifferentCard();
			Brick_1v6.clickBackToStoredCard();
		}
	}

	
	
		
		

}
