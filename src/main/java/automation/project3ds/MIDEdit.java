package automation.project3ds;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

public class MIDEdit extends MIDAdd{
	
	String mid_id;
	
	By onboardBtn = By.id("gateway_onboard");

	public MIDEdit(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}
	
	public void open(String mid_id) {
		String url = this.branch + "/admin/developers-mid-accounts/admin-edit?dma_id="+mid_id;
		this.mid_id = mid_id;
		driver.get(url);
	}

	public void clickOnboardButton() throws Exception {
		Element element = driver.getElement(onboardBtn);
		element.click();
		Alert alert = driver.switchToAlern();
		alert.accept();
		driver.getCurrentUrl("search", true);
	}
}
