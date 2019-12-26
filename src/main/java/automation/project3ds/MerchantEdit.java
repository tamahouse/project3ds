package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MerchantEdit extends MerchantAdd {

	String d_id;
	
	public MerchantEdit(Driver driver, String branch, String d_id) {
		super(driver, branch);
		this.d_id = d_id;
		this.open();
	}
	
	public void open() {
		String url = this.branch + "/admin/developers/edit?id="+this.d_id;
		driver.get(url);
	}
	
	public String getDID() {
		return this.d_id;
	}
	
	public void setCompany() {
		this.setCompany(d_id);
	}
	
	

}
