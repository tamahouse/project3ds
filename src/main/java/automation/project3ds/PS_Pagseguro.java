package automation.project3ds;

import org.openqa.selenium.By;

public class PS_Pagseguro {
	
	static String timestamp = AnnotationPage.timestamp();
	static String name1 = "Payment ";
	static String name2 = "Wall";
	public static String email = "meo"+timestamp+"@sandbox.pagseguro.com.br";
	
	static By nameTxb = By.id("name");
	static By emailTxb = By.id("email");
	static By continueBtn = By.id("paybutton");

	public static Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		driver.getElement(By.id("pagseguro"));
		return driver;
	}
	
	public static void createPayment() {
		setName();
		setEmail();
		clickContinueButton();
	}
	
	public static void setName() {
		Element element = getFrame().getElement(nameTxb);
		element.sendKeys("preset");
		AnnotationPage.sleep(5000);
		element.clear();
		element.sendKeys(name1);
		AnnotationPage.sleep(1000);
		element.sendKeys(name2);
	}
	
	public static void setEmail() {
		String timestamp = AnnotationPage.timestamp();
		email = "meo"+timestamp+"@sandbox.pagseguro.com.br";
		Element element = getFrame().getElement(emailTxb);
		element.sendKeys(email);
	}
	
	public static void clickContinueButton() {
		Element element = getFrame().getElement(continueBtn);
		element.click();
	}
	
}
