import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.MyTunnel;
import automation.project3ds.WidgetMainFrame;

public class WidgetBrick_1v6_Test_Screenshot {

	String host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";

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

	@Test
	public void saveScreenshot() throws Exception {
		String timestamp = String.valueOf(System.currentTimeMillis());
		driver = AnnotationPage.getDriver();
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src\\main\\java\\utility\\paymentMethodByCountry.xlsx");
		Recordset record = connection.executeQuery("select * from paymentMethod");
		List<Map<String, String>> list = MyTunnel.getMap(record);
		for (Map<String, String> map : list) {
			String co_id = map.get("co_id");
			System.out.println(co_id);
			String type = map.get("paymentMethod");
			host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D="
					+ co_id
					+ "&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
			driver.get(host);
			WidgetMainFrame.clickPaymentMethod(type);
			WidgetMainFrame.clickBuyButton();
			driver.screenShot("screenShot/" + timestamp + "/" + type + ".png");
			List<String> tabs = WidgetMainFrame.getRedirectWindows();
			if (tabs != null) {
				for (int i = 1; i < tabs.size(); i++) {
					String tab = tabs.get(i);
					driver.switchTo().window(tab);
					driver.screenShot("screenShot/" + timestamp + "/" + type + ".png");
					driver.close();
				}
				driver.switchTo().window(tabs.get(0));
			}
			
		}
	}

}
