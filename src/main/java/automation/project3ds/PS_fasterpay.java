package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_fasterpay  extends PS_t3_url {
	

	public PS_fasterpay(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_fasterpay2 getNewWindows() {
		return new PS_fasterpay2 (driver);
	}
	

}
