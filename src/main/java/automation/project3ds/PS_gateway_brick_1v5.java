package automation.project3ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class PS_gateway_brick_1v5 {
	
	
	static String email;
	static String name = "Payment Wall";
	public static String cardNumber = "4000000000000002";
	static String exp = "0122";
	static String cvv = "123";
	static String zip = "32043";

	static By id = By.id("brick");
	static By cardHolderTxb = By.id("brick-cardholder");
	static By cardNumberTxb = By.id("brick-card-number");
	static By cardExpTxb = By.id("brick-card-expiration");
	static By cardCvvTxb = By.id("brick-card-cvv");
	static By zipTxb = By.id("brick-zip");
	static By emailTxb = By.id("brick-email");
	static By payButton = By.xpath("//*[@class='button js-brick-submit']");
	static By processButton = By.xpath("//*[contains(@class,'is-active')]//*[@class='button button--100 button-secure']");
	
	public static Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		return driver;
	}
	
	public static void createPayment() {
		setCardHolder();
		setCardNumber();
		setCardExp();
		setCardCvv();
		setZip();
		setEmail();
		clickPayButton();
	}
	
	public static void finish3dsPolkOFF() {
		Driver driver = getFrame();
		getFrame().switchToWindows(containsInUrl, true);
		Widget3dsPolk obj = new Widget3dsPolk(driver);
		obj.clickSubmit();
		getFrame().switchToWindows("test-offerwall", true);
	}
	
	public static void finish3dsV1OFF() {
		Driver driver = getFrame();
		getFrame().switchToWindows("cardinalcommerce", true);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
		getFrame().switchToWindows("test-offerwall", true);
	}
	
	public static void finish3dsV1ON() {
		Driver driver = getFrame();
		WebElement iframe = getFrame().getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
	}
	
	public static void finish3dsV2ON() {
		Driver driver = getFrame();
		WebElement iframe = getFrame().getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV2 obj = new Widget3dsV2(driver);
		obj.finish();
	}
	
	private static void setCardHolder() {
		AnnotationPage.sleep(5000);
		Element element = getFrame().getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	private static void setCardNumber() {
		Element element = getFrame().getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private static void setCardExp() {
		Element element = getFrame().getElement(cardExpTxb);
		element.sendKeys(exp);
	}
	
	private static void setCardCvv() {
		Element element = getFrame().getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	private static void setZip() {
		Element element = getFrame().getElement(zipTxb);
		element.sendKeys(zip);
	}
	
	private static void setEmail() {
		email = "meo"+AnnotationPage.timestamp()+"@spam4.me";
		Element element = getFrame().getElement(emailTxb);
		element.sendKeys(email);
	}
	
	private static void clickPayButton() {
		Element element = getFrame().getElement(payButton);
		element.click();
	}
	
	public static void clickProcessButton() {
		Element element = getFrame().getElement(processButton);
		AnnotationPage.sleep(2000);
		element.click();
	}
}
