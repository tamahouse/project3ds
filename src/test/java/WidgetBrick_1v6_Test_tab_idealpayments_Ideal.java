import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetPaymentMethods;

public class WidgetBrick_1v6_Test_tab_idealpayments_Ideal {

	String host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D=144&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";

	static Driver driver;
	String id = "tab_idealpayments";

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void paymentSuccess() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(id);
		WidgetMainFrame.clickBuyButton();
		List<String> banks = WidgetPaymentMethods.tab_idealpayments_Ideal.getListBankIds();
		Random random = new Random();
		String bank = banks.get(random.nextInt(banks.size()));
		WidgetPaymentMethods.tab_idealpayments_Ideal.clickBank(bank);
		WidgetPaymentMethods.tab_idealpayments_Ideal.finishPaymentStep();
		WidgetPaymentMethods.tab_idealpayments_Ideal.getThankyou();
	}

}