package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_canadapost  extends PS_t3_url {
	

	public PS_canadapost(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_canadapost2 getNewWindows() {
		return new PS_canadapost2 (driver);
	}
	

}
