import org.testng.annotations.Test;

import automation.project3ds.AdminLogin;
import automation.project3ds.BaseTest;
import automation.project3ds.BusinessInfo;
import automation.project3ds.DevriseQueue;
import automation.project3ds.DevriseQueueManagement;
import automation.project3ds.Driver;
import automation.project3ds.Login;
import automation.project3ds.MerchantAdd;
import automation.project3ds.MerchantArea;
import automation.project3ds.MerchantEdit;
import automation.project3ds.MerchantManagement;
import automation.project3ds.MerchantManagement.Merchant;
import automation.project3ds.Navigator;
import automation.project3ds.ProjectManagement;
import automation.project3ds.ProjectManagement.Project;
import automation.project3ds.ProjectPingback;
import automation.project3ds.ProjectSetting;
import automation.project3ds.PwMerchantAdd;

public class Add_Merchant_PW_Test extends BaseTest{
	
	String branch = "http://feature-brick-test.wallapi.bamboo.stuffio.com";

	@Test
	public void addMerchant() {
		driver = new Driver();
		Navigator navigator = new Navigator(driver, branch);
		PwMerchantAdd merchantAdd = navigator.getPwMerchantAdd();
		merchantAdd.createAccount();
		BusinessInfo businessInfo = navigator.getBusinessInfo();
		businessInfo.fillProfile();
		ProjectManagement projectManagement = navigator.getProjectManagement();
		String a_id = projectManagement.getFirstProjectID();
		ProjectSetting projectSetting = navigator.getProjectSetting(a_id);
		projectSetting.setName();
		projectSetting.setPingbackEmail();
		projectSetting.clickSaveButton();
		ProjectPingback projectPingback = navigator.getProjectPingback(a_id);
		projectPingback.pingbackEmailSuccess();
		projectManagement = navigator.getProjectManagement();
		Project project = projectManagement.getProject(a_id);
		project.submitQueue();
		driver.quit();
		driver = new Driver();
		Navigator navigator2 = new Navigator(driver, branch);
		Login login = navigator2.getLoginPage();
		login.login();
		DevriseQueueManagement queueManagement = navigator2.getDevriseQueueManagement(a_id);
		String qid = queueManagement.getFirstQueueID();
		DevriseQueue queue = navigator2.getDevriseQueue(qid);
		queue.setCategory();
		queue.setModel();
		queue.setRisk();
		queue.clickSaveButton();
		MerchantManagement merchantManagement = navigator2.getMerchantManagement(a_id);
		Merchant merchant = merchantManagement.getFirstMerchant();
		String d_id = merchant.getID();
		MerchantEdit merchantEdit = navigator2.getMerchantEdit(d_id);
		merchantEdit.setCompany();
		merchantEdit.clickSaveButton();
//		MerchantArea merchantArea = navigator2.getMerchantArea(d_id);
//		ProjectSetting projectSetting2 = navigator2.getProjectSetting(a_id);
//		projectSetting2.setStatusLive();
//		projectSetting2.setBlockPaymentNo();
//		projectSetting2.setBlockOfferNo();
//		projectSetting2.clickSaveButton();
	}
}
