import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_mollie;
import automation.project3ds.PS_yandexmoney;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;

public class PS_yandex_yandexmoney_url_test extends BaseTest{
	
	String shortcode = "yandexmoney";
	String url = "http://feature-pwl-2061.wallapi.bamboo.stuffio.com";
	String co_id = "170";
	String testUrl = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\testUrl.xlsx";
	String host = AnnotationPage.WallapiUrl.host(url).isUidTimeline().co_id(co_id).isCustom("success_url", "any").generate();
	
//	static Driver driver;
//
//	@BeforeClass
//	public void setUp() throws Exception {
//		Login.login(host);
//	}
//	
//
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//		AnnotationPage.driver = null;
//	}
	
	
	@BeforeClass
	public void setUp() {
		this.driver = new Driver(browser);
		login(host);
	}
	
//	@BeforeMethod
//	public void openBrick() throws Exception {
//		driver.get(host);
//		ps = (PS_Neosurf) widgetMulti.createClick(shortcode);
//	}

	
	@Test
	public void execute() throws Exception {
		String testUrl = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\testUrl.xlsx";
		List<Map<String, String>> data = Action.getTestData(testUrl, "Sheet1");
		for(Map<String, String> map: data) {
			driver.switchToWindows("test-offerwall", true);
			String url = map.get("URL");
			System.out.println(url);
			WidgetPage widgetPage = new WidgetPage(driver);
			widgetPage.setCustomItem(AnnotationPage.WallapiUrl.SUCCESS_URL, url);
			widgetPage.clickSubmitButton();
			WidgetMulti widgetMulti = widgetPage.getMultiWidget();
			widgetMulti.clickPaymentMethod(shortcode);
			widgetMulti.clickPrice("50.00", "RUB");
			widgetMulti.clickBuyButton();
			PS_yandexmoney ps = new PS_yandexmoney(driver);
			ps.clickPayButton();
			ps.clickkBackToStoreCompletedButton();
//			String transID = ps.getTransId();
//			System.out.println(transID);
//			String cl_id = Pslog.get_cl_id_email_Fasterpay(transID);
//			System.out.println(cl_id);
//			Assertion.assertConverted(cl_id);
//			String cl_id = Pslog.get_cl_id_email_Fasterpay("giropay");
//			Assertion.assertConverted(cl_id);
		}

	}

}
