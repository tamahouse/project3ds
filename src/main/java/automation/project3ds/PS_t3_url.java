package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class PS_t3_url {
	
	Driver driver;
	
	public PS_t3_url(Driver driver, String widget) {
		if(widget.equals(WidgetType.TERMNIAL) || widget.equals(WidgetType.PW)) {
			driver.getElement(By.xpath("//*[contains(@class,'js-continue-new-window')]")).click();
		}
		this.driver = driver;
	}

}
