package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_alipay2 {
	
	Driver driver;
	
	String USER = "cnbuyer_6384@alitest.com";
	String PASSWORD = "b111111";
	String ALIPASSWORD = "b111111";
	
	String redirectUrl = "alipaydev.com";
	
	By computerBtn = By.id("J_tip_qr");
	By userTxb = By.id("J_tLoginId");
	By passwordTxb = By.id("payPasswd_rsainput");
	By loginBtn = By.id("J_newBtn");
	By alipasswordTxb = By.id("payPassword_rsainput");
	By confirmBtn = By.id("J_authSubmit");
	


	public PS_alipay2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
		AnnotationPage.sleep(1000);
	}
	
	public ThankyouPage finishPayment() {
		this.clickComputerButton();
		this.setUser();
		this.setPassword();
		this.clickLoginButton();
		this.setAliPassword();
		this.clickConfirm();
		return new ThankyouPage(driver);
	}
	
	private void clickComputerButton() {
		Element element = driver.getElement(computerBtn);
		driver.sleep(1000);
		element.click();
	}
	
	private void setUser() {
		Element element = driver.getElement(userTxb);
		driver.sleep(1000);
		element.sendKeys(USER);
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		element.sendKeys(PASSWORD);
		driver.sleep(2000);
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}
	
	private void setAliPassword() {
		Element element = driver.getElement(alipasswordTxb);
		element.sendKeys(ALIPASSWORD);
		driver.sleep(1000);
	}
	
	private void clickConfirm() {
		Element element = driver.getElement(confirmBtn);
		element.click();
	}

}
