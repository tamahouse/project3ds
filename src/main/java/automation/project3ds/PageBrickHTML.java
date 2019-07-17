package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

public class PageBrickHTML {

	Driver driver;
	Element element;

	By cardholderNameTxb = By.id("brick-cardholder");
	By cardNumberTxb = By.id("brick-card-number");
	By expiryDateTxb = By.id("brick-card-expiration");
	By cvvTxb = By.id("brick-card-cvv");
	By zipcodeTxb = By.id("brick-zip");
	By emailTxb = By.id("brick-email");
	By payBtn = By.xpath("//button[contains(@class, 'button js-brick-submit')]");
	By refIDForm = By.xpath("//form[@id = '3ds-submission-form']/input[@name='MD']");

	By processBtn = By.xpath("//button[contains(@class, 'button button--100 button-secure')]");
	By otpTxb = By.name("challengeDataEntry");
	By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");
	By successBtn = By.xpath("//button[@class = 'button js-brick-submit brick-is-success']");

	public PageBrickHTML(Driver driver) {
		this.driver = driver;
	}

	public void setCardholderName() {
		element = driver.getElement(cardholderNameTxb);
		element.sendKeys("Payment Wall");
	}

	public void setCardnumber(String cardNumber) {
		element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}

	public void setExpiryDate() {
		element = driver.getElement(expiryDateTxb);
		element.sendKeys("0122");
	}

	public void setCvv() {
		element = driver.getElement(cvvTxb);
		element.sendKeys("123");
	}

	public void setZipcode() {
		element = driver.getElement(zipcodeTxb);
		element.sendKeys("32043");
	}

	public void setEmail() {
		element = driver.getElement(emailTxb);
		element.sendKeys("meo@spam4.me");
	}

	public void clickPayButton() throws Exception {
//		Thread.sleep(10000);
		element = driver.getElement(payBtn);
		element.click();
	}

	public String getRefId() {
		String str = null;
		if (driver.checkExist(refIDForm, 20) == true) {
			element = driver.getElement(refIDForm);
			str = element.getAttribute("value");
			str = str.substring(str.lastIndexOf("=") + 1).substring(1);
		}
		return str;
	}

	private void clickProcessButton() {

		element = driver.getElement(processBtn);
		element.click();
	}

	public VisaPurchaseIframe getPurchaseFrame() {
		Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
		driver.switchTo().frame(iframe);
		return new VisaPurchaseIframe(driver);
	}

	public class VisaPurchaseIframe {
		Driver driver;

		public VisaPurchaseIframe(Driver driver) {
			this.driver = driver;
		}

		private void setOTP() throws Exception {
			Element otpTextbox = driver.getElement(otpTxb);
			otpTextbox.moveToView();
			otpTextbox.sendKeys("1234");
		}

		private void clickOTPSubmitButton() throws Exception {
			Element optSubmitButton = driver.getElement(otpSummitBtn);
			optSubmitButton.highlight();
			optSubmitButton.clickJS();
		}
	}

	private String getSelectedCase() throws Exception {
		Map<By, String> map = new HashMap<By, String>();
		map.put(successBtn, "PASSBY_THREEDS");
		map.put(By.xpath("//li[@class = 'errors__error']"), "ERROR");
		map.put(By.xpath("//*[@class='brick-step brick-step-3dsecure js-brick-step-3dsecure is-active']"),
				"OTP");

		Boolean x = false;
		int count = 0;
		while (x == false) {
			for (Map.Entry<By, String> entry : map.entrySet()) {
				if (driver.isInstanceExist(entry.getKey()) == true) {
					String str = entry.getValue();
					System.out.println(str);
					return str;
				}
			}
			Thread.sleep(100);
			if (++count > 100) {
				return null;
			}
		}
		return null;
	}

	private String getSelectedCaseAfterOTP() throws Exception {
		Map<By, String> map = new HashMap<By, String>();
		map.put(successBtn, "THREEDS_SUCCESS");
		map.put(By.xpath("//li[@class = 'errors__error']"), "THREEDS_FAILED");

		Boolean x = false;
		int count = 0;
		while (x == false) {
			for (Map.Entry<By, String> entry : map.entrySet()) {
				if (driver.isInstanceExist(entry.getKey()) == true) {
					String str =  entry.getValue();
					System.out.println(str);
					return str;
				}
			}
			Thread.sleep(100);
			if (++count > 100) {
				return null;
			}
		}
		return null;
	}

	public String returnRefID(String cardNumber) throws Exception {
		this.setCardnumber(cardNumber);
		this.setExpiryDate();
		this.setCardholderName();
		this.setCvv();
		this.setZipcode();
		this.setEmail();
		this.clickPayButton();

//		refID = this.getRefId();

//		try {
//		ExtentTestManager.getTest().info("refID: " + refID);
//		}catch (Exception ignore) {
//			
//		}

		String caseKey = this.getSelectedCase();
		if (caseKey.equals("PASSBY_THREEDS")) {
				return "PASSBY_THREEDS";
		} else if (caseKey.equals("OTP")) {
			this.clickProcessButton();
			VisaPurchaseIframe purchaseFrame = this.getPurchaseFrame();
			purchaseFrame.setOTP();
			purchaseFrame.clickOTPSubmitButton();
			String caseKeyAfterOTP = this.getSelectedCaseAfterOTP();
			if (caseKeyAfterOTP.equals("THREEDS_SUCCESS")) {
				return "THREEDS_SUCCESS";
			} else if (caseKeyAfterOTP.equals("THREEDS_FAILED")) {
				return "THREEDS_FAILED";
			}else {
				throw new Exception ("After OTP case is null");
			}
		} else if (caseKey.equals("ERROR")) {
			return "ERROR";
		}else {
			throw new Exception ("OTP case is null");
		}
//		
//		
//		System.out.println(cardNumber + " : " + refID);
//		return refID;
	}
}
