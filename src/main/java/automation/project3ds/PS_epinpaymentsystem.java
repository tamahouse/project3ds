package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_epinpaymentsystem {
	
	Driver driver;
	
	String pin = "7164068647811300";
	
	By pinTxb = By.xpath("//*[@id='code']/input");
	By buyBtn = By.id("but");
	
	
	public PS_epinpaymentsystem(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setPin();
		this.clickBuyButton();
	}

	private void setPin() {
		Element element = driver.getElement(pinTxb);
		element.sendKeys(pin);
	}
	
	private void clickBuyButton() {
		Element element = driver.getElement(buyBtn);
		driver.sleep(1000);
		element.click();
	}
	
	public String getPin() {
		return this.pin;
	}

}
