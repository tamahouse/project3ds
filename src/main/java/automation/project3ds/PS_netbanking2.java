package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

public class PS_netbanking2 {
	
	Driver driver;
	
	String timestamp = AnnotationPage.timestamp();
	String NAME = "Payment wall";
	String ADDRESS1 = "43 Address";
	String ADDRESS2 = "Address 2";
	String CITY = "Florida";
	String STATE = "Florida";
	String ZIP = "32043";
	String COUNTRY = "224";
	String PHONE = "994283640";
	String EMAIL = "meo"+timestamp+"@spam4.me";
	String CUSTOMER_ID = "test";
	String CUSTOMER_PASSWORD = "test";
	
	String email = EMAIL;
	
	
	String redirectUrl = "ipg-online.com";
	By nameTxb = By.id("bname");
	By addressTxb = By.id("baddr1");
	By cityTxb = By.id("bcity");
	By stateTxb = By.id("bstate");
	By zipTxb = By.id("bzip");
	By countrySlt = By.id("bcountry");
	By phoneTxb = By.id("phone");
	By emailTxb = By.id("email");
	By continueBtn = By.id("next");
	
	By nameTxb2 = By.id("snmea");
	By address1Txb = By.id("saddr1");
	By address2Txb = By.id("saddr2");
	By cityTxb2 = By.id("scity");
	By stateTxb2 = By.id("sstate");
	By zipTxb2 = By.id("szip");
	By countrySlt2= By.id("scountry");
	By emailTxb2 = By.id("semail");
	
	By bankRadio = By.xpath("//*[./label[text()='Test Bank']]/input");
	
	By customerID = By.id("textfield2");
	By customerPassword = By.id("textfield3");
	By submitBtn = By.id("button");
	
	By submitBtn2 = By.xpath("//*[@id='TDContent'][//*[@id='textfield2'][@value!='']]//*[@id='button']");
	
	
	public PS_netbanking2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage createPayment() {
		this.fillBillingInformation();
		this.fillShippingInformation();
		this.setBank();
		this.login();
		this.clickSubmitButton2();
		return new ThankyouPage(driver);
	}
	
	public String getEmail() {
		return this.email;
	}
	
	private void setName() {
		Element element = driver.getElement(nameTxb);
		element.sendKeys(NAME);
	}
	
	private void setAddress() {
		Element element = driver.getElement(addressTxb);
		element.sendKeys(ADDRESS1);
	}
	
	private void setCity() {
		Element element = driver.getElement(cityTxb);
		element.sendKeys(CITY);
	}
	
	private void setState() {
		Element element = driver.getElement(stateTxb);
		element.sendKeys(STATE);
	}
	
	private void setZip() {
		Element element = driver.getElement(zipTxb);
		element.sendKeys(ZIP);
	}
	
	private void setCountry() {
		Select select = new Select(driver.getElement(countrySlt));
		select.selectByValue(COUNTRY);
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.sendKeys(PHONE);
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	private void fillBillingInformation() {
		this.setName();
		this.setAddress();
		this.setCity();
		this.setState();
		this.setZip();
		this.setCountry();
		this.setPhone();
		this.setEmail();
		this.clickContinueButton();
	}
	
	private void setName2() {
		Element element = driver.getElement(nameTxb2);
		element.sendKeys(NAME);
	}
	
	private void setAddress2() {
		Element element = driver.getElement(address1Txb);
		element.sendKeys(ADDRESS1);
	}
	
	private void setAddress22() {
		Element element = driver.getElement(address2Txb);
		element.sendKeys(ADDRESS2);
	}
	
	private void setCity2() {
		Element element = driver.getElement(cityTxb2);
		element.sendKeys(CITY);
	}
	
	private void setState2() {
		Element element = driver.getElement(stateTxb2);
		element.sendKeys(STATE);
	}
	
	private void setZip2() {
		Element element = driver.getElement(zipTxb2);
		element.sendKeys(ZIP);
	}
	
	private void setCountry2() {
		Select select = new Select(driver.getElement(countrySlt2));
		select.selectByValue(COUNTRY);
	}
	
	
	private void setEmail2() {
		Element element = driver.getElement(emailTxb2);
		element.sendKeys(EMAIL);
	}
	

	private void fillShippingInformation() {
		this.setName2();
		this.setAddress2();
		this.setAddress22();
		this.setCity2();
		this.setState2();
		this.setZip2();
		this.setCountry2();
		this.setEmail2();
		this.clickContinueButton();
	}
	
	private void clickTestBankRadio() {
		Element element = driver.getElement(bankRadio);
		element.click();
	}
	
	private void setBank() {
		this.clickTestBankRadio();
		this.clickContinueButton();
	}
	
	private void setCustomerID() {
		Element element = driver.getElement(customerID);
		element.sendKeys(CUSTOMER_ID);
	}
	
	private void setCustomerPassword() {
		Element element = driver.getElement(customerPassword);
		element.sendKeys(CUSTOMER_PASSWORD);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
	private void login() {
		this.setCustomerID();
		this.setCustomerPassword();
		this.clickSubmitButton();
	}
	
	private void clickSubmitButton2() {
		Element element = driver.getElement(submitBtn2);
		element.click();
	}


}
