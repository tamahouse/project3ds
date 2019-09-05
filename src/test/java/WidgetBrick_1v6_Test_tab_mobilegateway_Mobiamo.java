import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetPaymentMethods;

public class WidgetBrick_1v6_Test_tab_mobilegateway_Mobiamo {
	String host;
	String type;
	
	static Driver driver;

	@Parameters({"type"})
	@BeforeClass
	public void setUp(String type) throws Exception {
		this.type = type;
		host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D="+type+"&data%5Bco_id%5D=14&data%5Buid%5D=test_user_chase&data%5Bps%5D=dummy&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
		Login.login(host);
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src\\main\\java\\utility\\paymentMethodByCountry.xlsx");
		Recordset record = connection.executeQuery("select * from paymentMethod where p1_paymentMethod = '"+id+"'");
		record.next();
		id = record.getField(type+"_paymentMethod");
	}

	
	String id = "tab_mobilegateway";


	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void enterPinSuccess() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetMainFrame.clickBuyButton(type);
		WidgetPaymentMethods.tab_mobilegateway_Mobiamo.setPin();
		WidgetPaymentMethods.tab_mobilegateway_Mobiamo.clickContinue();
		WidgetPaymentMethods.tab_mobilegateway_Mobiamo.getSuccessBlock();
	}

	@Test
	public void enterPinInvalid() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetMainFrame.clickBuyButton(type);
		WidgetPaymentMethods.tab_mobilegateway_Mobiamo.setPin("ANYOTHER");
		WidgetPaymentMethods.tab_mobilegateway_Mobiamo.clickContinue();
		Element error = WidgetPaymentMethods.tab_mobilegateway_Mobiamo.getPinErrorMessage();
		String error_color = error.getCssValue("color");
		String error_value = error.getText();
		Element enterPinIcon = WidgetPaymentMethods.tab_mobilegateway_Mobiamo.getEnterPinIcon();
		Element enterPinTxb = WidgetPaymentMethods.tab_mobilegateway_Mobiamo.getEnterPinTxb();
		String border_top_color_left = enterPinIcon.getCssValue("border-top-color");
		String border_top_color_right = enterPinTxb.getCssValue("border-top-color");
		String border_botton_color_left = enterPinIcon.getCssValue("border-bottom-color");
		String border_bottom_color_right = enterPinTxb.getCssValue("border-bottom-color");
		String border_left_color = enterPinIcon.getCssValue("border-left-color");
		String border_right_color = enterPinTxb.getCssValue("border-right-color");
		String RED = "#ef5350 rgba(239, 83, 80, 1) rgb(239, 83, 80)";
		Assertion.get().assertBeContains(border_top_color_left, RED, "[border_top_color_left]");
		Assertion.get().assertBeContains(border_top_color_right, RED, "[border_top_color_right]");
		Assertion.get().assertBeContains(border_botton_color_left, RED, "[border_botton_color_left]");
		Assertion.get().assertBeContains(border_bottom_color_right, RED, "[border_bottom_color_right]");
		Assertion.get().assertBeContains(border_left_color, RED, "[border_left_color]");
		Assertion.get().assertBeContains(border_right_color, RED, "[border_right_color]");
		Assertion.get().assertBeContains(error_color, RED, "[Error text color]");
		Assertion.get().assertEquals(error_value, "Invalid code. Please try again.", "[Error text value]");
		Assertion.end();
	}
	
	@Test
	public void term() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetMainFrame.clickBuyButton(type);
		WidgetPaymentMethods.tab_mobilegateway_Mobiamo.clickTerm();
		List<String> list = driver.waitForNewTab();
		driver.switchToWindows("test-offerwall", false);
		driver.waitUrlNotBlank();
		String url = driver.getCurrentUrl();
		driver.close();
		driver.switchToWindows("test-offerwall", true);
		Assertion.get().assertEquals(url, "https://www.mobiamo.com/terms#privacy", "[Term]");
		Assertion.end();
	}

}
