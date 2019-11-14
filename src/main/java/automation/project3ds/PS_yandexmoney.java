package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;

public class PS_yandexmoney {
	
	Driver driver;
	
	String redirectUrl = "yandex.ru";
	
	By payBtn = By.xpath("//button[./span[text()='Pay']]");
	By backToStoreBtn = By.xpath("//a[text()='Back to store']");
	By backToStoreCompletedBtn = By.xpath("//*[.//*[contains(@class,'title_last_yes')]]//a[text()='Back to store']");
	
	String transId;
	String successUrl;
	
	public PS_yandexmoney(Driver driver) {
		this.driver = driver;
		try {
		driver.switchToWindows(redirectUrl, true,60000);
		}catch(UnhandledAlertException e) {
			driver.switchTo().alert().accept();
			driver.switchToWindows(redirectUrl, true,60000);
		}
		String url = driver.getCurrentUrl();
		this.transId = url.substring(url.indexOf("orderId=")+8);
	}
	
	public ThankyouPage createPayment() {
		this.clickPayButton();
		this.clickkBackToStoreCompletedButton();
		return new ThankyouPage(driver);
	}
	
	public String getTransId() {
		return this.transId;
	}
	
	
	public void clickPayButton() {
		Element element = driver.getElement(payBtn);
		driver.sleep(2000);
		element.click();
	}
	
	private void clickkBackToStoreButton() {
		Element element = driver.getElement(backToStoreBtn);
		element.click();
	}
	
	public void clickkBackToStoreCompletedButton() {
		Element element = driver.getElement(backToStoreCompletedBtn);
		successUrl = element.getAttribute("href");
		System.out.println(successUrl);
		element.click();
	}


}
