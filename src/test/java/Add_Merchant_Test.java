import org.testng.annotations.Test;

import automation.project3ds.AdminLogin;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.BusinessInfo;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.MerchantAdd;
import automation.project3ds.Navigator;

public class Add_Merchant_Test extends BaseTest{
	
	String branch = "http://feature-brick-test.wallapi.bamboo.stuffio.com";

	@Test
	public void addMerchant() {
		driver = new Driver();
		Navigator navigator = new Navigator(driver, branch);
		Login loginPage = navigator.getLoginPage();
		loginPage.login();
		MerchantAdd merchantAdd = navigator.getMerchantAddPage();
//		String d_id = merchantAdd.add();
		String d_id = "88014";
		AdminLogin adminLogin = navigator.getAdminLogin();
		adminLogin.open(d_id);
		BusinessInfo businessInfo = navigator.getBusinessInfo();
		businessInfo.fillProfile();
		System.out.println();
	}
}
