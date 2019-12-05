package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_epspayment  extends PS_t3_url {
	

	public PS_epspayment(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_epspayment2 getNewWindows() {
		return new PS_epspayment2(driver);
	}
	
	

}
