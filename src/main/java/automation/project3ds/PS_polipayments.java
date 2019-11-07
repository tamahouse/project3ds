package automation.project3ds;

import org.openqa.selenium.By;

public class PS_polipayments {
	
	Driver driver;
	
	String COMPLETED_USERNAME = "DemoShopper";
	String COMPLETED_PASSWORD = "DemoShopper";
	String FAILED_USERNAME = "Shopper10";
	String FAILED_PASSWORD = "Shopper10";
	
	String username = COMPLETED_USERNAME;
	String password = COMPLETED_PASSWORD;
	
	String redirectUrl = "paywithpoli";
	
	By bycontinueBtn = By.id("proceed-button");
	By byusernameTxb = By.name("Username");
	By bypasswordTxb = By.name("Password");
	By byloginButton = By.xpath("//*[@id='footerButtonsRight']/div/button[@type='submit'][./*[text()='Login']]");
	By bycontinueBtn2 = By.xpath("//*[@id='footerButtonsRight']/div/button[@type='submit'][./*[text()='Continue']]");
	By byconfirmBtn = By.xpath("//*[@id='footerButtonsRight']/div/button[@type='submit'][./*[text()='Confirm']]");
	By byreturnToMerchantBtn = By.id("returnToMerchantButton");
	
	public PS_polipayments(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public void setUserName_Password(Boolean isCompleted) {
		if(isCompleted == false) {
			this.username = FAILED_USERNAME;
			this.password = FAILED_PASSWORD;
		}
	}
	
	public ThankyouPage createPayment() {
		this.clickContinueButton();
		this.login();
		this.clickContinueButton2();
		this.clickConfirmButton();
		this.clickReturnButton();
		return new ThankyouPage(driver);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(bycontinueBtn);
		element.click();
	}
	
	private void login() {
		this.setUsername();
		this.setPassword();
		this.clickLoginButton();
	}
	
	private void setUsername() {
		Element element = driver.getElement(byusernameTxb);
		element.sendKeys(username);
	}
	
	private void setPassword() {
		Element element = driver.getElement(bypasswordTxb);
		element.sendKeys(password);
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(byloginButton);
		element.clickThroughLoading();
	}
	
	private void clickContinueButton2() {
		Element element = driver.getElement(bycontinueBtn2);
		element.clickThroughLoading();
	}
	
	private void clickConfirmButton() {
		Element element = driver.getElement(byconfirmBtn);
		element.clickThroughLoading();
	}
	
	private void clickReturnButton() {
		Element element = driver.getElement(byreturnToMerchantBtn);
		element.clickThroughLoading();
	}
}
