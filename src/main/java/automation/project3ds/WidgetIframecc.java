package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class WidgetIframecc {
	
	static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("name", "Payment Wall");
		map.put("cardNumber", "4000000000000002");
		map.put("expDate", "0122");
		map.put("cvv", "123");
		map.put("zip", "32043");
		map.put("email","null");
	}
	
	static String email;
	static String name = "Payment Wall";
	static String cardNumber = "4000000000000002";
	static String expDate = "0122";
	static String cvv = "123";
	static String zip = "32043";

//	By img = By.xpath("//*[@data-card-name = 'mastercard' ]");
	private static By cardHolderInput = By.id("brick-cardholder");
//	private static By cardNumberContainer = By.id("brick-card-number");
	private static By cardNumberInput = By.id("input");
	private static By expTxb = By.id("brick-card-expiration");
	private static By buyBtn = By.xpath("//*[contains(@class,'is-active')]//*[contains(@class,'js-brick-submit')]");
	private static By processBtn = By.xpath("//*[contains(@class,'is-active')]/div/button/*[contains(text(),'Proceed to Bank')]");
	private static By refIDContainer = By.id("brick-payment-form");
	private static By useDifferentCard = By.xpath("//*[contains(@class,'js-brick-step-cards is-active')]//a[@data-phrase = 'use-different-card']");
	private static By backToStoredCard = By.xpath("//*[contains(@class,'js-brick-step-form is-active')]//a[@data-phrase='back-to-stored-cards']");
	private static By tryAgain = By.xpath("//*[contains(@class,'js-brick-step-3dsecure is-active')]//a[@data-phrase = 'Try again']");
	private static By zipInput = By.id("brick-zip");
	private static By emailInput = By.id("brick-email");
	private static By thankyou = By.xpath("//h3[text()='Thank you for your purchase!']");
	private static By paymentForm = By.id("brick-payment-form");
	private static By terms = By.xpath("//*[contains(@class,'brick-terms')]//a");
	
	private static By formError = By.xpath("//*[@class='brick-form-errors has-errors'][./following-sibling::*[./*[contains(@class,'brick-step-form') and contains(@class,'is-active')]]]");
	
	public static By white = By.xpath("//div[contains(@class, 'is-active')]//*[@data-phrase='by-clicking-button-you-agree']");
	public static By cardHolderTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Name on card')]]");
	public static By cardNumberTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Card number')]]");
	public static By expirationDateTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Expiration date')]]");
	public static By cvvTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'CVV') or contains(text(),'CID')]]");
	public static By zipTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'ZIP')]]");
	public static By emailTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Email')]]");
	
	public static Driver getFrame() {
		Driver driver = AnnotationPage.getDriver();
		driver.switchTo().defaultContent();
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		driver.switchTo().frame(iframe.getWebElement());
		iframe = driver.getElement(By.id("iframecc"));
		driver.switchTo().frame(iframe.getWebElement());
		return driver;
	}
	
	public static WidgetIframecc replace(String key, String value) {
		map.put(key, value);
		return new WidgetIframecc();
	}
	
	public void create() throws Exception {
		String name = map.get("name");
		String cardNumber = map.get("cardNumber");
		String expDate = map.get("expDate");
		String cvv = map.get("cvv");
		String zip = map.get("zip");
		String email = map.get("email");
		if(email.equals("null")) {
			email = generateEmail();
		}
		setCardHolder(name);
		setCardNumber(cardNumber);
		setExpirationDate(expDate);
		setCVV(cvv);
		setZipCode(zip);
		setEmail(email);
		clickBuyButton();
		map = new HashMap<String, String>();
		map.put("name", "Payment Wall");
		map.put("cardNumber", "4000000000000002");
		map.put("expDate", "0122");
		map.put("cvv", "123");
		map.put("zip", "32043");
		map.put("email","null");
	}
	
	public static Element getFormError() throws Exception {
		Element element = getFrame().getElement(formError);
		Thread.sleep(500);
		return element;
	}
	
	public static Element getCardHolderTextbox() {
		return getFrame().getElement(cardHolderTxb);
	}
	
	public static Element getCardNumberTextbox() {
		return getFrame().getElement(cardNumberTxb);
	}
	
	public static Element getExpirationDateTextbox() {
		return getFrame().getElement(expirationDateTxb);
	}
	
	public static Element getCvvTextbox() {
		return getFrame().getElement(cvvTxb);
	}
	
	public static Element getZipTextbox() {
		return getFrame().getElement(zipTxb);
		
	}
	
	public static Element getEmailTextbox() throws Exception {
		Element element =  getFrame().getElement(emailTxb);
		Thread.sleep(500);
		return element;
	}
	
	
	public static void clickUserDifferentCard() {
		Element element = getFrame().getElement(useDifferentCard);
		element.click();
	}
	
	public static void clickBackToStoredCard() {
		Element element = getFrame().getElement(backToStoredCard);
		element.click();
	}
	
	public static void clickTryAgain() {
		Element element = getFrame().getElement(tryAgain);
		element.click();
	}
	
	public static void clickTerms() {
		Element element = getFrame().getElement(terms);
		element.click();
	}

	
	public static void setCardHolder(String name) throws Exception {
		Element cardHolderNameTextbox = getFrame().getElement(cardHolderInput);
		cardHolderNameTextbox.sendKeys(name);
//		
	}
	
	public static Element getActiveBrandIcon() {
		return getFrame().getElement(By.xpath("//*[@class='brick-icon-cc-container']/*[contains(@class,'active')]"));
	}
	
	private static Driver getDeepIframe(Element cover) {
		WebElement frame = cover.getElement(By.tagName("iframe")).getWebElement();
		Driver driver = AnnotationPage.getDriver();
		driver.switchTo().frame(frame);
		return driver;
	}

	public static void setCardNumber(String cardNumber) throws Exception {
		Element cover = getCardNumberTextbox();
		Driver driver = getDeepIframe(cover);
		Element container = driver.getElement(cardNumberInput);
		container.sendKeys(cardNumber);
		getFrame().getElement(white).click();
		Thread.sleep(2000);
	}
	
	
	public static void setCVV(String cvv) throws Exception {
		Element cover = getCvvTextbox();
		Driver driver = getDeepIframe(cover);
		Element container = driver.getElement(cardNumberInput);
		container.sendKeys(cvv);
		getFrame().getElement(white).click();
	}
	
	
	public static void setExpirationDate(String exp) throws Exception {
		Element cover = getExpirationDateTextbox();
		Driver driver = getDeepIframe(cover);
		Element container = driver.getElement(cardNumberInput);
		container.sendKeys(exp);
		getFrame().getElement(white).click();
		Thread.sleep(500);
	}

	
	public static void setZipCode(String zip) throws Exception {
		Element zipTextbox = getFrame().getElement(zipInput);
		zipTextbox.sendKeys(zip);
//		this.clickImage();
	}
	
	private static String generateEmail() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String email = "meo"+timestamp+"@spam4.me";
		return email;
	}

	
	private static void setEmail(String email) {
		Element element = getFrame().getElement(emailInput);
		element.sendKeys(email);
	}
	


	public static void clickBuyButton() throws Exception {
		Element buyButton = getFrame().getElement(buyBtn);
		buyButton.click();
		Thread.sleep(500);
	}

	public static void clickProcessButton() throws Exception {
		Thread.sleep(1000);
		Element processButton = getFrame().getElement(processBtn);
		processButton.click();
	}

	public static String getRefId() {
		Element refIdContainer = getFrame().getElement(refIDContainer);
		String str = refIdContainer.getAttribute("action");
		str = str.substring(str.lastIndexOf("=") + 1).substring(1);
		return str;
	}

	public static Boolean getSuccess() {
		return getFrame().isExist(thankyou);
	}

//	public static PurchaseIframe getPurchaseFrame() {
//		Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
//		driver.switchTo().frame(iframe.getWebElement());
//		return new PurchaseIframe(driver);
//	}

//	public static class PurchaseIframe {
//		Driver driver;
//
//		public static PurchaseIframe(Driver driver) {
//			this.driver = driver;
//		}
//
//		private static void setOTP() throws Exception {
//			Element otpTextbox = driver.getElement(otpTxb);
//			otpTextbox.moveToTopView();
//			otpTextbox.sendKeysSlow(80, "1234");
//		}
//
//		private static void clickOTPSubmitButton() throws Exception {
//			Element optSubmitButton = driver.getElement(otpSummitBtn);
//			optSubmitButton.highlight();
//			optSubmitButton.click();
//		}
//
//		private static WidgetIframecc getIframecc() {
//			driver.switchTo().defaultContent();
//			Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
//			driver.switchTo().frame(iframe.getWebElement());
//			Element iframe2 = driver.getElement(By.id("iframecc"));
//			driver.switchTo().frame(iframe2.getWebElement());
//			return new WidgetIframecc(driver);
//		}
//
//		private static String getVersionCase() throws Exception {
//			Map<By, String> map = new HashMap<By, String>();
//			map.put(otpTxb, "V2");
//			map.put(By.id("authWindow"), "V1");
//			return driver.getSelection(map);
//		}
//
//		public static AuthWindow getAuthWindow() {
//			Element iframe = driver.getElement(By.id("authWindow"));
//			driver.switchTo().frame(iframe.getWebElement());
//			return new AuthWindow(driver);
//		}
//
//		public static class AuthWindow {
//			Driver driver;
//
//			By password = By.id("password");
//			By submitBtn = By.xpath("//input[@value = 'Submit']");
//
//			public static AuthWindow(Driver driver) {
//				this.driver = driver;
//			}
//
//			private static void setPassword() {
//				Element element = driver.getElement(password);
//				element.sendKeys("1234");
//			}
//
//			private static void clickSubmit() {
//				Element element = driver.getElement(submitBtn);
//				element.click();
//			}
//
//			private static WidgetIframecc getIframecc() {
//				driver.switchTo().defaultContent();
//				Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
//				driver.switchTo().frame(iframe.getWebElement());
//				Element iframe2 = driver.getElement(By.id("iframecc"));
//				driver.switchTo().frame(iframe2.getWebElement());
//				return new WidgetIframecc(driver);
//			}
//		}
//	}

}
