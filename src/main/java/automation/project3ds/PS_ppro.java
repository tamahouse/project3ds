package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

public class PS_ppro {
	Driver driver;
	By paymentOptions = By.xpath("//*[@id='payment-options']/*[@class='payment-method']");
	By thankyou = By.xpath("//*[./h3[text()='Thank you for your purchase!']]");
	

	public PS_ppro (Driver driver) {
		this.driver = driver;
	}
	

	public ThankyouPage finishPayment() throws Exception {
		By primaryButton = By.xpath("//button[@class='btn btn-primary']");
		List<String> tabs = driver.waitForNewTab();
		driver.switchTo().window(tabs.get(1));
		driver.getElement(primaryButton).click();;
		driver.getElement(primaryButton).click();
		driver.getElement(primaryButton).click();
		driver.navigate().refresh();
		return new ThankyouPage(driver);
	}

}
