package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class MIDAdd extends BasePage{
	
	String PS = "132";
	String VOLUME = "123456";
	String CC_EMBARCADERO = "CC_EMBARCADERO";
	String CC_POLK = "CC_POLK";
	String CC_EDDY = "CC_EDDY";
	String CC_DUMMY = "CC_DUMMY";
	String MERCHANT_ID = "63103550";
	String MCC_CODE = "927";
	
	String partner;
	
	By merchantTxb = By.xpath("//*[@id='companies_autocomplete'][not(contains(@style,'none'))]");
	By entities = By.xpath("//*[@id='entities_select'][not(contains(@style,'none'))]");
	By psSlt = By.xpath("//*[@id='ps_select'][not(contains(@style,'none'))]");
	By projectSlt = By.xpath("//*[@id='applications_select'][not(contains(@style,'none'))]");
	By descriptorTxb = By.xpath("//*[@id='descriptor'][not(contains(@style,'none'))]");
	By partnerSlt = By.xpath("//*[@id='pss_id'][not(contains(@style,'none'))]");
	By externalVolume = By.xpath("//*[@id='expected_annual_volume'][not(contains(@style,'none'))]");
	By merchantIDTxb = By.xpath("//*[@name='search[credentials][mid]'][not(contains(@style,'none'))]");
	By mccCodeSlt = By.xpath("//*[@id='mcc_code'][not(contains(@style,'none'))]");
	By saveBtn = By.id("save");

	public MIDAdd(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}
	
	public void open() {
		String url = this.branch + "/admin/developers-mid-accounts/create";
		driver.get(url);
	}
	
	public MIDManagement onboardEmbarcadero(String d_id, String a_id) {
		this.partner = CC_EMBARCADERO;
		this.setMerchant(d_id);
		this.setEntities();
		this.setProject(a_id);
		this.setPaymentSystem();
		this.setDescriptor();
		this.setPartner();
		this.setExpectedAnnualVolume();
		this.setMerchantID();
		this.waitMCCCode();
		this.clickSaveButton();
		return new MIDManagement(driver, branch);
	}
	
	public MIDManagement onboardPolk(String d_id, String a_id) {
		this.partner = CC_POLK;
		this.setMerchant(d_id);
		this.setEntities();
		this.setProject(a_id);
		this.setPaymentSystem();
		this.setDescriptor();
		this.setPartner();
		this.setMCCCode();
		this.setExpectedAnnualVolume();
		this.clickSaveButton();
		return new MIDManagement(driver, branch);
	}
	
	public MIDManagement onboardEddy(String d_id, String a_id) {
		this.partner = CC_EDDY;
		this.setMerchant(d_id);
		this.setEntities();
		this.setProject(a_id);
		this.setPaymentSystem();
		this.setDescriptor();
		this.setPartner();
		this.setExpectedAnnualVolume();
		this.waitMCCCode();
		this.clickSaveButton();
		return new MIDManagement(driver, branch);
	}
	
	public MIDManagement onboardDUMMY(String d_id, String a_id) {
		this.partner = CC_DUMMY;
		this.setMerchant(d_id);
		this.setEntities();
		this.setProject(a_id);
		this.setPaymentSystem();
		this.setDescriptor();
		this.setPartner();
		this.setExpectedAnnualVolume();
		this.waitMCCCode();
		this.clickSaveButton();
		return new MIDManagement(driver, branch);
	}
	
	private void setMerchant(String d_id) {
		Element txb = driver.getElement(merchantTxb);
		txb.sendKeys(d_id);
		Element choice = driver.getElement(By.xpath("//*[@id='companies_autocomplete_choices'][not(contains(@style,'none'))]/ul/li[@value='"+d_id+"']"));
		choice.click();
	}
	
	private void setEntities() {
		Select select = driver.getSelect(entities);
		int size = select.getOptions().size();
		while(size<2) {
			driver.sleep(500);
			select = driver.getSelect(entities);
			size = select.getOptions().size();
			System.out.println(size);
		}
		select.selectByIndex(1);
	}
	
	private void setProject(String a_id) {
		Select select = driver.getSelect(projectSlt);
		select.selectByValue(a_id);
	}
	
	private void setPaymentSystem() {
		Select select = driver.getSelect(psSlt);
		select.selectByValue(PS);
	}
	
	private void setDescriptor() {
		String key = partner.toLowerCase();
		Element element = driver.getElement(descriptorTxb);
		element.sendKeys(key+"_bamboo");
	}
	
	private void setPartner() {
		Select select = driver.getSelect(partnerSlt);
		select.selectByVisibleText(partner);
	}
	
	private void setExpectedAnnualVolume() {
		Element element = driver.getElement(externalVolume);
		element.sendKeys(VOLUME);
	}
	
	private void setMerchantID() {
		Element element = driver.getElement(merchantIDTxb);
		element.sendKeys(MERCHANT_ID);
	}
	
	private void setMCCCode() {
		Select select = driver.getSelect(mccCodeSlt);
		select.selectByValue(MCC_CODE);
	}
	
	private void waitMCCCode() {
		driver.getElement(mccCodeSlt);
		driver.sleep(1000);
	}
	
	private void clickSaveButton() {
		Element element = driver.getElement(saveBtn);
		element.click();
	}
	

	

}
