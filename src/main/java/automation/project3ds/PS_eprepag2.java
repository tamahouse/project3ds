package automation.project3ds;

import org.openqa.selenium.By;

public class PS_eprepag2 {
	
	Driver driver;
	
	String EMAIL = "meo@spam4.me";
	String redirectUrl = "e-prepag.com.br";
	
	By emailTxb = By.id("client_email");
	By buyBtn = By.id("paybutton");

	public PS_eprepag2(Driver driver) {
		driver.switchToWindows(redirectUrl, true);
		this.driver = driver;
	}
	
	

	
}
