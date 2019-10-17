package automation.project3ds;

import org.openqa.selenium.By;

public class Widget3dsPolk {

	Driver driver;

	static By submitButton = By.xpath("//form[@name='postPAResToMPIForm']//input[@type='submit']");

	public Widget3dsPolk(Driver driver) {
		this.driver = driver;
	}
	
	public void clickSubmit() {
		Element element = driver.getElement(submitButton);
		element.click();
	}
	
}
