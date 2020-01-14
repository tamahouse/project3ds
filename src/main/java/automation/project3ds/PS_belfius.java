package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_belfius  extends PS_t3_url {
	

	public PS_belfius(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_belfius2 getNewWindows() {
		return new PS_belfius2(driver);
	}
	
	

}
