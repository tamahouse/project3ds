package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_mobilegateway {
	
	Driver driver;
	
	String CARRIER = "66";
	String PHONE = "1445544211";
	String pin = "ACBDEF";
	
	By carrierSlt = By.xpath("//article[contains(@id,'step')][not(contains(@style,'none'))]//*[@id='carrier_direct']");
	By phoneTxb = By.xpath("//article[contains(@id,'step')][not(contains(@style,'none'))]//*[@id='input_phone']");
	By continueBtn = By.xpath("//article[contains(@id,'step')][not(contains(@style,'none'))]//*[@id='button_phone']");
	By pinTxb = By.xpath("//article[contains(@id,'step')][not(contains(@style,'none'))]//*[@id='input_pin']");
	By continueBtn2 = By.xpath("//article[contains(@id,'step')][not(contains(@style,'none'))]//*[@id='button_pin']");
	By success = By.xpath("//article[contains(@class,'success')][not(contains(@style,'none'))][@id='final_animation']");

	public PS_mobilegateway(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setCarrier();
		this.setPhone();
		this.clickContinueButton1();
		this.setPin();
		this.clickContinueButton2();
		this.waitForSuccess();
	}

	private void setCarrier() {
		Select select = driver.getSelect(carrierSlt);
		driver.sleep(2000);
		select.selectByValue(CARRIER);
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		driver.sleep(2000);
		element.sendKeys(PHONE);
	}
	
	private void clickContinueButton1() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	private void setPin() {
		Element element = driver.getElement(pinTxb);
		element.sendKeys(pin);
	}
	
	private void clickContinueButton2() {
		Element element = driver.getElement(continueBtn2);
		element.click();
	}
	
	private void waitForSuccess() {
		driver.getElement(success);
	}
}
