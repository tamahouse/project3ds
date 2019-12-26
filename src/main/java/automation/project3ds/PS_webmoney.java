package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_webmoney  extends PS_t3_url {
	

	public PS_webmoney(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_webmoney2 getNewWindows() {
		return new PS_webmoney2 (driver);
	}
	
	

}
