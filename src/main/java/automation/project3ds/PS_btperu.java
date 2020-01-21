package automation.project3ds;

import org.openqa.selenium.By;

public class PS_btperu {
	
	Driver driver;
	
	String DI = "34234234234";
	String EMAIL = "humanbeing@gmail.com";
	
	By bankOption = By.xpath("//a[@data-bank-id]");
	By diTxb = By.id("di");
	By emailTxb = By.id("email");
	By summitBtn = By.id("btnSubmit");

	public PS_btperu(Driver driver) {
		this.driver = driver;
	}
	
	public PS_payvalida createPayment() {
		this.setBank();
		this.setDI();
		this.setEmail();
		this.clickSubmit();
		return new PS_payvalida(driver);
	}
	
	private void setBank() {
		Element element = driver.getElement(bankOption);
		element.click();
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
