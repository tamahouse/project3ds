package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_yandexmoney  extends PS_t3_url {
	

	public PS_yandexmoney(Driver driver, String widget) {
		super(driver, widget);
	}
	
	public PS_yandexmoney2 getNewWindows() {
		return new PS_yandexmoney2(driver);
	}
	
	

}
