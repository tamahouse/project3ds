package automation.project3ds;

public class MerchantArea extends BasePage{

	public MerchantArea(Driver driver, String branch, String d_id) {
		super(driver, branch);
		this.open(d_id);
	}

	private void open(String d_id) {
		String url = this.branch + "/admin/developers/login?id="+d_id+"&admin_login=true";
		driver.get(url);
	}
}
