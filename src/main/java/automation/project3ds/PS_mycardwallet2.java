package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_mycardwallet2 {
	
	Driver driver;
	
	String EMAIL = "ricky@soft-world.com.tw";
	String PASSWORD = "sw123456";
	String SECURITY = "123456";
	
	String redirectUrl = "mycard520.com.tw";
	By mycardWalletLogo = By.xpath("//*[@id='intro']//*[@class='ptitle'][contains(text(),'MyCard')]");
	By emailTxb = By.id("MyCardMemId");
	By passwordTxb = By.id("MyCardMemPW");
	By loginBtn = By.id("Submit");
	By securityTxb = By.id("MyCardMemSafeCode");

	public PS_mycardwallet2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage finishPayment() {
		this.clickWalletLogo();
		this.setEmail();
		this.setPassword();
		this.clickLoginButton();
		this.setSecurityCode();
		this.clickSubmitButton();
		return new ThankyouPage(driver);
	}
	
	private void clickWalletLogo() {
		Element element = driver.getElement(mycardWalletLogo);
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
	
	private void setSecurityCode() {
		Element element = driver.getElement(securityTxb);
		element.sendKeys(SECURITY);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}
	
	
	

	
	

}
