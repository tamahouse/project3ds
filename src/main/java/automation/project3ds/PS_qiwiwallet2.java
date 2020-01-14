package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

public class PS_qiwiwallet2 {
	
	Driver driver;
	
	String PHONE = "78000008525";
	
	String redirectUrl = "api/qiwiwallet";
	
	By phoneTxb = By.id("phone_number_id");
	By continueBtn = By.id("qiwiwallet_action");
	
	
	public PS_qiwiwallet2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public PS_qiwiwallet3 createPayment() {
		this.setPhone();
		this.clickContinueButton();
		return new PS_qiwiwallet3(driver);
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.clear();
		element.sendKeys(PHONE);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}


}
