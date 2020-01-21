package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_mycardwallet  extends PS_t3_url {
	

	public PS_mycardwallet(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_mycardwallet2 getNewWindows() {
		return new PS_mycardwallet2(driver);
	}
	
	

}
