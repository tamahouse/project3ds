package automation.project3ds;

import org.openqa.selenium.By;


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
	By error = By.xpath("//*[@class='errors js-brick-errors is-errors']");
	
	public Brick_1v5(Driver driver) {
		this.driver = driver;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getEmail() {
		System.out.println(this.email);
		return this.email;
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
	
	public void waitForError() {
		driver.getElement(error,60000);
	}
	
	private void setCardHolder() {
		Element element = driver.getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	public void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	public void setCardExp() {
		Element element = driver.getElement(cardExpTxb);
		element.sendKeys(exp);
	}
	
	public void setCardCvv() {
		Element element = driver.getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	private void setZip() {
		Element element = driver.getElement(zipTxb);
		element.sendKeys(zip);
	}
	
	public void setEmail() {
		email = "meo"+AnnotationPage.timestamp()+"@spam4.me";
		System.out.println(this.email);
		Element element = driver.getElement(emailTxb);
		String cEmail = element.getAttribute("value");
		if(cEmail.equals("")) {
		element.sendKeys(email);
		}
	}
	
	public void clickPayButton() {
		Element element = driver.getElement(payButton);
		element.moveToTopView();
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
