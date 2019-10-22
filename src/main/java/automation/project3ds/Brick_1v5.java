package automation.project3ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Brick_1v5 {
	
	Driver driver;
	
	String email;
	String name = "Payment Wall";
	public String cardNumber = "4000000000000002";
	String exp = "0122";
	String cvv = "123";
	String zip = "32043";

	By id = By.id("brick");
	By cardHolderTxb = By.id("brick-cardholder");
	By cardNumberTxb = By.id("brick-card-number");
	By cardExpTxb = By.id("brick-card-expiration");
	By cardCvvTxb = By.id("brick-card-cvv");
	By zipTxb = By.id("brick-zip");
	By emailTxb = By.id("brick-email");
	By payButton = By.xpath("//*[@class='button js-brick-submit']");
	By processButton = By.xpath("//*[contains(@class,'is-active')]//*[@class='button button--100 button-secure']");
	By successButton = By.xpath("//*[@class='button js-brick-submit brick-is-success']");
	
	public Brick_1v5(Driver driver) {
		this.driver = driver;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public void createPayment() {
		setCardHolder();
		setCardNumber();
		setCardExp();
		setCardCvv();
		setZip();
		setEmail();
		clickPayButton();
	}
	
	
	private void setCardHolder() {
		Element element = driver.getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	private void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private void setCardExp() {
		Element element = driver.getElement(cardExpTxb);
		element.sendKeys(exp);
	}
	
	private void setCardCvv() {
		Element element = driver.getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	private void setZip() {
		Element element = driver.getElement(zipTxb);
		element.sendKeys(zip);
	}
	
	private void setEmail() {
		email = "meo"+AnnotationPage.timestamp()+"@spam4.me";
		Element element = driver.getElement(emailTxb);
		element.sendKeys(email);
	}
	
	private void clickPayButton() {
		Element element = driver.getElement(payButton);
		element.click();
	}
	
	public void clickProcessButton() {
		Element element = driver.getElement(processButton);
		element.highlight();
		AnnotationPage.sleep(2000);
		element.clickJS();
	}
	
	public void waitForSuccessButton() {
		driver.getElement(successButton,60000);
	}
}
