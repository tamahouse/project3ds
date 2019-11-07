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
import automation.project3ds.PS_boletobancario;
import automation.project3ds.PS_boletobr;
import automation.project3ds.PS_boletobr;
import automation.project3ds.Pslog;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetPage;


public class PS_boletobancario_Bussiness_Test extends BaseTest{

	String a_id = "99894";
	String shortcode = "boletobancario";
	String co_id = "30";
//	String url = "http://feature-pwl-2048.wallapi.bamboo.stuffio.com";
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
		widgetMulti.clickPrice("5.00", "BRL");
		widgetMulti.clickBuyButton();
		PS_boletobancario ps = new PS_boletobancario(driver);
		ps.createBussinessPayment();
		String cl_id = Network.getCl_id(driver);
		System.out.println(cl_id);
		Assertion.get().assertEquals(BasePage.isNumeric(cl_id), true,"[cl_id available]");
		Assertion.end();
//		Assertion.assertConverted(cl_id);
//		String print = email + " "+transactionCode;
//		System.out.println(print);
//		ExtentManager.logInfo(print);
	}
}
