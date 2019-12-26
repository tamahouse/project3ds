package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_kftc  extends PS_t3_url {
	

	public PS_kftc(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_kftc2 getNewWindows() {
		return new PS_kftc2 (driver);
	}
	
	

}
