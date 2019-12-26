package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_redcompra extends PS_t3_url {

	public PS_redcompra(Driver driver, String widget) {
		super(driver, widget);
		// TODO Auto-generated constructor stub
	}

	public PS_redcompra2 getNewWindows() {
		return new PS_redcompra2 (driver);
	}
	
}

