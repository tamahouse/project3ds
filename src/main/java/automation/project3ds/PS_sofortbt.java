package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_sofortbt  extends PS_t3_url {
	

	public PS_sofortbt(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_sofort getNewWindows() {
		return new PS_sofort(driver);
	}
	
	

}
