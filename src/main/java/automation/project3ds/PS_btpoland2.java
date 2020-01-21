package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

public class PS_btpoland2 {
	Driver driver;

	String redirectUrl = "tpay.com";
	By mbankSubOption = By.xpath("//*[@data-channel-id='18'][not(@data-is-business)]");
	By submitBtn = By.tagName("button");
	By returnBtn = By.xpath("//a[contains(@href,'success')]");
	

	public PS_btpoland2 (Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage finishPayment() {
		this.clickMBankSubOption();
		this.clickSubmitButton();
		this.clickReturnButton();
		return new ThankyouPage(driver);
	}
	
	private void clickMBankSubOption() {
		Element element = driver.getElement(mbankSubOption);
		element.click();
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
	private void clickReturnButton() {
		Element element = driver.getElement(returnBtn);
		element.click();
	}



}
