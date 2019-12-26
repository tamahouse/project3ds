package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_idealnl {
	
	Driver driver;
	
	By bank = By.xpath("//*[@id='ideal_form']//*[@data-id]");

	public PS_idealnl(Driver driver) {
		this.driver = driver;
	}
	
	public PS_idealnl2 clickBank() {
		Element element = driver.getElement(bank);
		driver.sleep(1000);
		element.click();
		return new PS_idealnl2 (driver);
	}

}
