package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_cherrycredits  extends PS_t3_url {
	

	public PS_cherrycredits(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_cherrycredits2 getNewWindows() {
		return new PS_cherrycredits2 (driver);
	}
	
	

}
