package automation.project3ds;

import org.openqa.selenium.By;

public class PS_Sofortbt {
	
	Driver driver;
	
	String redirectUrl = "sofort.com/payment/multipay/go/select_country";
	String bankCode = "Demo";
	String userID_kontonummer = "88888888";
	String pin = "0000";
	String tan = "12345";
	
	By bankcodeSearch = By.id("BankCodeSearch");
	By bankItem = By.xpath("//*[@data-url='/payment/wizard/trackClick/bank_search_item_selected/DE/de/Demo Bank']");
	By kontonummer = By.id("BackendFormLOGINNAMEUSERID");
	By pinTxb = By.id("BackendFormUSERPIN");
	By weiterButton = By.xpath("//button[text()='Weiter']");
	By girokontoCheckbox = By.id("account-1");
	By tanTxb = By.id("BackendFormTan");
	By complete = By.xpath("//*[contains(@class,'thanks')]");

	public PS_Sofortbt(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage createPayment() {
		this.setBank();
		this.login();
		this.submitAccount();
		this.submitTan();
		return new ThankyouPage(driver);
	}
	
	private void setBank() {
		this.setBankCode();
		this.clickBankItem();
	}
	
	private void login() {
		this.setUserID_Kontonummer();
		this.setPin();
		this.clickSubmit();
	}
	
	private void submitAccount() {
		this.clickGirokonto_Account();
		this.clickSubmit();
	}
	
	private void submitTan() {
		this.setTan();
		this.clickSubmit();
	}
	
	private void setBankCode() {
		Element element = driver.getElement(bankcodeSearch,60000);
		element.sendKeys(bankCode);
	}
	
	private void clickBankItem() {
		Element element = driver.getElement(bankItem);
		element.click();
	}
	
	private void setUserID_Kontonummer() {
		Element element = driver.getElement(kontonummer);
		element.sendKeys(userID_kontonummer);
	}
	
	private void setPin() {
		Element element = driver.getElement(pinTxb);
		element.sendKeys(pin);
	}
	
	private void clickSubmit() {
		Element element = driver.getElement(weiterButton);
		element.click();
	}
	
	private void clickGirokonto_Account() {
		Element element = driver.getElement(girokontoCheckbox);
		element.click();
	}
	
	private void setTan() {
		Element element = driver.getElement(tanTxb);
		element.sendKeys(tan);
	}
	
	private void waitForProcessing() {
		driver.getElement(complete);
	}

}
