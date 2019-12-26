package automation.project3ds;

import org.openqa.selenium.By;

public class PS_dotpayingb2{
	
	Driver driver;
	
	String redirectUrl = "instruction";
	By logo = By.id("channel_image_");
	By confirmBtn = By.xpath("//*[text()='Confirm the payment execution']");
	
	public PS_dotpayingb2(Driver driver) {
		driver.switchToWindows(redirectUrl, true,60000);
		this.driver = driver;
	}
	

	public PS_dotpayingb3 clickLogo() {
		Element element = driver.getElement(logo);
		element.click();
		return new PS_dotpayingb3(driver);
	}
	
	public ThankyouPage clickConfirm() {
		Element element = driver.getElement(confirmBtn);
		element.click();
		return new ThankyouPage(driver);
	}

}
