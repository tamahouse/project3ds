package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class Widget3dsNoIframe {
	
	static Driver windowDriver;
	static By textbox = By.xpath("//input[@name='code']");
	static By summitButton = By.xpath("//input[@value='SUBMIT']");
	
	public static void success3ds() throws Exception {
		String password = "BRICK";
		switchWindow();
		setPassword(password);
		clickSubmit();
		switchWindowBack();
	}
	
	public static void failed3ds() throws Exception {
		String password = "failed";
		switchWindow();
		setPassword(password);
		clickSubmit();
		switchWindowBack();
	}
	
	private static void switchWindowBack() throws Exception {
		Driver driver = AnnotationPage.getDriver();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	
	private static Driver switchWindow() throws Exception {
		Driver driver = AnnotationPage.getDriver();
		List<String> tabs = driver.waitForNewTab();
		driver.switchTo().window(tabs.get(1));
		windowDriver = driver;
		return windowDriver;
	}
	
	private static void setPassword(String password) throws Exception {
		Element element = windowDriver.getElement(textbox);
		element.sendKeys(password);
	}
	
	private static void clickSubmit() throws Exception {
		Element element = windowDriver.getElement(summitButton);
		element.click();
		Thread.sleep(3000);
	}

}
