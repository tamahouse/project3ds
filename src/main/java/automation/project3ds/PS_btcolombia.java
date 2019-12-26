package automation.project3ds;

import org.openqa.selenium.By;

public class PS_btcolombia {
	
	Driver driver;
	
	String DI = "34234234234";
	String EMAIL = "meo@spam4.me";
	
	By diTxb = By.id("di");
	By emailTxb = By.id("email");
	By summitBtn = By.id("btnSubmit");

	public PS_btcolombia(Driver driver) {
		this.driver = driver;
	}
	
	public PS_payvalida createPayment() {
		this.setDI();
		this.setEmail();
		this.clickSubmit();
		return new PS_payvalida(driver);
	}

	private void setDI() {
		Element element = driver.getElement(diTxb);
		element.sendKeys(DI);
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void clickSubmit() {
		Element element = driver.getElement(summitBtn);
		element.click();
	}

	
}
