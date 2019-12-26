package automation.project3ds;

public class Navigator {
	
	Driver driver;
	String branch;
	
	
	public Navigator (Driver driver, String branch) {
		this.driver = driver;
		this.branch = branch;
	}
	
	public Login getLoginPage() {
			return new Login(driver, branch);
	}
	
	public MerchantAdd getMerchantAddPage() {
			return new MerchantAdd(driver, branch);
	}
	
	public MerchantEdit getMerchantEdit(String d_id) {
		return new MerchantEdit(driver, branch, d_id);
}
	
	public AdminLogin getAdminLogin() {
			return new AdminLogin(driver, branch);
	}
	
	public BusinessInfo getBusinessInfo() {
		return new BusinessInfo(driver, branch);
	}
	
	public RiskSetting getRiskSetting() {
		return new RiskSetting(driver, branch);
	}

	public RiskSettingIframe getRiskSettingIframe() {
		return new RiskSettingIframe(driver, branch);
	}
	
	public PwMerchantAdd getPwMerchantAdd() {
		return new PwMerchantAdd(driver, branch);
	}
	
	public ProjectManagement getProjectManagement() {
		return new ProjectManagement(driver, branch);
	}
	
	public ProjectSetting getProjectSetting(String a_id) {
		ProjectSetting projectSetting = new ProjectSetting(driver, branch);
		projectSetting.open(a_id);
		return projectSetting;
	}
	
	public ProjectPingback getProjectPingback(String a_id) {
		return new ProjectPingback(driver, branch, a_id);
	}
	
	public DevriseQueueManagement getDevriseQueueManagement(String a_id) {
		return new DevriseQueueManagement(driver, branch, a_id);
	}
	
	public DevriseQueue getDevriseQueue(String qid) {
		return new DevriseQueue(driver, branch, qid);
	}
	
	public MerchantManagement getMerchantManagement(String a_id) {
		MerchantManagement merchantManagement = new MerchantManagement(driver, branch);
		merchantManagement.openAID(a_id);
		return merchantManagement;
	}
	
	public MerchantArea getMerchantArea(String d_id) {
		return new MerchantArea(driver, branch, d_id);
	}
	
	public MIDAdd getMIDAdd() {
		MIDAdd midAdd = new MIDAdd(driver, branch);
		midAdd.open();
		return midAdd;
	}
	
	public MIDEdit getMIDEdit(String mid_id) {
		MIDEdit midEdit = new MIDEdit(driver, branch);
		midEdit.open(mid_id);
		return midEdit;
	}
}
