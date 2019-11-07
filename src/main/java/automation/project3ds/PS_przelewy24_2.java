package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_przelewy24_2 {
	
	Driver driver;
	String redirectUrl = "sandbox.przelewy24.pl/";
	
	By bybank = By.xpath("//*[@class='method-group merchantCustomBackground']/div/a[not(contains(@class,'inactive'))]");
	By byzalogujButton = By.xpath("//button[text()='Zaloguj']");
	By byzaplacButton = By.id("pay_by_link_pay");
	
	By byzaplacDirectButton = By.id("reagulation-accept-button");
	
	public PS_przelewy24_2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage finishPaymentWithSelectBank() {
		this.clickBank();
		this.clickZalogujButton();
		this.clickZaplacButton();
		return new ThankyouPage(driver);
	}
	
	public ThankyouPage finishPaymentWithoutSelectBank() {
		this.clickZaplacDirectButton();
		this.clickZalogujButton();
		this.clickZaplacButton();
		return new ThankyouPage(driver);
	} 
	
	private Element getRandomBank() {
		driver.getElement(bybank);
		List<Element> list = driver.getElements(bybank);
		Random random = new Random();
		int index = random.nextInt(list.size());
		Element element = list.get(index);
		return element;
	}
	
	private void clickBank() {
		Element element = this.getRandomBank();
		element.click();
	}
	
	private void clickZalogujButton() {
		Element element = driver.getElement(byzalogujButton);
		element.click();
	}
	
	private void clickZaplacButton() {
		Element element = driver.getElement(byzaplacButton);
		element.click();
	}

	private void clickZaplacDirectButton() {
		Element element = driver.getElement(byzaplacDirectButton);
		element.click();
	}
}
