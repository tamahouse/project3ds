package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_wechatpayments {
	
	Driver driver;
	
	By qrImg = By.id("qr_code");
	String PAYMENT_PASSWORD = "748034";

	
	public PS_wechatpayments(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() throws Exception {
		this.waitForQR();
	}

	private void waitForQR() {
		driver.getElement(qrImg);
	}

}
