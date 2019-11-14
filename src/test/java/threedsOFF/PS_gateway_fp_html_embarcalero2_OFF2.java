package threedsOFF;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.BaseTest;
import automation.project3ds.CodeFeature;
import automation.project3ds.Driver;
import automation.project3ds.PS_gateway_html;

public class PS_gateway_fp_html_embarcalero2_OFF2  extends BaseTest{
	
	String shortcode = "gateway";
	String url = "http://feature-pwg-1139.wallapi.bamboo.stuffio.com";
	String co_id = "1";
	String a_id = "101696";
	String host = url +"/test-staging-brick/brick-fp.html";
	
	

	@BeforeClass
	public void setUp() throws Exception {
		this.driver = new Driver(browser);
		login(url+"/admin/test-offerwall");
		CodeFeature.setCodeFeature(driver,url, CodeFeature.CF_3DS_V2, false);
	}
	

	
	@Test
	public void v2() throws Exception {
		driver.get(host);
//		String cardNumber = "4012001037141112";
//		String cardNumber = "5200000000000007";
		String cardNumber = "5200000000001096";
//		String cardNumber = "5555555555554477";
		PS_gateway_html html = new PS_gateway_html(driver);
		html.setCardNumber(cardNumber);
		html.createPayment();
		html.waitForSuccessButton();
	}
	

}