package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_vtc  extends PS_t3_url {
	

	public PS_vtc(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_vtc2 getNewWindows() {
		return new PS_vtc2(driver);
	}
	
	

}
