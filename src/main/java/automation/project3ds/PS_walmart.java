package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_walmart  extends PS_t3_url {
	

	public PS_walmart(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_walmart2 getNewWindows() {
		return new PS_walmart2 (driver);
	}
	

}
