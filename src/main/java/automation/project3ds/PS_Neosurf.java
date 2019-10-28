package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_Neosurf {
	
	Driver driver;

	By enterPinTxb = By.id("pin1");
	By continueBtn = By.xpath("//*[@id='submitNeosurfForm']/input");
	By completed = By.id("returnFinal");
	String pin = "2679053409";
	
	public PS_Neosurf(Driver driver) {
		WebElement frameMedia = driver.getElement(By.tagName("iframe")).getWebElement();
		driver.switchTo().frame(frameMedia);
		this.driver = driver;
	}
	
	public void createPayment() {
		setPin();
		clickContinueButton();
		this.waitForCompleted();
	}
	
	public void setPin() {
		Element element = driver.getElement(enterPinTxb);
		element.highlight();
		element.sendKeys(pin);
	}
	
	public void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	public void waitForCompleted() {
		driver.getElement(completed);
	}
	
	
	

}
