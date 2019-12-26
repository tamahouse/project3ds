package automation.project3ds;

import org.openqa.selenium.By;

public class PS_ticketsurf {
	
	Driver driver;
	
	String TICKET = "7073930615476783";
	
	By ticketTxb = By.id("code_1");
	By submitBtn = By.id("payment_button");
	By error = By.xpath("//*[@id='error-result'][not(contains(@style,'none'))][@style]");

	public PS_ticketsurf(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setTicket();
		this.clickSubmitButton();
		this.waitForError();
	}

	private void setTicket() {
		Element element = driver.getElement(ticketTxb);
		element.sendKeys(TICKET);
	}
	
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
	private void waitForError() {
		driver.getElement(error);
	}

	
}
