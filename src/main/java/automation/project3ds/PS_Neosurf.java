package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_Neosurf {

	static By enterPinTxb = By.id("pin1");
	static By continueBtn = By.xpath("//*[@id='submitNeosurfForm']/input");
	static By completed = By.id("returnFinal");
	static String pin = "2679053409";
	
	public static Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		WebElement frameMedia = driver.getElement(By.tagName("iframe")).getWebElement();
		driver.switchTo().frame(frameMedia);
		return driver;
	}
	
	public static void setPin() {
		Element element = getFrame().getElement(enterPinTxb);
		element.sendKeys(pin);
	}
	
	public static void clickContinueButton() {
		Element element = getFrame().getElement(continueBtn);
		element.click();
	}
	
	public static void waitForCompleted() {
		getFrame().getElement(completed);
	}
}
