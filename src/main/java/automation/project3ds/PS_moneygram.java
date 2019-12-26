package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_moneygram  extends PS_t3_url {
	

	public PS_moneygram(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_moneygram2 getNewWindows() {
		return new PS_moneygram2 (driver);
	}
	

}
