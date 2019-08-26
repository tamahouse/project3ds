package automation.project3ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

public class PageBrickHTML {

	static Driver driver = AnnotationPage.getDriver();
	static Element element;
	static String email;

	static By cardholderNameTxb = By.id("brick-cardholder");
	static By cardNumberTxb = By.id("brick-card-number");
	static By expiryDateTxb = By.id("brick-card-expiration");
	static By cvvTxb = By.id("brick-card-cvv");
	static By zipcodeTxb = By.id("brick-zip");
	static By emailTxb = By.id("brick-email");
	static By payBtn = By.xpath("//button[contains(@class, 'button js-brick-submit')]");
	static By refIDForm = By.xpath("//form[@id = '3ds-submission-form']/input[@name='MD']");

	static By processBtn = By.xpath("//button[contains(@class, 'button button--100 button-secure')]");
	static By otpTxb = By.name("challengeDataEntry");
	static By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");
	static By successBtn = By.xpath("//button[@class = 'button js-brick-submit brick-is-success']");

	public static void setCardholderName() {
		element = driver.getElement(cardholderNameTxb);
		element.sendKeys("Payment Wall");
	}

	public static void setCardnumber(String cardNumber) {
		element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}

	public static void setExpiryDate() {
		element = driver.getElement(expiryDateTxb);
		element.sendKeys("0122");
	}

	public static void setCvv() {
		element = driver.getElement(cvvTxb);
		element.sendKeys("123");
	}

	public static void setZipcode() {
		element = driver.getElement(zipcodeTxb);
		element.sendKeys("32043");
	}

	private static String getEmail() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String email = "meo" + timestamp + "@spam4.me";
		return email;
	}

	public static void setEmail() {
		element = driver.getElement(emailTxb);
		email = getEmail();
		element.sendKeys(email);
	}

	public static void clickPayButton() throws Exception {
//		Thread.sleep(10000);
		element = driver.getElement(payBtn);
		element.click();
	}

	public static String getRefId() {
		String str = null;
		if (driver.isExist(refIDForm, 20) == true) {
			element = driver.getElement(refIDForm);
			str = element.getAttribute("value");
			str = str.substring(str.lastIndexOf("=") + 1).substring(1);
		}
		return str;
	}

	private static void clickProcessButton() throws Exception {
		Thread.sleep(2000);
		element = driver.getElement(processBtn);
		element.click();
	}

	public static VisaPurchaseIframe getPurchaseFrame() {
		Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
		driver.switchTo().frame(iframe.getWebElement());
		return new VisaPurchaseIframe(driver);
	}

	public static class VisaPurchaseIframe {
		Driver driver;

		public VisaPurchaseIframe(Driver driver) {
			this.driver = driver;
		}

		private void setOTP() throws Exception {
			Element otpTextbox = driver.getElement(otpTxb);
			otpTextbox.moveToView();
			otpTextbox.sendKeysSlow(30, "1234");
		}

		private void clickOTPSubmitButton() throws Exception {
			Element optSubmitButton = driver.getElement(otpSummitBtn);
			optSubmitButton.highlight();
			optSubmitButton.click();
		}

		private String getVersionCase() throws Exception {
			Map<By, String> map = new HashMap<By, String>();
			map.put(otpTxb, "V2");
			map.put(By.id("authWindow"), "V1");
			return driver.getSelection(map);
		}

		public AuthWindow getAuthWindow() {
			Element iframe = driver.getElement(By.id("authWindow"));
			driver.switchTo().frame(iframe.getWebElement());
			return new AuthWindow(driver);
		}

		public class AuthWindow {
			Driver driver;

			By password = By.id("password");
			By submitBtn = By.xpath("//input[@value = 'Submit']");

			public AuthWindow(Driver driver) {
				this.driver = driver;
			}

			private void setPassword() {
				Element element = driver.getElement(password);
				element.sendKeys("1234");
			}

			private void clickSubmit() {
				Element element = driver.getElement(submitBtn);
				element.click();
			}

		}
	}

	public static AuthWindow getAuthWindow() {
		Element iframe = driver.getElement(By.id("authWindow"));
		driver.switchTo().frame(iframe.getWebElement());
		return new AuthWindow(driver);
	}

	public static class AuthWindow {
		Driver driver;

		By password = By.id("password");
		By submitBtn = By.xpath("//input[@value = 'Submit']");

		public AuthWindow(Driver driver) {
			this.driver = driver;
		}

		private void setPassword() {
			Element element = driver.getElement(password);
			element.sendKeys("1234");
		}

		private void clickSubmit() {
			Element element = driver.getElement(submitBtn);
			element.click();
		}

	}

	private static String getSelectedCase() throws Exception {
		Map<By, String> map = new HashMap<By, String>();
		map.put(successBtn, "PASSBY_THREEDS");
		map.put(By.xpath("//li[@class = 'errors__error']"), "ERROR");
		map.put(By.xpath("//*[@class='brick-step brick-step-3dsecure js-brick-step-3dsecure is-active']"), "PURCHASE");
		return driver.getSelection(map);
	}

	private static String getSelectedCaseAfterOTP() throws Exception {
		Map<By, String> map = new HashMap<By, String>();
		map.put(successBtn, "THREEDS_SUCCESS");
		map.put(By.xpath("//li[@class = 'errors__error']"), "THREEDS_FAILED");
		return driver.getSelection(map);
	}

	public static String returnRefID(String cardNumber) throws Exception {
		setCardnumber(cardNumber);
		setExpiryDate();
		setCardholderName();
		setCvv();
		setZipcode();
		setEmail();
		clickPayButton();

//		refID = this.getRefId();

//		try {
//		ExtentTestManager.getTest().info("refID: " + refID);
//		}catch (Exception ignore) {
//			
//		}

		String caseKey = getSelectedCase();
		if (caseKey.equals("PASSBY_THREEDS")) {
			return "PASSBY_THREEDS";
		} else if (caseKey.equals("PURCHASE")) {
			clickProcessButton();
			Thread.sleep(1000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			if (tabs.size() == 1) {
				VisaPurchaseIframe purchaseFrame = getPurchaseFrame();
				String versionKey = purchaseFrame.getVersionCase();
				if (versionKey.equals("V2")) {
					purchaseFrame.setOTP();
					purchaseFrame.clickOTPSubmitButton();
					driver.switchTo().defaultContent();
				} else if (versionKey.equals("V1")) {
					automation.project3ds.PageBrickHTML.VisaPurchaseIframe.AuthWindow authWindow = purchaseFrame
							.getAuthWindow();
					authWindow.setPassword();
					authWindow.clickSubmit();
					driver.switchTo().defaultContent();
				}
			} else if (tabs.size() == 2) {
				Thread.sleep(3000);
				tabs = new ArrayList<String>(driver.getWindowHandles());
				if (tabs.size() == 2) {
					driver.switchTo().window(tabs.get(1));
					if (driver.isExist(By.id("authWindow"), 2000)) {
						AuthWindow authWindow = getAuthWindow();
						authWindow.setPassword();
						authWindow.clickSubmit();
						driver.switchTo().window(tabs.get(0));
					} else {
						driver.getElement(By.xpath("//input[@value='Submit']")).click();
						driver.switchTo().window(tabs.get(0));
					}
				} else {

				}
			}
			String caseKeyAfterOTP = getSelectedCaseAfterOTP();
			if (caseKeyAfterOTP.equals("THREEDS_SUCCESS")) {
				return "THREEDS_SUCCESS";
			} else if (caseKeyAfterOTP.equals("THREEDS_FAILED")) {
				return "THREEDS_FAILED";
			} else {
				throw new Exception("After OTP case is null");
			}

		} else if (caseKey.equals("ERROR")) {
			return "ERROR";
		} else {
			throw new Exception("OTP case is null");
		}
//		
//		
//		System.out.println(cardNumber + " : " + refID);
//		return refID;
	}
}
