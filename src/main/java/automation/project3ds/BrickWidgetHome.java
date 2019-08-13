package automation.project3ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import automation.project3ds.BrickWidgetHome.MainIframe.Iframecc;
import automation.project3ds.BrickWidgetHome.MainIframe.Iframecc.PurchaseIframe;
import automation.project3ds.BrickWidgetHome.MainIframe.Iframecc.PurchaseIframe.AuthWindow;

public class BrickWidgetHome {
	
	public static String email;

	private static Driver driver = AnnotationPage.getDriver();

	private static By refIDContainer = By.id("brick-payment-form");
	private static By otpTxb = By.name("challengeDataEntry");
	private static By otpSummitBtn = By
			.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");

	private static MainIframe getMainIframe() {
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		driver.switchTo().frame(iframe.getWebElement());
		return new MainIframe(driver);
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

	public static class MainIframe {
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
			driver.isExist(By.tagName("iframe"), 100);
			Element newIframe = driver.getElement(By.tagName("iframe"));
			driver.switchTo().frame(newIframe.getWebElement());
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

			private void setCardHolderName() throws Exception {
				Element cardHolderNameTextbox = driver.getElement(cardHolderNameTxb);
				cardHolderNameTextbox.sendKeys("Payment Wall");
//				
			}

			public void setCardNumber(String cardNumber) throws Exception {
				Element container = driver.getElement(cardNumberContainer);
				container.sendKeys(cardNumber);
				driver.getElement(By.xpath("//label[@for='brick-card-number']")).click();
				Thread.sleep(2000);
			}

			private void setExpiredDate() throws Exception {
				Thread.sleep(1000);
				Element expiredTextbox = driver.getElement(expTxb);
				expiredTextbox.click();
				expiredTextbox.sendKeys("0122");
//				this.clickImage();
			}

			private void setCVV() throws Exception {
				Element cvvTextbox = driver.getElement(cvvTxb);
				cvvTextbox.sendKeys("123");
//				this.clickImage();
			}

			private void setZipCode() throws Exception {
				Element zipTextbox = driver.getElement(zipTxb);
				zipTextbox.sendKeys("32043");
//				this.clickImage();
			}
			
			private String getEmail() {
				String timestamp = String.valueOf(System.currentTimeMillis());
				String email = "meo"+timestamp+"@spam4.me";
				return email;
			}

			public void setEmail() {
				Element element = driver.getElement(emailTxb);
				email = getEmail();
				element.sendKeys(email);
			}

			private void clickBuyButton() throws Exception {
//				Thread.sleep(5000);
				Element buyButton = driver.getElement(buyBtn);
				buyButton.moveToTopView();
				buyButton.click();

			}

			private void clickProcessButton() throws Exception {
				Thread.sleep(1000);
				Element processButton = driver.getElement(processBtn);
				processButton.click();
			}

			private String getRefId() {
				Element refIdContainer = driver.getElement(refIDContainer);
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
				map.put(By.xpath("//*[@class='button js-brick-submit brick-is-success']"), "SUCCESS");
				return driver.getSelection(map);
			}

			private String getRedirectCase() throws Exception {
				Map<By, String> map = new HashMap<By, String>();
				map.put(By.id("Cardinal-CCA-IFrame"), "OTP");
				map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
						"ERROR");
				return driver.getSelection(map);
			}

//			private String getRedirectCase() throws Exception {
//				Map<By, String> map = new HashMap<By, String>();
//				By auth = By.id("authWindow");
//				map.put(By.id("Cardinal-CCA-IFrame"), "V2");
//				map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
//						"ERROR");
//				map.put(auth, "V1");
//				for (int i = 0; i < 200; i++) {
//					for (Map.Entry<By, String> entry : map.entrySet()) {
//						if (entry.getKey().equals(auth)) {
//							ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//							driver.switchTo().window(tabs.get(1));
//						} else {
//							ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//							driver.switchTo().window(tabs.get(0));
//							MainIframe mainFrame = getMainIframe();
//							Iframecc iframeCC = mainFrame.getIframecc();
//						}
//						if (driver.isExistNow(entry.getKey()) == true) {
//							String str = entry.getValue();
//							System.out.println(str);
//							return str;
//						}
//						driver.sleep(100);
//					}
//				}
//				return null;
//			}

			private String getCompleteCase() throws Exception {
				Map<By, String> map = new HashMap<By, String>();
				map.put(By.xpath("//*[@class='brick-submit__success']"), "PURCHASE");
				map.put(By.xpath("//*[@class ='errors js-brick-errors is-errors' and not(contains(@style,'none'))]"),
						"ERROR");
				return driver.getSelection(map);
			}

			public PurchaseIframe getPurchaseFrame() {
				Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
				driver.switchTo().frame(iframe.getWebElement());
				return new PurchaseIframe(driver);
			}

			public class PurchaseIframe {
				Driver driver;

				public PurchaseIframe(Driver driver) {
					this.driver = driver;
				}

				private void setOTP() throws Exception {
					Element otpTextbox = driver.getElement(otpTxb);
					otpTextbox.moveToTopView();
					otpTextbox.sendKeysSlow(80, "1234");
				}

				private void clickOTPSubmitButton() throws Exception {
					Element optSubmitButton = driver.getElement(otpSummitBtn);
					optSubmitButton.highlight();
					optSubmitButton.click();
				}

				private Iframecc getIframecc() {
					driver.switchTo().defaultContent();
					Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
					driver.switchTo().frame(iframe.getWebElement());
					Element iframe2 = driver.getElement(By.id("iframecc"));
					driver.switchTo().frame(iframe2.getWebElement());
					return new Iframecc(driver);
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

					private Iframecc getIframecc() {
						driver.switchTo().defaultContent();
						Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
						driver.switchTo().frame(iframe.getWebElement());
						Element iframe2 = driver.getElement(By.id("iframecc"));
						driver.switchTo().frame(iframe2.getWebElement());
						return new Iframecc(driver);
					}
				}
			}

		}
	}

	public static String returnRefID(String cardNumber) throws Exception {
		MainIframe mainIframe = getMainIframe();
		String refID = null;
		Thread.sleep(1000);
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
		ExtentManager.logInfo("refID: " + refID);

		String caseKey = iframecc.getSelectedCase();
		if (caseKey.equals("ERROR")) {

		} else if (caseKey.equals("PURCHASE")) {
			iframecc.clickProcessButton();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			if (tabs.size() == 1) {
				String redirectKey = iframecc.getRedirectCase();
				if (redirectKey.equals("OTP")) {
					PurchaseIframe purchaseFrame = iframecc.getPurchaseFrame();
					String versionKey = purchaseFrame.getVersionCase();
					if (versionKey.equals("V2")) {
						purchaseFrame.setOTP();
						purchaseFrame.clickOTPSubmitButton();
						Iframecc newIframecc = purchaseFrame.getIframecc();
						newIframecc.getCompleteCase();
					} else if (versionKey.equals("V1")) {
						automation.project3ds.BrickWidgetHome.MainIframe.Iframecc.PurchaseIframe.AuthWindow authWindow = purchaseFrame
								.getAuthWindow();
						authWindow.setPassword();
						authWindow.clickSubmit();
						Iframecc newIframecc = authWindow.getIframecc();
						newIframecc.getCompleteCase();
					}
				}
			} else if (tabs.size() == 2) {
				Thread.sleep(7000);
				tabs = new ArrayList<String>(driver.getWindowHandles());
				if (tabs.size() == 2) {
					driver.switchTo().window(tabs.get(1));
					if (driver.isExist(By.id("authWindow"), 2000)) {
						AuthWindow authWindow = getAuthWindow();
						authWindow.setPassword();
						authWindow.clickSubmit();
						driver.switchTo().window(tabs.get(0));
						mainIframe = getMainIframe();
						iframecc = mainIframe.getIframecc();
						String finalKey = iframecc.getSelectedCase();
						Assert.assertEquals(finalKey, "SUCCESS");
					} else {
						driver.getElement(By.xpath("//input[@value='Submit']")).click();
						driver.switchTo().window(tabs.get(0));
						mainIframe = getMainIframe();
						iframecc = mainIframe.getIframecc();
						String finalKey = iframecc.getSelectedCase();
						Assert.assertEquals(finalKey, "SUCCESS");
						
					}
					driver.switchTo().window(tabs.get(0));
				} else {

				}
			}
		} else if (caseKey.equals("SUCCESS")) {

		}

		System.out.println(cardNumber + " : " + refID);
		return refID;
	}

}
