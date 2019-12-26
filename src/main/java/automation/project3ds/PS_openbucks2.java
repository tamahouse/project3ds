package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

public class PS_openbucks2 {
	
	Driver driver;
	String EMAIL = "meo@spam4.me";
	String PASSWORD = "Ez654321";
	
	String redirectUrl = "openbucks.com";
	
	By menuLoginBtn = By.id("menuLogin");
	
	By toLoginBtn = By.xpath("//*[@id='registerToLogin']/button");
	By emailTxb = By.id("loginEmail");
	By passwordTxb = By.id("loginPassword");
	By loginBtn = By.xpath("//*[@id='loginSubmit'][not(@disabled)]");
	
	By paynowBtn = By.id("creditPayNow");
	By returnMerchantBtn = By.id("receiptToMerchant");

	
	public PS_openbucks2 (Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage finishPaymentOpenbuck() {
		this.clickMenuLoginButton();
		this.fillForm();
		return new ThankyouPage(driver);
	}
	
	public ThankyouPage finishPayment() {
		this.clickToLoginButton();
		this.setEmail();
		this.setPassword();
		this.clickLoginButton();
		this.clickPayNowButton();
		this.clickReturnMerchant();
		return new ThankyouPage(driver);
	}
	
	public void fillForm() {
		this.setEmail();
		this.setPassword();
		this.clickLoginButton();
		this.clickPayNowButton();
		this.clickReturnMerchant();
	}
	
	
	private void clickMenuLoginButton() {
		Element element = driver.getElement(menuLoginBtn);
		driver.sleep(2000);
		element.click();
	}
	
	private void clickToLoginButton() {
		Element element = driver.getElement(toLoginBtn);
		element.click();
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		element.sendKeys(PASSWORD);
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}

	private void clickPayNowButton() {
		Element element = driver.getElement(paynowBtn);
		element.click();
	}
	
	private void clickReturnMerchant() {
		Element element = driver.getElement(returnMerchantBtn);
		element.click();
	}

}
