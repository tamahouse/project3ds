package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_bradescobrazil extends PS_ebanx {
	
	
	
	By radioButton = By.xpath("//*[@id='list-payment-options']/*[2]");


	public PS_bradescobrazil(Driver driver) {
		super(driver);
	}
	
	public PS_ebanx2 createPaymentWithRadio() throws Exception {
		this.clickRadioButton();
		return this.createPayment();
	}
	
	private void clickRadioButton() {
		driver.getElement(radioButton).click();
	}
	

}

