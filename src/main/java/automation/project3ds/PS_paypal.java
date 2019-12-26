package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_paypal  extends PS_t3_url {
	

	public PS_paypal(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_paypal2 getNewWindows() {
		return new PS_paypal2 (driver);
	}
	

}
