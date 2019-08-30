package additional;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.WidgetMainFrame;

public class FindMobiamo {

	String host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1&data%5Bcustom%5D%5Ba%5D=dfsdfsdfsd";

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
	public void findMobiamo() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		Select select = new Select(driver.getElement(By.id("country_select")));
		List<WebElement> list = select.getOptions();
		List<String> listValue = new ArrayList<String>();
		for (WebElement webElement : list) {
			String value = webElement.getAttribute("value");
			listValue.add(value);
//			String country = webElement.getText();
//			System.out.println(value + "-"+country);
		}
		for(String value: listValue) {
			host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D="+value+"&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1&data%5Bcustom%5D%5Ba%5D=dfsdfsdfsd";
			driver.get(host);
			List<String> listPayment = WidgetMainFrame.getListPayment();
			String payment = "";
			for(String str: listPayment) {
				payment = payment + " " + str; 
			}
			System.out.println(value + payment);
			
		}
	}
}
