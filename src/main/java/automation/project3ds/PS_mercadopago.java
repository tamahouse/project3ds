package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_mercadopago {
	
	Driver driver;
	
	
	public String email;
	
	By emailTxb = By.id("email");
	By continueBtn = By.id("paybutton");

	public PS_mercadopago(Driver driver) {
		this.driver = driver;
	}
	
	public PS_mercadopago2 createPayment() {
		setEmail();
		clickContinueButton();
		return new PS_mercadopago2(driver);
	}


	public String getEmail() {
		return email;
	}
	
	
	public void setEmail() {
		String timestamp = AnnotationPage.timestamp();
		this.email = "test_payer_"+timestamp+"@testuser.com";
		Element element = driver.getElement(emailTxb);
		driver.sleep(1000);
		element.sendKeys(email);
	}
	
	public void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
}
