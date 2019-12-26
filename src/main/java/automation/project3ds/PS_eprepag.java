package automation.project3ds;

import org.openqa.selenium.By;

public class PS_eprepag {
	
	Driver driver;
	
	String EMAIL = "meo@spam4.me";
	
	By emailTxb = By.id("client_email");
	By buyBtn = By.id("paybutton");

	public PS_eprepag(Driver driver) {
		this.driver = driver;
	}
	
	public PS_eprepag2 createPayment() {
		this.setEmail();
		this.clickBuyButton();
		return new PS_eprepag2(driver);
	}

	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void clickBuyButton() {
		Element element = driver.getElement(buyBtn);
		element.click();
	}
	

	
}
