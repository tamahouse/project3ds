package automation.project3ds;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automation.project3ds.Driver.Browser;


public class PS_gateway_brick_1v5 {

	
	Brick_1v5 brick;
	
	public PS_gateway_brick_1v5() {
		AnnotationPage.sleep(7000);
		brick = new Brick_1v5(getFrame());
	}
	
	public Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		return driver;
	}
	
	public void setBrick() {
		brick = new Brick_1v5(getFrame());
	}
	
	public void setCardNumber(String cardNumber) {
		brick.setCardNumber(cardNumber);
	}
	
	public void createPayment() {
		brick.createPayment();
	}
	
	public void finish3dsPolkOFF() throws Exception {
		Driver driver = getFrame();
		driver.switchTo().defaultContent();
		driver.switchToWindows("3dsecure", true);
		Widget3dsPolk obj = new Widget3dsPolk(driver);
		AnnotationPage.sleep(1000);
		obj.clickSubmit();
		driver.switchToWindows("test-offerwall", true);
	}
	
	public void finish3dsV1OFF() {
		Driver driver = getFrame();
		driver.switchTo().defaultContent();
		driver.switchToWindows("cardinalcommerce", true);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
		driver.switchToWindows("test-offerwall", true);
	}
	
	public void finish3dsV1ON() {
		Driver driver = getFrame();
		if(driver.getBrowser().equals(Browser.IE)) {
			driver.switchTo().defaultContent();
			driver.switchToWindows("brick-3ds-v2", true);
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV1 obj = new Widget3dsV1(driver);
			obj.finish();
			driver.switchToWindows("test-offerwall", true);
		}else {
			WebElement iframe = getFrame().getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV1 obj = new Widget3dsV1(driver);
			obj.finish();
		}

	}
	
	public void finish3dsV2ON() {
		Driver driver = getFrame();
		if(driver.getBrowser().equals(Browser.IE)) {
			driver.switchTo().defaultContent();
			driver.switchToWindows("brick-3ds-v2", true);
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV2 obj = new Widget3dsV2(driver);
			obj.finish();
			driver.switchToWindows("test-offerwall", true);
		}else {
			WebElement iframe = getFrame().getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV2 obj = new Widget3dsV2(driver);
			obj.finish();
		}

	}
	
	public void clickProcessButton() {
		brick.clickProcessButton();
	}
}
