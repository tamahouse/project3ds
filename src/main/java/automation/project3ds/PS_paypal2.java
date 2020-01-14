package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

public class PS_paypal2 {
	
	Driver driver;
	String EMAIL = "pwoptiman1-buyer@gmail.com";
	String PASSWORD = "PeterInc#";
	
	String redirectUrl = "paypal.com";
	
	
	By emailTxb = By.id("email");
	By nextBtn = By.id("btnNext");
	By passwordTxb = By.id("password");
	By loginBtn = By.id("btnLogin");
	
	By continueBtn = By.id("confirmButtonTop");

	
	public PS_paypal2 (Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	
	public ThankyouPage finishPayment() {
		this.setEmail();
		this.clickNextButton();
		this.setPassword();
		this.clickLoginButton();
		this.clickContinueButton();
		return new ThankyouPage(driver);
	}
	
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void clickNextButton() {
		Element element = driver.getElement(nextBtn);
		element.click();
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		driver.sleep(1000);
		element.sendKeys(PASSWORD);
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		driver.sleep(2000);
		element.click();
	}



}
