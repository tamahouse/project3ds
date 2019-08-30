package automation.project3ds.oldTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.BrickWidgetHome;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.Pslog;

public class BrickWidget_ON1_Test {

	final String host1107 = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=CC+Embarcadero+test+%28JammyWall%29%5B101601%5D&data%5Ba_id%5D=101601&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069cze3&data%5Bag_type%5D=fixed";
	final String host1108 = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bag_type%5D=fixed";
	final String hostBrickHTML = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/brick/test-staging/brick.html";
//	String host = host1108;
//	String host = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bag_type%5D=fixed";
//	String host = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806900cze3&data%5Bps%5D=dummy&data%5Bag_type%5D=fixed";
	String host = "http://feature-brick-test.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806900&data%5Bps%5D=dummy&data%5Bag_type%5D=fixed";
	static Driver driver;
	String filePath = "src\\main\\java\\utility\\cardNumber_v1.xlsx";
	List<Map<String, String>> mapList;

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
		mapList = Action.getTestData(filePath);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "data")
	public Object[][] data() throws Exception {
		List<Object[]> temp = new ArrayList<Object[]>();
		for (Map<String, String> map : mapList) {
			String PAN = map.get("PAN");
			Object[] obj = new Object[] { PAN, map };
			temp.add(obj);
		}
		Object[][] data = temp.toArray(new Object[][] {});
		return data;
	}

	@Test(dataProvider = "data")
	public void execute(String cardNumber, Map<String, String> map) throws Exception {
		ExtentManager.logInfo("CardNumber: " + cardNumber);
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String refID = BrickWidgetHome.returnRefID(cardNumber);
		String t_id = Pslog.getTID(refID);
		System.out.println(cardNumber + " : " + refID + " : " + t_id);
		ExtentManager.logInfo("t_id: " + t_id);
		Action.assertResultV1(t_id, map);
	}

}
