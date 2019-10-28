package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_vtc {
	
	Driver driver;
	
	String cardNumber = "4111111111111111";
	String cardHolder = "Payment Wall";
	String cardMonth = "01";
	String cardYear = "2022";
	String cardCvv = "123";
	String email;
	String address = "43 Flo";
	String addressCity = "Hanoi";
	
	String account = "0357758300";
	String password = "Abcd1234";
	
	String redirectUrl = "alpha1.vtcpay.vn";
	
	By visaButton = By.xpath("//*[@title='Visa']");
	By cardNumberTxb = By.id("txtCardNumber");
	By cardHolderTxb = By.id("txtCardHolder");
	By cardMonthSelect = By.id("ddlMonth");
	By cardYearSelect = By.id("ddlYear");
	By cardCvvTxb = By.id("txtCardCVN");
	By emailTxb = By.id("txtEmail");
	By addressTxb = By.id("txtAddress");
	By addressCityTxb = By.id("txtProvincialCity");
	By submitButton = By.id("lbtConfirmPayment");
	By confirmButton = By.id("btnPopupConfirm");
	
	By vtcPayButton = By.xpath("//a[contains(@href,'portalgateway')]");
	By vtcPayAccount = By.id("txtAccountNumber");
	By vtcPayPassword = By.id("txtPassword");
	By vtcPayLoginButton = By.id("btnLogin");
	By vtcPayPayButton = By.xpath("//*[@class='link_all_thanhtoan_ud']");
	By okButton = By.id("btnSuccess");


	public PS_vtc(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
		AnnotationPage.sleep(1000);
	}
	
	public ThankyouPage createCreditCardPayment() {
		this.clickVisa();
		this.setCardNumber();
		this.setCardHolder();
		this.setCardMonth();
		this.setCardYear();
		this.setCardCvv();
		this.setEmail();
		this.setAddress();
		this.setAddressCity();
		this.clickSubmitButton();
		this.clickConfirmButton();
		return new ThankyouPage(driver);
	}
	
	public ThankyouPage createEwalletPayment() {
		this.clickVtcPayButton();
		this.setVtcAccount();
		this.setVtcPassword();
		this.clickLoginButton();
		this.clickPayButton();
		this.clickOkButton();
		return new ThankyouPage(driver);
	}
	
	private void clickVisa() {
		Element element = driver.getElement(visaButton);
		element.click();
	}
	
	private void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private void setCardHolder() {
		Element element = driver.getElement(cardHolderTxb);
		element.sendKeys(cardHolder);
	}
	
	private void setCardMonth() {
		Select select = new Select(driver.getElement(cardMonthSelect));
		select.selectByValue(cardMonth);
	}
	
	private void setCardYear() {
		Select select = new Select(driver.getElement(cardYearSelect));
		select.selectByValue(cardYear);
	}
	
	private void setCardCvv() {
		Element element = driver.getElement(cardCvvTxb);
		element.sendKeys(cardCvv);
	}
	
	private void setEmail() {
		String time = AnnotationPage.timestamp();
		email = "meo"+time+"@spam4.me";
		Element element = driver.getElement(emailTxb);
		element.sendKeys(email);
	}
	
	private void setAddress() {
		Element element = driver.getElement(addressTxb);
		element.sendKeys(address);
	}
	
	private void setAddressCity() {
		Element element = driver.getElement(addressCityTxb);
		element.sendKeys(addressCity);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitButton);
		element.click();
	}
	
	private void clickConfirmButton() {
		Element element = driver.getElement(confirmButton);
		element.click();
	}
	
	private void clickVtcPayButton() {
		Element element = driver.getElement(vtcPayButton);
		element.click();
	}
	
	private void setVtcAccount() {
		Element element = driver.getElement(vtcPayAccount);
		element.sendKeys(account);
	}
	
	private void setVtcPassword() {
		Element element = driver.getElement(vtcPayPassword);
		element.sendKeys(password);
	}

	
	private void clickLoginButton() {
		Element element = driver.getElement(vtcPayLoginButton);
		element.click();
		AnnotationPage.sleep(1000);
	}
	
	private void clickPayButton() {
		Element element = driver.getElement(vtcPayPayButton);
		element.click();
	}

	private void clickOkButton() {
		Element element = driver.getElement(okButton);
		element.click();
	}
}
