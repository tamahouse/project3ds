package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PwMerchantAdd extends BasePage{
	
	String timestamp = timestamp();

	String NAME = "First Lastname";
	String PHONE = timestamp;
	String WEBSITE = "www.paymentwall.com";
	String EMAIL = "meo"+timestamp + "@spam4.me";
	String PASSWORD = "Ez654321";
	
	String COMPANY = "company";
	String TYPE ="3";
	String COUNTRY = "1";
	String ADDRESS = "43 Florida";
	String CITY = "Florida";
	String ZIP = "32043";
	
	By nameTxb = By.id("name");
	By phoneTxb = By.id("phone");
	By websiteTxb = By.id("website_url");
	By emailTxb = By.id("email");
	By passwordTxb = By.id("password");
	By confirmPasswordTxb = By.id("confirm_password");
	By policyChx = By.xpath("//*[./*[@id='agree-policy']]/label");
	By nextBtn = By.xpath("//button[@id='submit-form-button'][not(contains(@class,'disabled'))]");
	
	By businessNameTxb = By.id("company_name");
	By industryTxb = By.name("data[service_type]");
	By countryTxb = By.name("data[country]");
	By addressTxb = By.id("company_street");
	By cityTxb = By.id("company_city");
	By postTxb = By.id("company_zip");
	By createBtn = By.id("submit-form-button");
	
	public PwMerchantAdd(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}

	public void open() {
		String url = this.branch + "/pwaccount/";
		driver.get(url);
	}
	
	public void createAccount() {
		this.open();
		this.createMerchant();
		this.createBussinessAccount();
	}
	
	private void setName() {
		Element element = driver.getElement(nameTxb);
		element.sendKeys(NAME);
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.sendKeys(PHONE);
	}
	
	private void setWebsite() {
		Element element = driver.getElement(websiteTxb);
		element.sendKeys(WEBSITE);
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		element.sendKeys(PASSWORD);
	}
	
	private void setConfirmPassword() {
		Element element = driver.getElement(confirmPasswordTxb);
		element.sendKeys(PASSWORD);
	}
	
	private void clickPolicy() {
		Element element = driver.getElement(policyChx);
		element.check();
	}
	
	private void clickNextButton() {
		Element element = driver.getElement(nextBtn);
		element.click();
	}
	
	private void createMerchant() {
		this.setName();
		this.setPhone();
		this.setWebsite();
		this.setEmail();
		this.setPassword();
		this.setConfirmPassword();
		this.clickPolicy();
		this.clickNextButton();
	}
	
	private void setBusinessName() {
		Element element = driver.getElement(businessNameTxb);
		element.sendKeys(COMPANY);
	}
	
	private void setIndustry() {
		Select select = driver.getSelect(industryTxb);
		select.selectByValue(TYPE);
	}
	
	private void setCountry() {
		Select select = driver.getSelect(countryTxb);
		select.selectByValue(COUNTRY);
	}
	
	private void setAddress() {
		Element element = driver.getElement(addressTxb);
		element.sendKeys(ADDRESS);
	}
	
	private void setCity() {
		Element element = driver.getElement(cityTxb);
		element.sendKeys(CITY);
	}
	
	private void setPostCode() {
		Element element = driver.getElement(postTxb);
		element.sendKeys(ZIP);
	}
	
	private void clickCreateButton() {
		Element element = driver.getElement(createBtn);
		element.click();
		driver.getCurrentUrl("developers", true);
	}
	
	private void createBussinessAccount() {
		this.setBusinessName();
		this.setIndustry();
		this.setCountry();
		this.setAddress();
		this.setCity();
		this.setPostCode();
		this.clickCreateButton();
	}
	
	
}
