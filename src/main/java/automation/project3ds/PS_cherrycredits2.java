package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.Select;

public class PS_cherrycredits2 {
	
	Driver driver;
	
	String redirectUrl = "api/cherrycredits";
	
	
	public PS_cherrycredits2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	

}
