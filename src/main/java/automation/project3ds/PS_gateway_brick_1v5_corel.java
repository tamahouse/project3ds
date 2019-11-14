package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automation.project3ds.Driver.Browser;


public class PS_gateway_brick_1v5_corel extends PS_gateway_brick_1v5{

	
	public PS_gateway_brick_1v5_corel(Driver driver) {
		super(driver);
	}
	
	
	public Brick_1v5_corel getBrick_1v5_corel() {
		return new Brick_1v5_corel(driver);
	}
	
	
	
}
