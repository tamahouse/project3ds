package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_openbucks  extends PS_t3_url {
	

	public PS_openbucks(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_openbucks2 getNewWindows() {
		return new PS_openbucks2 (driver);
	}
	

}
