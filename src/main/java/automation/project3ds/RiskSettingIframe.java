package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RiskSettingIframe extends BasePage{
	
	
	String BIG_PRICE_POINT_LIMIT = "400";
	String REASON = "test";

	By iframe1 = By.xpath("//*[@id='frame-container']/iframe[1]");
	By projectFrame = By.xpath("//a[contains(@href,'application')]");
	By searchTxb = By.id("applications_autocomplete");
	By searchBtn = By.xpath("//input[@value='Search']");
	By checkBox = By.xpath("(//input[@type='checkbox'])[position()>1]");
	By enableChx = By.name("settings[enable]");
	By bigPricePointChx = By.xpath("//input[@type='checkbox'][@name='enabledSettings[big_price_point]']");
	By bigPricePointLimitTxb = By.name("settings[big_price_point_limit]");
	By saveBtn = By.xpath("//input[@value='Save']");
	
	By reasonTxb = By.xpath("//textarea[@class='form-control reason']");
	By submitBtn = By.xpath("//button[contains(@class,'btnSubmit')]");
	
	public RiskSettingIframe(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}

	public void openProject(String a_id) {
		String url = this.branch + "/admin/ps-fraud-settings-old/application?search[a_id]="+a_id;
		driver.get(url);
	}
	
	public void clickProjectFrame() {
		Element element = driver.getElement(projectFrame);
		element.click();
	}
	
	private void setSearch(String condition) {
		Element element = driver.getElement(searchTxb);
		element.sendKeys(condition);
	}
	
	private void setProject(String a_id) {
		Element element = driver.getElement(By.xpath("//*[@id='applications_autocomplete_choices'][not(contains(@style,'none'))]/ul/li[@value='"+a_id+"']"));
		element.click();
	}
	
	private void clickSearchButton() {
		Element element = driver.getElement(searchBtn);
		element.click();
		driver.getElement(checkBox);
	}
	
	public void openProjectSetting(String a_id) {
		this.setSearch(a_id);
		this.setProject(a_id);
		this.clickSearchButton();
	}
	
	public void unLimitProject(String a_id) {
		this.enableRiskSetting(a_id);
		this.uncheckFilter(a_id);
	}
	
	private void uncheckFilter(String a_id) {
		this.openProject(a_id);
		this.uncheckAll();
		this.checkBigPricePoint();
		this.setBigPricePointLimit();
		this.clickSaveButton();
		this.setReason();
		this.clickSubmitButton();
	}
	
	private void enableRiskSetting(String a_id) {
		this.openProject(a_id);
		this.checkEnabled();
	}
	
	
	private void uncheckAll() {
		List<Element> elements = driver.getElements(checkBox);
		for(Element element: elements) {
			element.uncheck();
		}
	}
	
	private void checkEnabled() {
		Element element = driver.getElement(enableChx);
		Boolean isEnabled = element.isSelected();
		if(!isEnabled) {
			element.check();
			this.clickSaveButton();
			this.setReason();
			this.clickSubmitButton();
		}
	}
	
	private void checkBigPricePoint() {
		Element element = driver.getElement(bigPricePointChx);
		element.check();
	}
	
	private void setBigPricePointLimit() {
		this.setBigPricePointLimit(BIG_PRICE_POINT_LIMIT);
	}
	
	private void setBigPricePointLimit(String limit) {
		Element element = driver.getElement(bigPricePointLimitTxb);
		element.clear();
		element.sendKeys(limit);
	}
	
	private void clickSaveButton() {
		Element element = driver.getElement(saveBtn);
		element.click();
	}
	
	private void setReason() {
		Element element = driver.getElement(reasonTxb);
		element.sendKeys(REASON);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
}
