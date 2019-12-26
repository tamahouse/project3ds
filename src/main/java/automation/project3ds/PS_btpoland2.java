package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

public class PS_btpoland2 {
	Driver driver;

	String redirectUrl = "tpay.com";
	

	public PS_btpoland2 (Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}


}
