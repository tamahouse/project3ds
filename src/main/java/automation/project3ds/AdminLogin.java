package automation.project3ds;

public class AdminLogin extends BasePage{

	public AdminLogin(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}
	
	public void open(String d_id) {
		String url = this.branch + "/admin/developers/login?id="+d_id+"&amp;admin_login=true";
		driver.get(url);
	}
}
