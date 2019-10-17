package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Widget3dsV1 {

	Driver driver;

	static By password = By.id("password");
	static By submitBtn = By.xpath("//input[@value = 'Submit']");

	public Widget3dsV1(Driver driver) {
		WebElement iframe = driver.getElement(By.id("authWindow")).getWebElement();
		driver.switchTo().frame(iframe);
		this.driver = driver;
	}
	
	public void finish() {
		this.setPassword();
		this.clickSubmit();
	}

	private void setPassword() {
		Element element = driver.getElement(password);
		element.sendKeys("1234");
	}

	private void clickSubmit() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
}
