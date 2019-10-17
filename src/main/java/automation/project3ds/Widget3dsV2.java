package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Widget3dsV2 {

	Driver driver;

	static By codeTxb = By.xpath("//*[@name='challengeDataEntry']");
	static By submitBtn = By.xpath("//input[@value = 'SUBMIT']");

	public Widget3dsV2(Driver driver) {
		this.driver = driver;
	}
	
	public void finish() {
		this.setCode();
		this.clickSubmit();
	}

	private void setCode() {
		Element element = driver.getElement(codeTxb);
		element.sendKeys("1234");
	}

	private void clickSubmit() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
}
