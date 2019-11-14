package threedsON;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.Assertion;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.PS_gateway_html;
import automation.project3ds.Pslog;

public class PS_gateway_fp_html_polk_ON2  extends BaseTest_Z2 {
	
	String shortcode = "gateway";
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101696";
	String host = url+"/test-staging-brick/brick-fp.html";
	int ps_id = 132;
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(url+"/admin/test-offerwall");
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	
	
	@Test
	public void polk() throws Exception {
		driver.get(host);
		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		PS_gateway_html html = new PS_gateway_html(driver);
		html.setCardNumber(cardNumber);
		html.createPayment();
		html.waitForError();
		String email = html.getEmail();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		Assertion.get().assertEquals(BasePage.isNumeric(cl_id), true,"[cl_id available]");
		Assertion.end();
	}
	
	

}
