package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_sepadirectdebit {
	
	Driver driver;
	
	By proceedBtn = By.id("proceed-step");
	By confirm = By.id("confirm");

	public PS_sepadirectdebit(Driver driver) {
		this.driver = driver;
	}
	
	private void clickProceedButton() {
		Element element = driver.getElement(proceedBtn);
		element.click();
	}
	
	private void waitForConfirm() {
		driver.getElement(confirm);
	}
	
	public void createPayment() {
		this.clickProceedButton();
	}
	

}
