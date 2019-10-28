package automation.project3ds;

import org.openqa.selenium.By;

public class ThankyouPage {
	
	Driver driver;
	
	By complete = By.xpath("//*[contains(@class,'thanks')]");

	public ThankyouPage(Driver driver) {
		this.driver = driver;
		driver.getElement(complete);
	}
}
