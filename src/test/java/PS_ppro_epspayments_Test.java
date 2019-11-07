import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.Network;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.PS_boletobr;
import automation.project3ds.PS_btfinland;
import automation.project3ds.PS_epspayments;
import automation.project3ds.PS_idealpayments;
import automation.project3ds.PS_multibanco;
import automation.project3ds.PS_ppro;
import automation.project3ds.PS_boletobr;
import automation.project3ds.Pslog;
import automation.project3ds.ThankyouPage;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;


public class PS_ppro_epspayments_Test extends BaseTest{

	String a_id = "99894";
	String shortcode = "epspayments";
	String co_id = "1";
	String url = "http://feature-pwl-2054.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).isCustom().generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
	}
	
	@Test
	public void execute() throws Exception {
		
		WidgetPage widgetPage = new WidgetPage(driver);
		WidgetMulti widgetMulti = widgetPage.getMultiWidget();
		widgetMulti.clickPaymentMethod(shortcode);
		widgetMulti.clickPrice(0);
		widgetMulti.clickBuyButton();
		PS_epspayments ps = new PS_epspayments(driver);
		PS_ppro ps2 = ps.createPayment();
		ps2.finishPaymentStep();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		String reportUrl = url+ "/admin/payment-clicks/transaction-details?click="+cl_id;
		driver.get(reportUrl);
		Assertion.assertConverted(cl_id);
//		Assertion.get().assertEquals(BasePage.isNumeric(cl_id), true,"[cl_id available]");
//		Assertion.end();
//		PS_Pagseguro_API.setStatus(transactionCode, "3");
//		String email = ps.getEmail();
//		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
//		Assertion.assertConverted(cl_id);
//		String print = email + " "+transactionCode;
//		System.out.println(print);
//		ExtentManager.logInfo(print);
	}
}
