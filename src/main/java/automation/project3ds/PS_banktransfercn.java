package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_banktransfercn  extends PS_t3_url {
	

	public PS_banktransfercn(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_banktransfercn2 getNewWindows() {
		return new PS_banktransfercn2 (driver);
	}
	
	

}
