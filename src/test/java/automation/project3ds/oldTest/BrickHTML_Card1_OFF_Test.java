package automation.project3ds.oldTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.FasterPay;
import automation.project3ds.PageBrickHTML;
import automation.project3ds.Pslog;
import automation.project3ds.Z2;

public class BrickHTML_Card1_OFF_Test {

//	final String host1107 = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=CC+Embarcadero+test+%28JammyWall%29%5B101601%5D&data%5Ba_id%5D=101601&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069cze3&data%5Bag_type%5D=fixed";
//	final String host1108 = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bag_type%5D=fixed";
//	final String host = "http://feature-brick-test.wallapi.bamboo.stuffio.com/brick/test-staging/brick.html";
//	final String hostNoSongbird = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/brick/test-staging/brick-polk.html";
//	String hostReport = "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/reports/payment-transaction";
	String filePath = "src\\main\\java\\utility\\cardNumber_v1.xlsx";
	By nameTxb = By.id("login");
	By passwordTxb = By.id("password");
	By loginBtn = By.id("submit_button");
	String name = utility.ConfigFile.name;
	String password = utility.ConfigFile.password;
	Driver driver;
	List<Map<String, String>> mapList;
	
//	* Host for test brick to FP
	String host = "http://feature-ccg-827.wallapi.bamboo.stuffio.com/test-staging-brick/brick.html";

	@SuppressWarnings("unused")
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

	private void beforeClass() throws Exception {
		mapList = Action.getTestData(filePath);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "data")
	public Object[][] data() throws Exception {
		this.beforeClass();
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
		PageBrickHTML.returnRefID(cardNumber);
		String email = PageBrickHTML.email;

//		String t_id = Z2.get_t_id();
		
		String clickId = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(clickId);
		ExtentManager.logInfo("cl_id : " + clickId);
		String f_id = Pslog.get_Fasterpay_payment_order_id(clickId);
		System.out.println(f_id);
		ExtentManager.logInfo("f_id : " + f_id);
		String t_id = FasterPay.getTID_Fasterpay(f_id);
		System.out.println(cardNumber + " : " + clickId + " : " + f_id + " : " + t_id);
		ExtentManager.logInfo(cardNumber + " : " + clickId + " : " + f_id + " : " + t_id);
		Action.assertResultV1(t_id, map);
	}

}
