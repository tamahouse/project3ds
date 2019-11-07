package threedsON;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.Network;
import automation.project3ds.PS_Neosurf;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_gateway_compact;
import automation.project3ds.Pslog;
import automation.project3ds.WallapiAPI;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_gateway_compact_widget_polk_ON  extends BaseTest {
	
	String shortcode = "gateway";
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String url = "http://feature-brick-test.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101707";
	int ps_id = 132;
	String host = AnnotationPage.WallapiUrl.host(url).a_id(a_id).co_id(co_id).generate();

	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	

	
	@Test
	public void polk() throws Exception {
		driver.get(host);
		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
		WidgetPage widgetPage = new WidgetPage(driver);
		widgetPage.getMultiWidget().click(shortcode);
		PS_gateway_compact widget = widgetPage.getMultiWidget().getPS_gateway_compact();
		widget.setCoID(co_id);
		widget.setCardNumber(cardNumber);
		widget.createPayment();
		widget.finish3dsPolkOFF();
		widgetPage.getMultiWidget().waitForThankYou();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}

}
