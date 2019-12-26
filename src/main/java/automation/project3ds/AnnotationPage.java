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
	
	public static void sleep(int milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException ignore) {
		}
	}
	
	public static String timestamp() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	
	public static Driver getDriver() {
		if(driver == null) {
			driver = new Driver(Browser.Chrome);
		}
		return driver;
	}
	
	public static String host = "http://develop.wallapi.bamboo.stuffio.com";
	public static String P2Endpoind = "http://feature-pgp-471.pg.s3.stuffio.com";
	
	static String p1 = AnnotationPage.WallapiUrl.host(host).widget("p1").isUidTimeline().isBrick16().generate();
	static String t3 = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%2887535%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=t3&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1&data%5Bcustom%5D%5Bversion%5D=1.2";
	static String pw = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=pw&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
	
	public static Map<String, String> hostMap = new HashMap<String, String>();
	static {
		hostMap.put("p1", p1);
		hostMap.put("t3", t3);
		hostMap.put("pw", pw);
	}
	
	public static void screenShot(Driver driver, String filePath) {
		Driver.screenShot(driver.getWebDriver(), filePath);
	}
	
	public static class WallapiUrl{
		
		public static String SUCCESS_URL = "success_url";
		String host = AnnotationPage.host;
//		String a_id = "99894";
		String a_id = "101693";
		String widget = "p1";
		String co_id = "1";
		String uid = "218069";
		Boolean isCustom = true;
		Boolean isDark = false;
		Boolean isBrick16 = false;
		
		String prefix = "&data%5B";
		String suffix = "%5D=";
		String custom = "";
		String customItem = "";
		String brick_1_6 = "";
		String theme = "";
		String version = "";
		String p = "";


		
		public WallapiUrl() {
			
		}
		
		public static WallapiUrl host(String host) {
			WallapiUrl url = new WallapiUrl();
			url.host = host;
			return url;
		}
		
		public static WallapiUrl host() {
			WallapiUrl url = new WallapiUrl();
			return url;
		}
		
		public WallapiUrl a_id(String a_id) {
			this.a_id = a_id;
			return this;
		}
		
		public WallapiUrl t3() {
			this.widget = "t3";
			this.version = "&data%5Bcustom%5D%5Bversion%5D=1.2";
			return this;
		}
		
		public WallapiUrl pw() {
			this.widget = "pw";
			this.version = "&data%5Bcustom%5D%5Bversion%5D=1.2";
			return this;
		}
		
		public WallapiUrl uni(String shortcode) {
			this.widget = "p2";
			this.p = prefix + "ps" + suffix+shortcode;
			return this;
		}
		
		public WallapiUrl uni(String widget, String shortcode) {
			String originShortcode = shortcode;
			try {
			originShortcode = shortcode.substring(0,shortcode.indexOf("_"));
			}catch(Exception e) {
				
			}
			this.widget = widget;
			this.p = prefix + "ps" + suffix+originShortcode;
			return this;
		}
		
		public WallapiUrl widget(String widget) {
			if(widget.equals("t3")) {
				this.t3();
			}else if(widget.equals("pw")){
				this.pw();
			}else {
			this.widget = widget;
			}
			return this;
		}
		
		public WallapiUrl co_id(String co_id) {
			this.co_id = co_id;
			return this;
		}
		
		
		
		
		public WallapiUrl uid(String uid) {
			this.uid = uid;
			return this;
		}
		
		public WallapiUrl isCustom() {
				custom = "&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=";
			return this;
		}
		
		public WallapiUrl isPrice(String price, String currency) {
			custom = "&are_flexible_call=on&data%5Bamount%5D="+price+"&data%5BcurrencyCode%5D="+currency+"&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=";
		return this;
	}
		
		public WallapiUrl isDark() {
				theme = "&data%5Bcustom%5D%5Btheme%5D=dark";
			return this;
		}
		
		public WallapiUrl isBrick16() {
				brick_1_6 = "&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
			return this;
		}
		
		
		public WallapiUrl isCustom(String key, String value) {
			customItem = "&data%5Bcustom%5D%5B"+key+"%5D="+value;
		return this;
	}
	
		
		public WallapiUrl isUidTimeline() {
			uid = Driver.timestamp();
		return this;
	}
		
		public String generate() {
			String h = this.host + "/admin/test-offerwall?";
			String a = prefix+"a_id"+suffix+this.a_id;
			String w = prefix + "widget" + suffix+ this.widget;
			String co = prefix + "co_id" + suffix + this.co_id;
			String u = prefix + "uid" + suffix+ this.uid;
			String url = h + a+ w+ co+ u+p+this.custom+this.brick_1_6+this.theme+this.version+this.customItem;
			return url;
		}
		
	}
}
