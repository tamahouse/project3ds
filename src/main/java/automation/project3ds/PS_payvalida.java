package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

public class PS_payvalida {
	
	Driver driver;
	
	String redirectUrl = "payvalida.com";
	
	
	
	public PS_payvalida(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}


}
