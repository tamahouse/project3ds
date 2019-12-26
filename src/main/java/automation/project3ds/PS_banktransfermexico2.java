package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_banktransfermexico2 extends PS_astropay{
	
	String redirectUrl = "api/banktransfermexico";
	

	public PS_banktransfermexico2(Driver driver) {
		super(driver);
		driver.switchToWindows(redirectUrl, true,60000);
	}
	




	
}

