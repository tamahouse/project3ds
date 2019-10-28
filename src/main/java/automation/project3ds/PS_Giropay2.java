package automation.project3ds;

import org.openqa.selenium.By;

public class PS_Giropay2 {
	
	Driver driver;
	
	String redirectUrl = "ftg-customer-integration.giropay.de";
	String userId = "chiptanscatest2";
	String pin = "12345";
	String tan = "123456";
	
	By account_anmeldename = By.xpath("//*[@name=\"account/addition[@name=benutzerkennung]\"]");
	By pinTxb = By.xpath("//*[@name=\"ticket/pin\"]");
	By jetztButton = By.xpath("//*[@title='Jetzt bezahlen']");
	By jetztButton2 = By.xpath("//*[@value='Jetzt bezahlen']");
	By weiterButton = By.name("weiterButton");
	By tanTxb = By.xpath("//*[@name=\"ticket/tan\"]");
	By loginButton = By.xpath("//input[@title='authentifizieren']");
	By giropaybeendenButton = By.xpath("//*[@value='giropay beenden + zurück zum Shop']");
	

	public PS_Giropay2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage createPayment() {
		this.login();
		this.selectTanOption();
		this.submitTan();
		this.submitDetail();
		this.submitTan2();
		this.finalSubmit();
		return new ThankyouPage(driver);
	}
	
	private void login() {
		this.setUserID_Anmeldename();
		this.setPin();
		this.clickJetztButton();
	}
	
	private void selectTanOption() {
		this.clickWeiterButton();
	}
	
	private void submitTan() {
		this.setTan();
		this.clickLoginButton();
	}
	
	private void submitDetail() {
		this.clickWeiterButton();
	}
	
	private void submitTan2() {
		this.setTan();
		this.clickJetztButton2();
	}
	
	private void finalSubmit() {
		this.clickGiropayBendenButton();
	}

	
	private void setUserID_Anmeldename() {
		Element element = driver.getElement(account_anmeldename);
		element.sendKeys(userId);
	}
	
	private void setPin() {
		Element element = driver.getElement(pinTxb);
		element.sendKeys(pin);
	}
	
	private void clickJetztButton() {
		Element element = driver.getElement(jetztButton);
		element.click();
	}
	
	private void clickWeiterButton() {
		Element element = driver.getElement(weiterButton);
		element.moveToTopView();
		element.click();
	}
	
	private void clickJetztButton2() {
		Element element = driver.getElement(jetztButton2);
		element.click();
	}
	
	private void setTan() {
		Element element = driver.getElement(tanTxb);
		element.moveToTopView();
		element.sendKeys(tan);
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginButton);
		element.click();
	}
	
	private void clickGiropayBendenButton() {
		Element element = driver.getElement(giropaybeendenButton);
		element.moveToTopView();
		element.click();
	}

}
