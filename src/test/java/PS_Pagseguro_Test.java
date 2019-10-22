import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMulti;

public class PS_Pagseguro_Test {

	String a_id = "99894";
	String shortcode = "pagseguro";
	String co_id = "30";
	String url = "http://feature-pwl-1969.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).isCustom().generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	
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
	
	public void openPS() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMulti.clickPaymentMethod(shortcode);
		WidgetMulti.clickBuyButton();
	}
	
	@DataProvider(name="data")
	public Object[][] data() throws Exception{
		List<Map<String, String>> dataTable = Action.getTestData(filePath,shortcode);
		List<Object[]> list = new ArrayList<Object[]>();
		for(Map<String, String> map: dataTable) {
			String status = map.get("status");
			String code = map.get("code");
			String process = map.get("process");
			String enable = map.get("enable");
			if(enable.equals("1")) {
			Object[] obj = new Object[] {status,code, process};
			list.add(obj);
			}
		}
		Object[][] data = list.toArray(new Object[][] {});
		return data;
	}
	
	@Test(dataProvider = "data")
	public void execute(String status, String code, String process) throws Exception {
		List<String> steps;
		try {
		steps = new ArrayList<String>(Arrays.asList(process.split(",")));
		}catch(NullPointerException e) {
			steps = new ArrayList<String>();
			if(!process.equals("")) {
				steps.add(process);
			}
		}
		this.openPS();
		PS_Pagseguro.createPayment();
		String transactionCode = PS_Pagseguro2.createCreditCardPayment();
		for(int i=0; i< steps.size(); i++) {
			String step = steps.get(i);
			PS_Pagseguro_API.setStatus(transactionCode, step);
			if(i != steps.size()-1) {
			AnnotationPage.sleep(60000);
			}
		}
		String email = PS_Pagseguro.email;
		String print = email + " "+ Pslog.get_cl_id_email_Fasterpay(email) + " " + transactionCode + " "+ status;
		System.out.println(print);
		ExtentManager.logInfo(print);
	}
}
