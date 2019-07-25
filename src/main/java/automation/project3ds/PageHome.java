package automation.project3ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import automation.project3ds.PageHome.MainIframe.Iframecc;
import automation.project3ds.PageHome.MainIframe.Iframecc.VisaPurchaseIframe;

public class PageHome {

	Driver driver;

	By cardNumberTxb1 = By.id("card_1");
	By refIDContainer = By.xpath("//input[@name ='ref_id']");
	By otpTxb = By.name("challengeDataEntry");
	By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");

	public PageHome(Driver driver) {
		this.driver = driver;
	}
	
	private MainIframe getMainIframe() {
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		driver.switchTo().frame(iframe.getWebElement());
		return new MainIframe(driver);
	}

	public class MainIframe {
		Driver driver;

		By buyBtn = By.id("ps_psb");

		public MainIframe(Driver driver) {
			this.driver = driver;
		}

		private void clickBuyButton() throws Exception {
			driver.getElement(this.buyBtn).click();
			Thread.sleep(3000);
		}
		
		public Iframecc getIframecc() {
			Element newIframe = driver.getElement(By.id("iframecc"));
			driver.switchTo().frame(newIframe.getWebElement());
			return new Iframecc(driver);
		}
		

		public class Iframecc {

			Driver driver;

			By cardNumberContainer = By.id("card_number_container");
			By expMonthTxb = By.id("expmonth_field");
			By expYearTxb = By.id("expyear_field");
			By cardHolderNameTxb = By.id("cardholder");
			By cvvTxb = By.id("cvv_id");
			By addressTxb = By.name("street");
			By cityTxb = By.name("city");
			By countrySlt = By.id("country_select");
			By zipTxb = By.id("zip");
			By stateSlt = By.name("state");
			By buyBtn = By.id("pay_button");

			public Iframecc(Driver driver) {
				this.driver = driver;
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
				
				private Iframecc getIframecc() {
					driver.switchTo().defaultContent();
					Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
					driver.switchTo().frame(iframe.getWebElement());
					Element iframe2 = driver.getElement(By.id("iframecc"));
					driver.switchTo().frame(iframe2.getWebElement());
					return new Iframecc(driver);
				}

				private void clickOTPSubmitButton() throws Exception {
					Element optSubmitButton = driver.getElement(otpSummitBtn);
					optSubmitButton.highlight();
					optSubmitButton.click();
				}
			}
			
			private void getComfirmStatus() {
				driver.getElement(By.id("confirm"));
			}

			public VisaPurchaseIframe getPurchaseFrame() {
				Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
				driver.switchTo().frame(iframe.getWebElement());
				return new VisaPurchaseIframe(driver);
			}

			private void setCardNumber(String cardNumber) throws Exception {
				Iterable<String> str = Splitter.fixedLength(4).split(cardNumber);
				String[] numbers = Iterables.toArray(str, String.class);
				Element container = driver.getElement(cardNumberContainer);
				List<Element> list = container.getElements(By.xpath("./input"));
				for (int i = 0; i < list.size(); i++) {
					Element element = list.get(i);
					element.click();
					element.sendKeys(numbers[i]);
				}
			}

			private void setExpiredDate() {
				Element expmonthTextbox = driver.getElement(expMonthTxb);
				Element expyearTextbox = driver.getElement(expYearTxb);
				expmonthTextbox.sendKeys("01");
				expyearTextbox.sendKeys("22");
			}

			private void setCardHolderName() {
				Element cardHolderNameTextbox = driver.getElement(cardHolderNameTxb);
				cardHolderNameTextbox.sendKeys("Payment Wall");
			}

			private void setCVV() {
				Element cvvTextbox = driver.getElement(cvvTxb);
				cvvTextbox.sendKeys("123");
			}

			private void setAddress() {
				Element addressTextbox = driver.getElement(addressTxb);
				addressTextbox.sendKeys("32 Florida");
			}

			private void setCity() {
				Element cityTextbox = driver.getElement(cityTxb);
				cityTextbox.sendKeys("Florida");
			}

			private void setCountry() {
				Select select = new Select(driver.getElement(countrySlt));
				select.selectByValue("US");
			}

			private void setZipCode() {
				Element zipTextbox = driver.getElement(zipTxb);
				zipTextbox.sendKeys("32043");
			}

			private void setState() {
				Select select = new Select(driver.getElement(stateSlt));
				select.selectByValue("FL");
			}

			private void clickBuyButton() {
				Element buyButton2 = driver.getElement(buyBtn);
				buyButton2.click();
			}

			private String getRefId() {
				Element refIdContainer = driver.getElement(refIDContainer);
				String str = refIdContainer.getAttribute("value");
				str = str.substring(1);
				return str;
			}

			private String getSelectedCase() throws Exception {
				Map<By, String> map = new HashMap<By, String>();
				map.put(By.id("Cardinal-CCA-IFrame"), "VISA_PURCHASE");
				map.put(By.xpath("//*[@id ='errors' and not(contains(@style,'none'))]"), "ERROR");
				map.put(By.id("save_button_id"), "MASTERCARD_PURCHASE");
				return driver.getSelection(map);
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
		iframecc.setAddress();
		iframecc.setCity();
		iframecc.setCountry();
		iframecc.setZipCode();
		iframecc.setState();
		iframecc.clickBuyButton();
		refID = iframecc.getRefId();
		try {
			ExtentManager.getTest().info("refID: " + refID);
		}catch(Exception ignore) {
			
		}
		String caseKey = iframecc.getSelectedCase();
		if (caseKey.equals("ERROR")) {

		} else if (caseKey.equals("VISA_PURCHASE")) {
			VisaPurchaseIframe visaFrame = iframecc.getPurchaseFrame();
			visaFrame.setOTP();
			visaFrame.clickOTPSubmitButton();
			Iframecc newIframecc= visaFrame.getIframecc();
			newIframecc.getComfirmStatus();
		} else if (caseKey.equals("MASTERCARD_PURCHASE")) {

		}
		System.out.println(cardNumber + " : " + refID);
		return refID;
	}

}
