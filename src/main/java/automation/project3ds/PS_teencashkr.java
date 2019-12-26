package automation.project3ds;

import org.openqa.selenium.By;

public class PS_teencashkr {
	
	Driver driver;
	
	String CARD_ID = "MCARCH0000004360";
	String PASSWORD = "MLNWC4TYTDLAWLX4";
	
	By cardID = By.id("card_id");
	By password = By.id("card_password");
	By buyBtn = By.id("paybutton");
	By error = By.xpath("//*[@id='error'][not(contains(@style,'none'))]");

	public PS_teencashkr(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setCardID();
		this.setPassword();
		this.clickBuyButton();
		this.waitForError();
	}

	private void setCardID() {
		Element element = driver.getElement(cardID);
		element.sendKeys(CARD_ID);
	}
	
	private void setPassword() {
		Element element = driver.getElement(password);
		element.sendKeys(PASSWORD);
	}
	
	private void clickBuyButton() {
		Element element = driver.getElement(buyBtn);
		element.click();
	}
	
	private void waitForError() {
		driver.getElement(error);
	}

	
}
