package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_przelewy24 {
	
	Driver driver;
	
	String email = "meo@spam4.me";
	
	By bybank = By.xpath("//*[@id='payment-options']//a");
	By byemail = By.id("przelewy_email");
	By bypayButton = By.id("paybutton");

	public PS_przelewy24(Driver driver) {
		this.driver = driver;
	}
	
	private Element getRandomBank() {
		driver.getElement(bybank);
		List<Element> list = driver.getElements(bybank);
		Random random = new Random();
		int index = random.nextInt(list.size());
		Element element = list.get(index);
		return element;
	}
	
	private Element getBank(int index) {
		driver.getElement(bybank);
		driver.sleep(2000);
		List<Element> list = driver.getElements(bybank);
		Element element = list.get(index);
		return element;
	}
	
	public PS_przelewy24_2 createPaymentWithSelectBank() {
		this.clickBank(0);
		this.setEmail();
		this.clickPayButton();
		return new PS_przelewy24_2(driver);
	}
	
	public PS_przelewy24_2 createPaymentWithoutSelectBank() {
		this.clickBank(1);
		this.setEmail();
		this.clickPayButton();
		return new PS_przelewy24_2(driver);
	}
	
	private void setEmail() {
		Element element = driver.getElement(byemail);
		element.sendKeys(email);
	}
	
	private void clickBank(int index) {
		Element element = this.getBank(index);
		element.click();
	}
	
	private void clickPayButton() {
		Element element = driver.getElement(bypayButton);
		element.click();
	}
}
