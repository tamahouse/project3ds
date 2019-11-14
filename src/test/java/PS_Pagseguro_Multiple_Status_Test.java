import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.project3ds.Action;
import automation.project3ds.AnnotationPage;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.ExtentManager;
import automation.project3ds.PS_Pagseguro;
import automation.project3ds.PS_Pagseguro2;
import automation.project3ds.PS_Pagseguro_API;
import automation.project3ds.Pslog;
import automation.project3ds.WidgetPage;

public class PS_Pagseguro_Multiple_Status_Test extends BaseTest{

	String a_id = "99894";
	String shortcode = "pagseguro";
	String co_id = "30";
	String url = "http://feature-pwl-1969.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(url).co_id(co_id).a_id(a_id).isCustom().generate();
	String filePath = "C:\\Workspace\\project3ds\\src\\main\\java\\utility\\ps_data.xlsx";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(host);
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
		
		WidgetPage widgetPage = new WidgetPage(driver);
		PS_Pagseguro ps = (PS_Pagseguro) widgetPage.getMultiWidget().createClick(shortcode);
		PS_Pagseguro2 ps2 = ps.createPayment();
		String transactionCode = ps2.createCreditCardPayment();
		for(int i=0; i< steps.size(); i++) {
			String step = steps.get(i);
			PS_Pagseguro_API.setStatus(transactionCode, step);
			if(i != steps.size()-1) {
			AnnotationPage.sleep(60000);
			}
		}
		String email = ps.getEmail();
		String print = email + " "+ Pslog.get_cl_id_email_Fasterpay(email) + " " + transactionCode + " "+ status;
		System.out.println(print);
		ExtentManager.logInfo(print);
	}
}
