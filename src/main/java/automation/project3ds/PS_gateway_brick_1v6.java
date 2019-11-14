package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automation.project3ds.Driver.Browser;


public class PS_gateway_brick_1v6 {

	Driver driver;
	
	public PS_gateway_brick_1v6(Driver driver) {
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		AnnotationPage.sleep(7000);
		this.driver = driver;
	}
	
	
	public Brick_1v6 getBrick_1v6() {
		return new Brick_1v6(driver);
	}
	
	
	public void finish3dsPolkOFF() throws Exception {
		driver.switchTo().defaultContent();
		driver.switchToWindows("3dsecure", true);
		Widget3dsPolk obj = new Widget3dsPolk(driver);
		AnnotationPage.sleep(1000);
		obj.clickSubmit();
		driver.switchToWindows("test-offerwall", true);
	}
	
	public void finish3dsV1OFF() {
		driver.switchTo().defaultContent();
		driver.switchToWindows("cardinalcommerce", true);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
		driver.switchToWindows("test-offerwall", true);
	}
	
	public void finish3dsV1ON() {
		if(driver.getBrowser().equals(Browser.IE)) {
			driver.switchTo().defaultContent();
			driver.switchToWindows("brick-3ds-v2", true);
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV1 obj = new Widget3dsV1(driver);
			obj.finish();
			driver.switchToWindows("test-offerwall", true);
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
			driver.switchToWindows("test-offerwall", true);
		}else {
			WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
			driver.switchTo().frame(iframe);
			Widget3dsV2 obj = new Widget3dsV2(driver);
			obj.finish();
		}

	}
	
}
