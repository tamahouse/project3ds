package automation.project3ds;

import org.openqa.selenium.By;


public class Brick_1v5_corel extends Brick_1v5{

	public Brick_1v5_corel(Driver driver) {
		super(driver);
	}
	
	public void createPayment() {
		this.setEmail();
		this.setCardNumber();
		this.setCardExp();
		this.setCardCvv();
		this.clickPayButton();
		this.waitForSuccessButton();
	}
	
}
