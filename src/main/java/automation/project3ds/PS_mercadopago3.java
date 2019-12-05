package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_mercadopago3 {
	
	Driver driver;
	public static String EMAIL = "test_user_82315550@testuser.com";
	
	String redirectUrl = "mercadolivre.com";
	String email = EMAIL;
	String password = "qatest7910";
	
	By emailTxb = By.id("user_id");
	By continueBtn = By.xpath("//*[@value='Continuar']");
	By passwordTxb = By.id("password");
	By completeBtn = By.id("action-complete");

	public PS_mercadopago3(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public void login() {
		this.setUserID();
		this.clickContinueButton();
		this.setPassword();
		this.clickCompletedButton();
	}
	
//	public PS_mercadopago2 createPayment_AccountOption() {
//		this.setUserID();
//		this.clickContinueButton();
//		this.setPassword();
//		this.clickCompletedButton();
//		
//		return ps2;
//	}
	
	private void setUserID() {
		Element element = driver.getElement(emailTxb);
		driver.sleep(1000);
		element.sendKeys(email);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		driver.sleep(1000);
		element.sendKeys(password);
	}
	
	private void clickCompletedButton() {
		Element element = driver.getElement(completeBtn);
		element.click();
	}
	
}
