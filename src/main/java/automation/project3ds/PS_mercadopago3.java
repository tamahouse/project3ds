package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetMainFrame.WidgetType;

public class PS_mercadopago3 {
	
	Driver driver;
	public static String EMAIL = "test_user_82315550@testuser.com";
	
	String redirectUrl = "mercadolivre.com";
	String email = EMAIL;
	String password = "qatest7910";
	
	By emailTxb = By.id("user_id");
	By continueBtn = By.xpath("//*[@value='Continuar']");
	By passwordTxb = By.id("password");
	By completeBtn = By.xpath("//*[@value='complete']");

	public PS_mercadopago3(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public PS_mercadopago2 createPayment_AccountOption() {
		this.setUserID();
		this.clickContinueButton();
		this.setPassword();
		this.clickCompletedButton();
		driver.switchToWindows("test-offerwall",true, 30000);
		WidgetPage widgetPage = new WidgetPage(driver);
		WidgetMulti widgetMulti = (WidgetMulti) widgetPage.getWidgetMainFrame(WidgetType.MULTI);
		PS_mercadopago2 ps2 = (PS_mercadopago2) widgetMulti.getPS(PS_shortcode.MERCADOPAGO2);
		return ps2;
	}
	
	private void setUserID() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(email);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		element.sendKeys(password);
	}
	
	private void clickCompletedButton() {
		Element element = driver.getElement(completeBtn);
		element.click();
	}
	
}
