package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_dragonpay {
	
	Driver driver;

	public PS_dragonpay(Driver driver) {
		WebElement webElement = driver.getElement(By.xpath("//iframe[contains(@src,'dragonpay.ph')]")).getWebElement();
		driver.switchTo().frame(webElement);
		this.driver = driver;
	}
	
	
	


	
}
