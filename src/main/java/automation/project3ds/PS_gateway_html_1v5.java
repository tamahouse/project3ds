package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automation.project3ds.Driver.Browser;


public class PS_gateway_html_1v5 {
	
	Driver driver;

	Brick_1v5 brick;
	
	public PS_gateway_html_1v5(Driver driver) {
		this.driver = driver;
		brick = new Brick_1v5(driver);
	}
	
	public void setCardNumber(String cardNumber) {
		brick.setCardNumber(cardNumber);
	}
	
	public void createPayment() {
		brick.createPayment();
	}
	
	public void waitForError() {
		brick.waitForError();
	}
	
	public String getEmail() {
		return brick.getEmail();
	}
	
	public void finish3dsPolk() {
		driver.switchToWindows("3dsecure", true);
		Widget3dsPolk obj = new Widget3dsPolk(driver);
		obj.clickSubmit();
		driver.switchToWindows("brick-new-widget", true);
	}
	
	public void finish3dsV1OFF() {
		driver.switchToWindows("cardinalcommerce", true);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
		driver.switchToWindows("brick-new-widget", true);
	}
	
	public void finish3dsV1ON() {
		if(driver.getBrowser().equals(Browser.IE)) {
			driver.switchTo().defaultContent();
			driver.switchToWindows("brick-3ds-v2", true);
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV1 obj = new Widget3dsV1(driver);
			obj.finish();
			driver.switchToWindows("brick-new-widget", true);
		}else {
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV1 obj = new Widget3dsV1(driver);
			obj.finish();
		}
	}
	
	public void finish3dsV2ON() {
		if(driver.getBrowser().equals(Browser.IE)) {
			driver.switchTo().defaultContent();
			driver.switchToWindows("brick-3ds-v2", true);
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV2 obj = new Widget3dsV2(driver);
			obj.finish();
			driver.switchToWindows("brick-new-widget", true);
		}else {
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV2 obj = new Widget3dsV2(driver);
			obj.finish();
		}
	}
	
	public void clickProcessButton() {
		brick.clickProcessButton();
	}
	
	public void waitForSuccessButton() {
		driver.switchTo().defaultContent();
		brick.waitForSuccessButton();
	}
	
}
