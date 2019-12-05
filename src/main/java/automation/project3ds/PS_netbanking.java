package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_netbanking  extends PS_t3_url {
	

	public PS_netbanking(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_netbanking2 getNewWindows() {
		return new PS_netbanking2(driver);
	}
	
	

}
