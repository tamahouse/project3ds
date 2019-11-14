package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

public class Widget3dsNoIframe {
	
	static Driver otpDriver;
	static String mainTab;
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
		driver.switchTo().window(mainTab);
	}
	
	private static void switchWindow() throws Exception {
		Driver driver = AnnotationPage.getDriver();
		List<String> tabs = driver.waitForNewTab();
		for(String tab: tabs) {
			driver.switchTo().window(tab);
			driver.waitUrlNotBlank();
			String url = driver.getCurrentUrl();
			if(!url.contains("test-offerwall")) {
				driver.switchTo().window(tab);
				otpDriver = driver;
			}else {
				mainTab = tab;
			}
		}
	}
	
	private static void setPassword(String password) throws Exception {
		Element element = otpDriver.getElement(textbox);
		element.sendKeys(password);
	}
	
	private static void clickSubmit() throws Exception {
		Element element = otpDriver.getElement(summitButton);
		element.click();
		Thread.sleep(3000);
	}

}
