import org.testng.annotations.Test;

import automation.project3ds.AdminLogin;
import automation.project3ds.BasePage;
import automation.project3ds.BaseTest;
import automation.project3ds.BusinessInfo;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.MIDAdd;
import automation.project3ds.MIDEdit;
import automation.project3ds.MIDManagement;
import automation.project3ds.MerchantAdd;
import automation.project3ds.MerchantArea;
import automation.project3ds.MerchantEdit;
import automation.project3ds.MerchantManagement;
import automation.project3ds.Navigator;
import automation.project3ds.ProjectManagement;
import automation.project3ds.ProjectSetting;

public class Add_Merchant_Test extends BaseTest{
	
	String branch = "http://feature-brick-test.wallapi.bamboo.stuffio.com";

	@Test
	public void addMerchant() throws Exception {
		driver = new Driver();
		Navigator navigator = new Navigator(driver, branch);
		Login loginPage = navigator.getLoginPage();
		loginPage.login();
		MerchantAdd merchantAdd = navigator.getMerchantAddPage();
		MerchantManagement merchantManagement = merchantAdd.add();
		String d_id = merchantManagement.getDIDFromUrl();
		MerchantEdit merchantEdit = navigator.getMerchantEdit(d_id);
		merchantEdit.setCompany();
		merchantEdit.clickSaveButton();
		MerchantArea merchantArea = navigator.getMerchantArea(d_id);
		BusinessInfo businessInfo = navigator.getBusinessInfo();
		businessInfo.fillProfile();
		ProjectManagement projectManagement = navigator.getProjectManagement();
		projectManagement.clickAddButton();
		String a_id = projectManagement.getFirstProjectID();
		ProjectSetting projectSetting = navigator.getProjectSetting(a_id);
		projectSetting.setStatusLive();
		projectSetting.setBlockPaymentNo();
		projectSetting.setBlockOfferNo();
		projectSetting.clickSaveButton();
		MIDAdd midAdd = navigator.getMIDAdd();
		MIDManagement midManagement = midAdd.onboardDUMMY(d_id, a_id);
		String mid_id = midManagement.getMIDIDFromUrl();
		MIDEdit midEdit = navigator.getMIDEdit(mid_id);
		midEdit.clickOnboardButton();
	}
}
