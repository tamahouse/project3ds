package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_wavegame  extends PS_t3_url {
	

	public PS_wavegame(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_wavegame2 getNewWindows() {
		return new PS_wavegame2 (driver);
	}
	
	

}
