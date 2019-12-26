package automation.project3ds;

import org.openqa.selenium.By;

public class PS_oxxomexico {
	
	Driver driver;
	
	String NAME = "Payment Wall";
	String EMAIL = "jane@doe.com";
	
	By nameTxb = By.id("name");
	By emailTxb = By.id("email");
	By submitBtn = By.id("payment_button");
	By error = By.xpath("//*[@id='error_message'][not(contains(@style,'none'))]");

	public PS_oxxomexico(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setName();
		this.setEmail();
		this.clickSubmitButton();
		this.waitForError();
	}

	private void setName() {
		Element element = driver.getElement(nameTxb);
		element.sendKeys(NAME);
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
	private void waitForError() {
		driver.getElement(error);
	}

	
}
