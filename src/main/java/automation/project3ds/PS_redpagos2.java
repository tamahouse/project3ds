package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_redpagos2 extends PS_astropay{
	
	String redirectUrl = "api/redpagos";
	
	String CI = "11211234";
	
	By bank = By.xpath("//*[@class='payment-method']");

	public PS_redpagos2(Driver driver) {
		super(driver);
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	private void setCI() {
		this.set_cpf(CI);
	}
	
	public void createPayment() {
		this.set_name();
		this.setCI();
		this.set_email();
		this.clickSummit();
		this.waitForError();
	}
	
	
	
	
	



	
}

