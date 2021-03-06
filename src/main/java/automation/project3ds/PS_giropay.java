package automation.project3ds;

import org.openqa.selenium.By;

public class PS_giropay {
	
	Driver driver;
	
	String bic = "TESTDETT421";

	
	By bicTxb = By.id("giropay_bic");
	By continueButton = By.id("submitButton");


	public PS_giropay(Driver driver) {
		this.driver = driver;
	}
	
	public PS_giropay2 submitBic() {
		this.setBic();
		this.clickContinue();
		return new PS_giropay2(driver);
	}
	
	private void setBic() {
		Element element = driver.getElement(bicTxb);
		driver.sleep(1000);
		element.sendKeys(bic);
	}
	
	private void clickContinue() {
		Element element = driver.getElement(continueButton);
		element.click();
	}
	

}
