package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import automation.project3ds.PageHome1108.MainIframe.Iframecc;
import automation.project3ds.PageHome1108.MainIframe.Iframecc.PurchaseIframe;

public class PageHome1108 {

	Driver driver;

	By refIDContainer = By.id("brick-payment-form");
	By otpTxb = By.name("challengeDataEntry");
	By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");

	public PageHome1108(Driver driver) {
		this.driver = driver;
	}

	public MainIframe getMainIframe() {
		Element iframe = driver.findElement(By.xpath("//*[@id=\"main\"]/iframe"));
		driver.switchTo().frame(iframe);
		return new MainIframe(driver);
	}

	public class MainIframe {
		Driver driver;

		By buyBtn = By.id("ps_psb");

		public MainIframe(Driver driver) {
			this.driver = driver;
		}

		public void clickBuyButton() throws Exception {
			driver.getElement(this.buyBtn).click();
			Thread.sleep(3000);
		}

		public Iframecc getIframecc() throws Exception {
			driver.checkExist(By.tagName("iframe"), 100);
			Element newIframe = driver.getElement(By.tagName("iframe"));
			driver.switchTo().frame(newIframe);
			return new Iframecc(driver);
		}

		public class Iframecc {

			Driver driver;
//			By img = By.xpath("//*[@data-card-name = 'mastercard' ]");
			By cardHolderNameTxb = By.id("brick-cardholder");
			By cardNumberContainer = By.id("brick-card-number");
			By expTxb = By.id("brick-card-expiration");
			By cvvTxb = By.id("brick-card-cvv");
			By zipTxb = By.id("brick-zip");
			By emailTxb = By.id("brick-email");
			By buyBtn = By.xpath("//*[@class='button js-brick-submit']");
			By processBtn = By.xpath("//*[@class='button button--100 button-secure']");

			public Iframecc(Driver driver) {
				this.driver = driver;
			}
			
//			private void clickImage() {
//				Element image = driver.getElement(img);
//				image.click();
//			}

			private void setCardHolderName() {
				Element cardHolderNameTextbox = driver.getElement(cardHolderNameTxb);
				cardHolderNameTextbox.sendKeys("Payment Wall");
//				this.clickImage();
			}

			public void setCardNumber(String cardNumber) throws Exception {
				Element container = driver.getElement(cardNumberContainer);
				container.sendKeysSlow(cardNumber);
			}

			private void setExpiredDate() throws Exception {
				Element expiredTextbox = driver.getElement(expTxb);
				expiredTextbox.sendKeysSlow("0122");
//				this.clickImage();
			}

			private void setCVV() throws Exception {
				Element cvvTextbox = driver.getElement(cvvTxb);
				cvvTextbox.sendKeysSlow("123");
//				this.clickImage();
			}

			private void setZipCode() throws Exception {
				Element zipTextbox = driver.getElement(zipTxb);
				zipTextbox.sendKeysSlow("32043");
//				this.clickImage();
			}

			private void setEmail() throws Exception {
				Element emailTextbox = driver.getElement(emailTxb);
				emailTextbox.sendKeysSlow("meo@spam4.me");
//				this.clickImage();
			}

			private void clickBuyButton() throws Exception {
				Thread.sleep(3000);
				Element buyButton = driver.getElement(buyBtn);
				buyButton.moveToView();
				buyButton.click();
				
			}

			private void clickProcessButton() {
				
				Element processButton = driver.getElement(processBtn);
				processButton.click();
			}

			private String getRefId() {
				Element refIdContainer = driver.findElement(refIDContainer);
				String str = refIdContainer.getAttribute("action");
				str = str.substring(str.lastIndexOf("=") + 1).substring(1);
				return str;
			}

			private String getSelectedCase() throws Exception {
				Thread.sleep(3000);
				Map<By, String> map = new HashMap<By, String>();
				map.put(By.xpath("//*[@class='brick-step brick-step-3dsecure js-brick-step-3dsecure is-active']"),
						"PURCHASE");
				map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
						"ERROR");
				map.put(By.xpath("//*[@class='button js-brick-submit brick-is-success']"),"SUCCESS");
				return driver.getSelectedCase(map);
			}

			private String getRedirectCase() throws Exception {
				Map<By, String> map = new HashMap<By, String>();
				map.put(By.id("Cardinal-CCA-IFrame"), "PURCHASE");
				map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
						"ERROR");
				return driver.getSelectedCase(map);
			}

			private String getCompleteCase() throws Exception {
				Map<By, String> map = new HashMap<By, String>();
				map.put(By.xpath("//*[@class='brick-submit__success']"), "PURCHASE");
				map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
						"ERROR");
				return driver.getSelectedCase(map);
			}

			public class PurchaseIframe {
				Driver driver;

				public PurchaseIframe(Driver driver) {
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

				private Iframecc getIframecc() {
					driver.switchTo().defaultContent();
					Element iframe = driver.findElement(By.xpath("//*[@id=\"main\"]/iframe"));
					driver.switchTo().frame(iframe);
					Element iframe2 = driver.getElement(By.id("iframecc"));
					driver.switchTo().frame(iframe2);
					return new Iframecc(driver);
				}
			}

			public PurchaseIframe getPurchaseFrame() {
				Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
				driver.switchTo().frame(iframe);
				return new PurchaseIframe(driver);
			}

		}
	}

	public String returnRefID(String cardNumber) throws Exception {
		MainIframe mainIframe = this.getMainIframe();
		String refID = null;
		mainIframe.clickBuyButton();
		Iframecc iframecc = mainIframe.getIframecc();
		iframecc.setCardNumber(cardNumber);
		iframecc.setExpiredDate();
		iframecc.setCardHolderName();
		iframecc.setCVV();
		iframecc.setZipCode();
		iframecc.setEmail();
		iframecc.clickBuyButton();
		refID = iframecc.getRefId();
		try {
		ExtentTestManager.getTest().info("refID: " + refID);
		}catch (Exception ignore) {
			
		}
		String caseKey = iframecc.getSelectedCase();
		if (caseKey.equals("ERROR")) {

		} else if (caseKey.equals("PURCHASE")) {
			iframecc.clickProcessButton();
			String redirectKey = iframecc.getRedirectCase();
			if (!redirectKey.equals("ERROR")) {
				PurchaseIframe purchaseFrame = iframecc.getPurchaseFrame();
				purchaseFrame.setOTP();
				purchaseFrame.clickOTPSubmitButton();
				Iframecc newIframecc = purchaseFrame.getIframecc();
				newIframecc.getCompleteCase();
			}
		} else if(caseKey.equals("SUCCESS")) {
			
		}
		
		
		System.out.println(cardNumber + " : " + refID);
		return refID;
	}

}
