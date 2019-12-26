package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MerchantAdd extends BasePage {
	
	String timestamp = timestamp();
	String NAME = "Name";
	String LASTNAME = "Lastname";
	String EMAIL = "meo"+timestamp + "@spam4.me";
	String PASSWORD = "000000";
	String COMPANY = timestamp;
	
	String addUrl;
	String d_id;
	
	By nameTxb = By.name("data[d_name]");
	By lastnameTxb = By.name("data[d_lastname]");
	By emailTxb = By.name("data[d_email]");
	By passwordTxb = By.name("data[d_password]");
	By companyTxb = By.name("data[d_company]");
	By saveBtn = By.xpath("//input[@value='Save']");

	public MerchantAdd(Driver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void open() {
		String url = this.branch + "/admin/developers/add";
		driver.get(url);
	}
	
	public void openEdit() {
		String url = this.branch + "/admin/developers/edit?id="+this.d_id;
		driver.get(url);
	}
	
	
	public MerchantAdd(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}


	public void setName() {
		Element element = driver.getElement(nameTxb);
		element.sendKeys(NAME);
	}
	
	private void setLastName() {
		Element element = driver.getElement(lastnameTxb);
		element.sendKeys(LASTNAME);
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		element.sendKeys(PASSWORD);
	}
	
	public void setCompany() {
		this.setCompany(COMPANY);
	}
	
	protected void setCompany(String company) {
		Element element = driver.getElement(companyTxb);
		element.clear();
		element.sendKeys(company);
	}
	
	public void clickSaveButton() {
		Element element = driver.getElement(saveBtn);
		element.click();
	}
	
	public MerchantManagement add() {
		this.open();
		this.setName();
		this.setLastName();
		this.setEmail();
		this.setPassword();
		this.setCompany();
		this.clickSaveButton();
		return new MerchantManagement (driver, branch);
	}
	

	
	

}
