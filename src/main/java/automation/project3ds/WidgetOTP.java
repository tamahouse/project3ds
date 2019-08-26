package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetOTP {
	
	private static Driver driver = AnnotationPage.getDriver();

	private static By passwordTextbox = By.xpath("//input[@type='password']");
	private static By submitButton = By.xpath("//input[@value='SUBMIT']");
	
	
	public static void completeOTP() {
		setPassword();
		clickSubmitButton();
	}
	
	private static void setPassword() {
		Element element = driver.getElement(passwordTextbox);
		element.sendKeys("BRICK");
	}
	
	private static void clickSubmitButton() {
		Element element = driver.getElement(submitButton);
		element.click();
	}
}
