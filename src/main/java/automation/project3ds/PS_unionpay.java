package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_unionpay  extends PS_t3_url {
	

	public PS_unionpay(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_unionpay2 getNewWindows() {
		return new PS_unionpay2 (driver);
	}
	
	

}
