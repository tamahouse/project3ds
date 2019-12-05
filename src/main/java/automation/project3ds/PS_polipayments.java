package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_polipayments  extends PS_t3_url {
	

	public PS_polipayments(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_polipayments2 getNewWindows() {
		return new PS_polipayments2(driver);
	}
	
	

}
