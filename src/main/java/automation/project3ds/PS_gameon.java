package automation.project3ds;

import org.openqa.selenium.By;

public class PS_gameon {
	
	Driver driver;
	
	String CODE = "0000000000000000000000000";
	
	By codeTxb = By.xpath("//*[@id='cardCodePartAll' or @id = 'cardCodePart1']");
	By submitBtn = By.id("submit_button");
	By error = By.xpath("//*[@id='messages'][not(contains(@style,'none'))]");

	public PS_gameon(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setCode();
		this.clickSubmitButton();
		this.waitForError();
	}

	private void setCode() {
		Element element = driver.getElement(codeTxb);
		element.sendKeys(CODE);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
	private void waitForError() {
		driver.getElement(error,120000);
	}

	
}
