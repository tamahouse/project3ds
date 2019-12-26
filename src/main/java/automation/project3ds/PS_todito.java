package automation.project3ds;

import org.openqa.selenium.By;

public class PS_todito {
	
	Driver driver;
	
	String CARD_NUMBER = "cardNumber";
	String PIN = "PIN";
	
	By cardNumberTxb = By.id("todito_cardnumber");
	By pinTxb = By.id("todito_pin");
	By buyBtn = By.id("paybutton");
	By error = By.xpath("//*[@id='error'][not(contains(@style,'none'))]");

	public PS_todito(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setCardNumber();
		this.setPIN();
		this.clickBuyButton();
		this.waitForError();
	}

	private void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(CARD_NUMBER);
	}
	
	private void setPIN() {
		Element element = driver.getElement(pinTxb);
		element.sendKeys(PIN);
	}
	
	private void clickBuyButton() {
		Element element = driver.getElement(buyBtn);
		element.click();
	}
	
	private void waitForError() {
		driver.getElement(error);
	}

	
}
