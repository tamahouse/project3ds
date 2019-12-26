package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_yamoney  extends PS_t3_url {
	

	public PS_yamoney(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_yamoney2 getNewWindows() {
		return new PS_yamoney2 (driver);
	}
	
	

}
