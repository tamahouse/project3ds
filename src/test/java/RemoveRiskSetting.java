import org.testng.annotations.Test;

import automation.project3ds.AdminLogin;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.BusinessInfo;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.MerchantAdd;
import automation.project3ds.Navigator;
import automation.project3ds.RiskSetting;
import automation.project3ds.RiskSettingIframe;

public class RemoveRiskSetting extends BaseTest{
	
	String branch = "http://feature-brick-test.wallapi.bamboo.stuffio.com";
	String a_id = "99894";

	@Test
	public void riskRemove() {
		driver = new Driver();
		Navigator navigator = new Navigator(driver, branch);
		Login loginPage = navigator.getLoginPage();
		loginPage.login();
		RiskSettingIframe riskIframe = navigator.getRiskSettingIframe();
		riskIframe.unLimitProject(a_id);
	}
}
