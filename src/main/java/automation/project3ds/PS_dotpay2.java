package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

public class PS_dotpay2 {
	Driver driver;

	String redirectUrl = "test_fake";
	By acceptBtn = By.id("submit_success");
	By nextBtn = By.xpath("//*[@value='Next']");
	

	public PS_dotpay2 (Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage finishPayment() {
		this.clickAcceptBtn();
		return new ThankyouPage(driver);
	}
	
	public void clickAcceptBtn() {
		Element element = driver.getElement(acceptBtn);
		element.click();
	}
	
	public void clickNextBtn() {
		try {
		Element element = driver.getElement(nextBtn,1000);
		element.click();
		}catch (Exception ignore) {
			
		}
	}
	


}
