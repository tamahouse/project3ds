package automation.project3ds;

import org.openqa.selenium.By;

public class Widget_p2_3 extends WidgetObject{
	
	
	public Widget_p2_3(Driver driver) {
		super(driver);
	}
	
	public PS_gateway_brick_1v5_corel getPS_gateway_brick_1v5_corel() throws Exception {
		return new PS_gateway_brick_1v5_corel(driver);
	}
}
