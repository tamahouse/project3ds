package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DevriseQueue extends BasePage {

	String CATEGORY = "105";
	String MODEL_CHECK = "2";
	String RISK = "1";
	String TEXT = "any";


	By categorySlt = By.name("data[ac_ids][]");
	By modelSlt = By.name("data[a_compliance_status]");
	By modelSuccess = By.xpath("//*[./*[contains(text(),'Business Model Check:')]]/button[contains(@class,'success')]");
	By riskSlt = By.name("data[a_compliance_risk_level]");
	By riskSuccess = By.xpath("//*[./*[contains(text(),'Business Model Risk Level:')]]/button[contains(@class,'success')]");
	
	By noteIframe = By.xpath("//*[@class='popup-wrapper'][not(contains(@style,'none'))]//iframe");
	By noteTxtArea = By.id("tinymce");
	By noteSubmitBtn = By.xpath("//*[@class='popup-wrapper'][not(contains(@style,'none'))]//input[@value='Submit']");
	
	By saveBtn = By.id("save_submit_button");

	public DevriseQueue(Driver driver, String branch, String qid) {
		super(driver, branch);
		this.open(qid);
	}

	private void open(String qid) {
		String url = this.branch + "/admin/devrise/queue-messages?id=" + qid;
		driver.get(url);
	}
	
	public void setCategory() {
		Select select = driver.getSelect(categorySlt);
		String selectedValue = select.getFirstSelectedOption().getAttribute("value");
		if(!selectedValue.equals(CATEGORY)) {
			select.selectByValue(CATEGORY);
		}
		
	}
	
	private void setModelOnly() {
		Select select = driver.getSelect(modelSlt);
		select.selectByValue(MODEL_CHECK);
	}
	
	public void setModel() {
		Select select = driver.getSelect(modelSlt);
		String selectedValue = select.getFirstSelectedOption().getAttribute("value");
		if(!selectedValue.equals(MODEL_CHECK)) {
			this.setModelOnly();
			this.setNoteText();
			this.clickNoteSubmitButton();
			this.waitForModelSuccess();
		}
	}

	private void setNoteText() {
		WebElement webElement = driver.getElement(noteIframe).getWebElement();
		driver.switchTo().frame(webElement);
		Element element = driver.getElement(noteTxtArea);
		element.sendKeys(TEXT);
		driver.switchTo().defaultContent();
	}
	
	private void setRiskOnly() {
		Select select = driver.getSelect(riskSlt);
		select.selectByValue(RISK);
		
	}
	
	public void setRisk() {
		Select select = driver.getSelect(riskSlt);
		String selectedValue = select.getFirstSelectedOption().getAttribute("value");
		if(!selectedValue.equals(RISK)) {
		this.setRiskOnly();
		this.setNoteText();
		this.clickNoteSubmitButton();
		this.waitForRiskSuccess();
		}
	}
	
	private void clickNoteSubmitButton() {
		Element element = driver.getElement(noteSubmitBtn);
		element.click();
	}
	
	private void waitForModelSuccess() {
		driver.getElement(modelSuccess);
	}
	
	private void waitForRiskSuccess() {
		driver.getElement(riskSuccess);
	}
	
	public void clickSaveButton() {
		Element element = driver.getElement(saveBtn);
		element.click();
	}
	


}
