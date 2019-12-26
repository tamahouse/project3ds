package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_rapida {
	
	Driver driver;
	
	String EMAIL = "meo@spam4.me";
	String PHONE = "9942836403";
	
	By emailTxb = By.id("email");
	By phoneTxb = By.id("phone");
	By continueBtn = By.id("continue");
	By codeTxt = By.id("code");
	
	By bank = By.xpath("//*[@class='bank_image_div']");

	public PS_rapida(Driver driver) {
		this.driver = driver;
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.sendKeys(PHONE);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	private void waitForCode() {
		driver.getElement(codeTxt);
	}
	
	public void createPayment() {
		this.setEmail();
		this.setPhone();
		this.clickContinueButton();
		this.waitForCode();
	}

}
