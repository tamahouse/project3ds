package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_bancontact  extends PS_t3_url {
	

	public PS_bancontact(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_bancontact2 getNewWindows() {
		return new PS_bancontact2 (driver);
	}
	
	

}
