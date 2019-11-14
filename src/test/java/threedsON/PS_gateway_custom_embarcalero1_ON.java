package threedsON;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.BaseTest_Z2;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.PS_gateway_custom;
import automation.project3ds.Pslog;

public class PS_gateway_custom_embarcalero1_ON  extends BaseTest_Z2 {
	
	String shortcode = "gateway";
//	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "76";
	String a_id = "101696";
	String host = url+"/test-staging-brick/brick-custom-new-widget.html";
	int ps_id = 132;
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(url+"/admin/test-offerwall");
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, true);
	}
	
	
	@Test
	public void v1() throws Exception {
		driver.get(host);
//		String cardNumber = "4012001037141112";
		String cardNumber = "5200000000000007";
//		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		PS_gateway_custom custom = new PS_gateway_custom(driver);
		custom.setCardNumber(cardNumber);
		custom.createPayment();
		String email = custom.getEmail();
		custom.finish3dsV1ON();
		String cl_id = Pslog.get_cl_id_email_Fasterpay(email);
		System.out.println(cl_id);
		Assertion.assertConverted(cl_id);
	}
	
	

}
