package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_ebanx2 {
	
	Driver driver;
	
	By simulateYes = By.xpath("//a[contains(@href,'authorizeYes')]");

	public PS_ebanx2(Driver driver) {
		WebElement webElement = driver.getElement(By.id("payment-result__iframe")).getWebElement();
		driver.switchTo().frame(webElement);
		this.driver = driver;
	}
	
	public void finishPayment() {
		this.clickSimulateYes();
	}
	
	private void clickSimulateYes() {
		Element element = driver.getElement(simulateYes);
		element.click();
	}

}
