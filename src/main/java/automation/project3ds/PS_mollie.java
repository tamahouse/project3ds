package automation.project3ds;

import org.openqa.selenium.By;

public class PS_mollie {
	
	Driver driver;
	
	String PAID = "paid";
	String FAILED = "failed";
	String CANCELED = "canceled";
	String EXPIRED = "expired";
	
	String redirectUrl = "mollie.com";
	String status = PAID;
	
	By continueBtn = By.xpath("//button[@class='button form__button']");
	
	public PS_mollie(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage createPayment() {
		this.setStatus();
		this.clickContinueButton();
		return new ThankyouPage(driver);
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	private void setStatus() {
		By xpath = By.xpath("//input[@name='final_state'][@value='"+status+"']");
		Element element = driver.getElement(xpath);
		element.click();
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}

}
