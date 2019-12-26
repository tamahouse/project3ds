package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_mol {
	
	Driver driver;
	

	public PS_mol(Driver driver) {
		WebElement webElement = driver.getElement(By.xpath("//iframe[contains(@src,'mol.com')]")).getWebElement();
		driver.switchTo().frame(webElement);
		this.driver = driver;
	}
	
	
	


	
}
