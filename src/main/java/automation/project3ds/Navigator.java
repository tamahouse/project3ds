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
	
	public AdminLogin getAdminLogin() {
			return new AdminLogin(driver, branch);
	}
	
	public BusinessInfo getBusinessInfo() {
		return new BusinessInfo(driver, branch);
}

}
