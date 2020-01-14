package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_cbc  extends PS_t3_url {
	

	public PS_cbc(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_cbc2 getNewWindows() {
		return new PS_cbc2(driver);
	}
	
	

}
