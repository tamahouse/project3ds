package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RiskSetting extends BasePage {
	

	public RiskSetting(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
		this.open();
	}

	public void open() {
		String url = this.branch + "/admin/ps-fraud-settings-old";
		driver.get(url);
	}
	
	
}
