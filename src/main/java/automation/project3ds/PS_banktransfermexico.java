package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_banktransfermexico extends PS_t3_url {

	public PS_banktransfermexico(Driver driver, String widget) {
		super(driver, widget);
		// TODO Auto-generated constructor stub
	}

	public PS_banktransfermexico2 getNewWindows() {
		return new PS_banktransfermexico2 (driver);
	}
	
}

