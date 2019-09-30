package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import automation.project3ds.Driver.Browser;

public class AnnotationPage {

public static Driver driver;

	public static class Type {
		public static String P1 = "p1";
		public static String T3 = "t3";
		public static String PW = "pw";
		public static String MULTI = "multi";
		public static String UNI = "uni";
		public static String V5 = "v5";
	}
	
	public static Driver getDriver() {
		if(driver == null) {
			driver = new Driver(Browser.Chrome);
		}
		return driver;
	}
	
	static String p1 = "http://feature-wid-105.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
	static String t3 = "http://feature-wid-105.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%2887535%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=t3&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1&data%5Bcustom%5D%5Bversion%5D=1.2";
	static String pw = "http://feature-wid-105.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=pw&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
	
	public static Map<String, String> hostMap = new HashMap<String, String>();
	static {
		hostMap.put("p1", p1);
		hostMap.put("t3", t3);
		hostMap.put("pw", pw);
	}
	
	public static void screenShot(String filePath) {
		Driver.screenShot(driver.getWebDriver(), filePath);
	}
}
