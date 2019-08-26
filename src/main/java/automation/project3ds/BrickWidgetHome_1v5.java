package automation.project3ds;

import java.util.ArrayList;

import org.openqa.selenium.By;


public class BrickWidgetHome_1v5 {
	
	public static String email;

	private static Driver driver = AnnotationPage.getDriver();

	
	private static By otpTxb = By.name("challengeDataEntry");
	private static By otpSummitBtn = By
			.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");



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

	

	public static String returnRefID(String cardNumber) throws Exception {
		String refID = null;
		Thread.sleep(1000);
		WidgetMainFrame.clickBuyButton();
		WidgetIframeccBackup.createPayment(cardNumber);
		refID = WidgetIframecc.getRefId();
		ExtentManager.logInfo("refID: " + refID);
		handleCase();
		System.out.println(cardNumber + " : " + refID);
		return refID;
	}
	
	private static void handleCase() throws Exception {
		String caseKey = WidgetIframeccBackup.getSelectedCase();
		if (caseKey.equals("ERROR")) {

		} else if (caseKey.equals("PURCHASE")) {
			WidgetIframecc.clickProcessButton();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			if (tabs.size() == 1) {
//				String redirectKey = WidgetIframecc.getRedirectCase();
//				if (redirectKey.equals("OTP")) {
//					PurchaseIframe purchaseFrame = iframecc.getPurchaseFrame();
//					String versionKey = purchaseFrame.getVersionCase();
//					if (versionKey.equals("V2")) {
//						purchaseFrame.setOTP();
//						purchaseFrame.clickOTPSubmitButton();
//						WidgetIframecc newIframecc = purchaseFrame.getIframecc();
//						newIframecc.getCompleteCase();
//					} else if (versionKey.equals("V1")) {
//						automation.project3ds.BrickWidgetHome_1v5.MainIframe.WidgetIframecc.PurchaseIframe.AuthWindow authWindow = purchaseFrame
//								.getAuthWindow();
//						authWindow.setPassword();
//						authWindow.clickSubmit();
//						WidgetIframecc newIframecc = authWindow.getIframecc();
//						newIframecc.getCompleteCase();
//					}
//				}
			} else if (tabs.size() == 2) {
				Thread.sleep(7000);
				tabs = new ArrayList<String>(driver.getWindowHandles());
				if (tabs.size() == 2) {
					driver.switchTo().window(tabs.get(1));
					WidgetOTP.completeOTP();
//					if (driver.isExist(By.id("authWindow"), 2000)) {
//						AuthWindow authWindow = getAuthWindow();
//						authWindow.setPassword();
//						authWindow.clickSubmit();
//						driver.switchTo().window(tabs.get(0));
//						mainIframe = getMainIframe();
//						iframecc = mainIframe.getIframecc();
//						String finalKey = iframecc.getSelectedCase();
//						Assert.assertEquals(finalKey, "SUCCESS");
//					} else {
//						driver.getElement(By.xpath("//input[@value='Submit']")).click();
//						driver.switchTo().window(tabs.get(0));
//						mainIframe = getMainIframe();
//						iframecc = mainIframe.getIframecc();
//						String finalKey = iframecc.getSelectedCase();
//						Assert.assertEquals(finalKey, "SUCCESS");
//						
//					}
					driver.switchTo().window(tabs.get(0));
					WidgetMainFrame.getCompleteMessage();
					
				} else {

				}
			}
		} else if (caseKey.equals("SUCCESS")) {

		}
	}

}
