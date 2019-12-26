package automation.project3ds;

import org.openqa.selenium.By;

public class ProjectPingback extends BasePage {
	
	String a_id;
	By sendBtn = By.xpath("//input[@value='Send Pingback']");
	By successTxt = By.className("success");
	
	public ProjectPingback(Driver driver, String branch, String a_id) {
		super(driver, branch);
		this.open(a_id);
	}
	
	public void open(String a_id) {
		String url = this.branch + "/developers/pingback/test?id="+a_id;
		driver.get(url);
		this.a_id = a_id;
	}
	
	public void pingbackEmailSuccess() {
		this.clickSendPingback();
		this.waitForSuccess();
	}
	
	private void clickSendPingback() {
		Element element = driver.getElement(sendBtn);
		element.click();
	}
	
	private void waitForSuccess() {
		driver.getElement(successTxt);
	}

}
