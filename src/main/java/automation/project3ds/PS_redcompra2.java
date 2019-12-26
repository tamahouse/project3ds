package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_redcompra2 extends PS_astropay{
	
	String redirectUrl = "api/redcompra";
	
	String RUT = "90079204";
	
	By bank = By.xpath("//*[@class='payment-method']");

	public PS_redcompra2(Driver driver) {
		super(driver);
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	private void setRUT() {
		Element element = driver.getElement(cpf);
		element.sendKeys(RUT);
	}
	
	public void createPayment() {
		this.set_name();
		this.setRUT();
		this.set_email();
		this.clickSummit();
		this.waitForError();
	}



	
}

