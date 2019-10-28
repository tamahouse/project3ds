package automation.project3ds;

import org.openqa.selenium.By;

public class PS_Pagseguro {
	
	Driver driver;
	
	String timestamp = AnnotationPage.timestamp();
	String name1 = "Payment ";
	String name2 = "Payment Wall";
	public String email = "meo"+timestamp+"@sandbox.pagseguro.com.br";
	
	By nameTxb = By.id("name");
	By emailTxb = By.id("email");
	By continueBtn = By.id("paybutton");

	public PS_Pagseguro(Driver driver) {
		this.driver = driver;
	}
	
	public PS_Pagseguro2 createPayment() {
		setName();
		setEmail();
		clickContinueButton();
		return new PS_Pagseguro2(driver);
	}


	public String getEmail() {
		return email;
	}
	
	public void setName() {
		Element element = driver.getElement(nameTxb);
		element.sendKeys("preset");
		AnnotationPage.sleep(5000);
		element.clear();
		element.sendKeys(name1);
		AnnotationPage.sleep(1000);
		element.sendKeys(name2);
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
