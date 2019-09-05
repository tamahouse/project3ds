import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.Widget3dsNoIframe;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;

public class WidgetBrick_1v6_Test_StaticLink {

	String host;
	String type;
	
	static Driver driver;

	@Parameters({"type"})
	@BeforeClass
	public void setUp(String type) throws Exception {
		this.type = type;
		host = AnnotationPage.hostMap.get(type);
		Login.login(host);
	}

	
	static Element whiteSpace;

	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void privacyPolicy() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPrivacyPolicy();
		driver.switchToWindows("test-offerwall", false);
		String url = driver.getCurrentUrl();
		Assertion.get().assertEquals(url, "https://www.paymentwall.com/en/privacypolicy", "[Privacy Policy]");
		Assertion.end();
	}
	
	@Test
	public void terms() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.clickTerms();
		driver.switchToWindows("test-offerwall", false);
		String url = driver.getCurrentUrl();
		Assertion.get().assertEquals(url, "https://www.paymentwall.com/en/terms", "[Term]");
		Assertion.end();
	}
	
	@Test
	public void processToBank() throws Exception {
		String cardNumber = "4000000000000002";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("cardNumber", cardNumber).create();
		String cl_id = WidgetIframecc.getRefId();
		System.out.println(cardNumber + " " +cl_id );
		WidgetIframecc.clickProcessButton();
		Widget3dsNoIframe.success3ds();
		Boolean x = WidgetMainFrame.getCompleteMessage(type);
		Assertion.isSuccess(x);
		Assertion.end();
	}
	
	@Test
	public void tryAgain() throws Exception {
		String cardNumber = "4000000000000002";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("cardNumber", cardNumber).create();
		for(int i = 0; i< 5; i++) {
			WidgetIframecc.clickTryAgain();
			WidgetIframecc.clickBuyButton();
		}
	}
	
	@Test
	public void storedCardSwitch() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		for(int i = 0; i< 5; i++) {
			WidgetIframecc.clickUserDifferentCard();
			WidgetIframecc.clickBackToStoredCard();
		}
	}

	
	
		
		

}
