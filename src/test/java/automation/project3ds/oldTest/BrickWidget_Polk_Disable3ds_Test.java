package automation.project3ds.oldTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.BrickWidgetHome;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.Pslog;

public class BrickWidget_Polk_Disable3ds_Test {

	final String host1107 = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=CC+Embarcadero+test+%28JammyWall%29%5B101601%5D&data%5Ba_id%5D=101601&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069cze3&data%5Bag_type%5D=fixed";
	final String host1108 = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bag_type%5D=fixed";
	final String hostBrickHTML = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/brick/test-staging/brick.html";
//	String host = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+Polk+%28Test+Company%29%5B99907%5D&data%5Ba_id%5D=99907&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bps%5D=gateway&data%5Bag_type%5D=fixed";
//	String host = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069cze3&data%5Bps%5D=dummy&data%5Bag_type%5D=fixed";
//	String host = "http://feature-brick-test.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+Polk+%28Test+Company%29%5B99907%5D&data%5Ba_id%5D=99907&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bps%5D=gateway&data%5Bag_type%5D=fixed";
	static Driver driver;
	String filePath = "src\\main\\java\\utility\\cardNumber_v1.xlsx";
	List<Map<String, String>> mapList;

	public void setUp() throws Exception {
		Login.login(host);
//		mapList = Action.getTestData(filePath);
	}
	
//	**Host test 3ds_v2
	String host = "http://feature-ccg-830.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+Polk+%28Test+Company%29%5B99907%5D&data%5Ba_id%5D=99907&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=EUR&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=";
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "data")
	public Object[][] data() throws Exception {
		this.setUp();
		List<Object[]> temp = new ArrayList<Object[]>();
//		String PAN = map.get("PAN");
		String PAN = "5444870724493746";
		Object[] obj = new Object[] { PAN, host };
		temp.add(obj);
		Object[][] data = temp.toArray(new Object[][] {});
		return data;
	}

	@Test(dataProvider = "data")
	public void execute(String cardNumber, String host) throws Exception {
		ExtentManager.logInfo("CardNumber: " + cardNumber);
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String refID = BrickWidgetHome.returnRefID(cardNumber);
		String t_id = Pslog.getTID(refID);
		System.out.println(cardNumber + " : " + refID + " : " + t_id);
		ExtentManager.logInfo("t_id: " + t_id);
	}

}
