package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_paysafecard {
	
	Driver driver;
	
	String CARD_NUMBER = "cardNumber";
	String PIN = "PIN";
	
	By cardNumberTxb = By.id("todito_cardnumber");
	By pinTxb = By.id("todito_pin");
	By buyBtn = By.id("paybutton");

	public PS_paysafecard(Driver driver) {
		WebElement webElement = driver.getElement(By.xpath("//*[@id='ps_content']/iframe")).getWebElement();
		driver.switchTo().frame(webElement);
		this.driver = driver;
	}
	
	
	


	
}
