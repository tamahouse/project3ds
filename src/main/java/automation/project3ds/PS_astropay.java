package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_astropay {
	
	Driver driver;

	By cpf = By.id("x_cpf");
	By name = By.id("x_name");
	By email = By.id("x_email");
	By payment_button = By.id("payment_button");
	By bank = By.xpath("//*[@class='payment-method']");
	By error = By.xpath("//*[@id='error_message' and contains(@style,'block')]");
	
	String CPF = "43622652101";
	String NAME = "Leonor S Alves";
	String EMAIL;

	public PS_astropay(Driver driver) {
		this.driver = driver;
	}

	public void set_cpf(String str) {
		Element element = driver.getElement(cpf);
		element.sendKeys(str);
	}

	public void set_name(String str) {
		Element element = driver.getElement(name);
		driver.sleep(1000);
		element.sendKeys(str);
	}


	public void set_email(String str) {
		Element element = driver.getElement(email);
		element.sendKeys(str);
	}


	public void set_cpf() {
		set_cpf(CPF);
	}


	public void set_name() {
		set_name(NAME);
	}


	public void set_email() {
		String timestamp = AnnotationPage.timestamp();
		EMAIL = "meo"+timestamp+"@spam4.me";
		set_email(EMAIL);
	}
	
	public String getEmail() {
		return this.EMAIL;
	}

	public void clickSummit() {
		Element element = driver.getElement(payment_button);
		element.click();
	}

	public void waitForError() {
		 driver.getElement(error);
	}

	public void createPayment() throws Exception {
		this.set_name();
		this.set_cpf();
		this.set_email();
		this.clickSummit();
		this.waitForError();
	}
	
	public void clickBank() {
		Element element = driver.getElement(bank);
		driver.sleep(1000);
		element.click();
	}
}

