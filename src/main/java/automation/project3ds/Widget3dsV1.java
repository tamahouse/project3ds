package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	}

	private void setPassword() {
		Element element = driver.getElement(password);
		element.sendKeys("1234");
		element.click();
		element.sendKeys(Keys.ENTER);
		try {
			AnnotationPage.sleep(5000);
			driver.switchTo().alert().accept();
		}catch(Exception ignore) {
			
		}
	}
	

	private void clickSubmit() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
}
