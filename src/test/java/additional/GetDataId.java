package additional;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.MyTunnel;
import automation.project3ds.WidgetMainFrame;

public class GetDataId {

	String host;
	String type;
	
//	**Host for get name
	
//	String host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894&data%5Bwidget%5D=t3&data%5Bco_id%5D=1&data%5Buid%5D=test_dark&data%5Bps%5D=dummy&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bversion%5D=1.2&data%5Bcustom%5D%5Btheme%5D=dark";
	
	
	static Driver driver;

	@Parameters({"type"})
	@BeforeClass
	public void setUp(String type) throws Exception {
		this.type = type;
		host = AnnotationPage.hostMap.get(type);
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
			String paymentMethod = map.get("paymentMethod");
			host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894&data%5Bwidget%5D=t3&data%5B"
					+ co_id
					+ "&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
			driver.get(host);
			WidgetMainFrame.getFrame().getElement(By.xpath("//*[@class='payment-option-outer']/div"));
			List<Element> meo = WidgetMainFrame.getFrame().getElements(By.xpath("//*[@class='payment-option-outer']/div"));
			Boolean x = false;
			for(Element element: meo) {
				String id = element.getAttribute("data-id");
				if(paymentMethod.contains(id)) {
					System.out.println(id);
					x = true;
					break;
				}
			}
			if(x == false) {
				System.out.println();
			}
		}
	}

}
