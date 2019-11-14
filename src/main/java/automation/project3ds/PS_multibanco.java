package automation.project3ds;

import org.openqa.selenium.By;

public class PS_multibanco {
	
	Driver driver;
	
	String timestamp = AnnotationPage.timestamp();
	String name = "Payment Wall";
	public String email;
	
	By nameTxb = By.id("multibanco_fullname");
	By emailTxb = By.id("multibanco_email");
	By continueBtn = By.id("submitButton");

	public PS_multibanco(Driver driver) {
		this.driver = driver;
	}
	
	public PS_ppro createPayment() {
		setName();
		setEmail();
		clickContinueButton();
		return new PS_ppro(driver);
	}


	public String getEmail() {
		return email;
	}
	
	public void setName() {
		Element element = driver.getElement(nameTxb);
		AnnotationPage.sleep(1000);
		element.sendKeys(name);
	}
	
	public void setEmail() {
		String timestamp = AnnotationPage.timestamp();
		email = "meo"+timestamp+"@sandbox.pagseguro.com.br";
		Element element = driver.getElement(emailTxb);
		element.sendKeys(email);
	}
	
	public void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
}
