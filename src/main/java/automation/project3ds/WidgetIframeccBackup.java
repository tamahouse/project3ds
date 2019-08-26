package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;


public class WidgetIframeccBackup {
	
	static String email;

//	By img = By.xpath("//*[@data-card-name = 'mastercard' ]");
	private static By cardHolderNameTxb = By.id("brick-cardholder");
//	private static By cardNumberContainer = By.id("brick-card-number");
	private static By cardNumberContainer = By.id("input");
	private static By expTxb = By.id("brick-card-expiration");
	private static By cvvTxb = By.id("brick-card-cvv");
	private static By zipTxb = By.id("brick-zip");
	private static By emailTxb = By.id("brick-email");
	private static By buyBtn = By.xpath("//*[contains(@class,'is-active')]//*[contains(@class,'js-brick-submit')]");
	private static By processBtn = By.xpath("//*[@class='button button--100 button-secure']");
	private static By refIDContainer = By.id("brick-payment-form");
	private static By useDifferentCard = By.xpath("//a[@data-phrase = 'use-different-card']");


	public static Driver getFrame() {
		Driver driver = AnnotationPage.getDriver();
		driver.switchTo().defaultContent();
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		driver.switchTo().frame(iframe.getWebElement());
		iframe = driver.getElement(By.id("iframecc"));
		driver.switchTo().frame(iframe.getWebElement());
		return driver;
	}
	
	public static void clickUserDifferentCard() {
		Element element = getFrame().getElement(useDifferentCard);
		element.click();
	}
	
	public static void createPayment(String cardNumber) throws Exception {
		setCardNumber(cardNumber);
		setExpiredDate();
		setCardHolderName();
		setCVV();
		setZipCode();
		setEmail();
		clickBuyButton();
	}

	private static void setCardHolderName() throws Exception {
		Element cardHolderNameTextbox = getFrame().getElement(cardHolderNameTxb);
		cardHolderNameTextbox.sendKeys("Payment Wall");
//		
	}

	public static void setCardNumber(String cardNumber) throws Exception {
		Element container = getFrame().getElement(cardNumberContainer);
		container.sendKeys(cardNumber);
		getFrame().getElement(By.xpath("//label[@for='brick-card-number']")).click();
		Thread.sleep(2000);
	}

	private static void setExpiredDate() throws Exception {
		Thread.sleep(1000);
		Element expiredTextbox = getFrame().getElement(expTxb);
		expiredTextbox.click();
		expiredTextbox.sendKeys("0122");
//		this.clickImage();
	}

	private static void setCVV() throws Exception {
		Element cvvTextbox = getFrame().getElement(cvvTxb);
		cvvTextbox.sendKeys("123");
//		this.clickImage();
	}

	private static void setZipCode() throws Exception {
		Element zipTextbox = getFrame().getElement(zipTxb);
		zipTextbox.sendKeys("32043");
//		this.clickImage();
	}
	
	private static String generateEmail() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String email = "meo"+timestamp+"@spam4.me";
		return email;
	}

	private static void setEmail() {
		Element element = getFrame().getElement(emailTxb);
		email = generateEmail();
		element.sendKeys(email);
	}
	
	private static String getEmail() {
		return email;
	}

	public static void clickBuyButton() throws Exception {
//		Thread.sleep(5000);
		Element buyButton = getFrame().getElement(buyBtn);
		buyButton.moveToTopView();
		buyButton.click();

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

	public static String getSelectedCase() throws Exception {
		Thread.sleep(3000);
		Map<By, String> map = new HashMap<By, String>();
		map.put(By.xpath("//*[@class='brick-step brick-step-3dsecure js-brick-step-3dsecure is-active']"),
				"PURCHASE");
		map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
				"ERROR");
		map.put(By.xpath("//*[@class='button js-brick-submit brick-is-success']"), "SUCCESS");
		return getFrame().getSelection(map);
	}

	private static String getRedirectCase() throws Exception {
		Map<By, String> map = new HashMap<By, String>();
		map.put(By.id("Cardinal-CCA-IFrame"), "OTP");
		map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
				"ERROR");
		return getFrame().getSelection(map);
	}

	private static String getCompleteCase() throws Exception {
		Map<By, String> map = new HashMap<By, String>();
		map.put(By.xpath("//*[@class='brick-submit__success']"), "PURCHASE");
		map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
				"ERROR");
		return getFrame().getSelection(map);
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
