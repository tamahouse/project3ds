package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_alipay  extends PS_t3_url {
	

	public PS_alipay(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_alipay2 getNewWindows() {
		return new PS_alipay2(driver);
	}
	
	

}