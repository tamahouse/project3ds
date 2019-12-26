package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ProjectSetting extends BasePage {
	
	String PINGBACKEMAIL = "meo@spam4.me";
	String LIVE = "1";
	String BLOCK_NO = "0";
	String a_id;

	By pingbackTxb = By.id("a_pingback_url");
	By saveBtn = By.xpath("//*[@class='buttons_container']/button[@class='save_btn']");
	By projectName = By.name("data[a_name]");
	By pingbackEmailRadio = By.xpath("//*[@name='data[a_pingback_email]'][@value='1']");
	By statusSlt = By.id("a_live");
	By blockPaymentSlt = By.id("a_block_payments");
	By blockOfferSlt = By.id("a_block_offers");
	
	public ProjectSetting(Driver driver, String branch) {
		super(driver, branch);
	}
	
	public void open(String a_id) {
		String url = this.branch + "/developers/applications/edit/?id="+a_id;
		driver.get(url);
		this.a_id = a_id;
	}
	
	public void setName() {
		this.setName("project "+ a_id);
	}
	
	private void setName(String name) {
		Element element = driver.getElement(projectName);
		element.clear();
		element.sendKeys(name);
	}
	
	public void setPingbackEmail() {
		Element type = driver.getElement(pingbackEmailRadio);
		type.check();
		this.setPingbackText();
	}
	
	private void setPingbackText() {
		Element element = driver.getElement(pingbackTxb);
		element.sendKeys(PINGBACKEMAIL);
	}
	
	
	public void setStatusLive(){
		this.setStatus(LIVE);
	}
	
	private void setStatus(String value) {
		Select select = driver.getSelect(statusSlt);
		select.selectByValue(value);
	}

	public void setBlockPaymentNo() {
		Select select = driver.getSelect(blockPaymentSlt);
		select.selectByValue(BLOCK_NO);
	}
	
	public void setBlockOfferNo() {
		Select select = driver.getSelect(blockOfferSlt);
		select.selectByValue(BLOCK_NO);
	}
	
	public void clickSaveButton() {
		Element element = driver.getElement(saveBtn);
		element.click();
	}
}
