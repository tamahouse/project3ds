package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_rapipago2 extends PS_astropay{
	
	String redirectUrl = "api/rapipago";
	
	By bank = By.xpath("//*[@class='payment-method']");

	public PS_rapipago2(Driver driver) {
		super(driver);
		driver.switchToWindows(redirectUrl, true,60000);
	}
	



	
}

