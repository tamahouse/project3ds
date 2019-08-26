package automation.project3ds.oldTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.PageBrickHTML;
import automation.project3ds.Pslog;
import automation.project3ds.Z2;

public class BrickHTML_Polk_Test {

//	final String host1107 = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=CC+Embarcadero+test+%28JammyWall%29%5B101601%5D&data%5Ba_id%5D=101601&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069cze3&data%5Bag_type%5D=fixed";
//	final String host1108 = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bag_type%5D=fixed";
//	final String hostBrickHTML = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/brick/test-staging/brick.html";
	final String hostBrickURL = "http://feature-brick-test.wallapi.bamboo.stuffio.com/brick/test-staging/brick-polk.html";
	String host = hostBrickURL;
//	String hostReport = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/reports/payment-transaction";
	By nameTxb = By.id("login");
	By passwordTxb = By.id("password");
	By loginBtn = By.id("submit_button");
	String name = utility.ConfigFile.name;
	String password = utility.ConfigFile.password;
	Driver driver;
	Map<String, String> map;
	String filePath = "src\\main\\java\\utility\\cardNumber_v1.xlsx";


	private void killRemain() throws Exception {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
//				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void setUp() throws Exception {
//		map = new HashMap<String, String>();
//		map.put("PAN", "4012001037141112");
//	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}


	@DataProvider(name = "data")
	public Object[][] data() throws Exception {
//		this.setUp();
		List<Object[]> temp = new ArrayList<Object[]>();
//		String PAN = map.get("PAN");
		String PAN = "4012001037141112";
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
		PageBrickHTML.returnRefID(cardNumber);
		
		String t_id = Z2.get_t_id();
		
		String refID = Pslog.get_cl_id(t_id);
		System.out.println(cardNumber + " : " + refID + " : " + t_id);
		
		ExtentManager.logInfo("t_id: " + t_id);
//		ResponseLookup response = Z2.getLookupResponse(t_id);
//		String version = response.getVersion();
//		version = version.substring(0,1);
//		MySoftAssertAll assertion = new MySoftAssertAll();
//		assertion.assertEquals(version, "1");
//		assertion.assertAll();
	}
	
	
	

}
