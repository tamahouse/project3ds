package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Brick_1v6 {
	
	Driver driver;
	
	String EMAIL;
	String NAME = "Payment Wall";
	String CARDNUMBER = "4000000000000002";
	String EXPDATE = "0122";
	String CVV = "123";
	String ZIP = "32043";

//	By img = By.xpath("//*[@data-card-name = 'mastercard' ]");
	private By brick = By.id("brick");
	private By cardHolderInput = By.id("brick-cardholder");
//	private By cardNumberContainer = By.id("brick-card-number");
	private By cardNumberInput = By.id("input");
	private By expTxb = By.id("brick-card-expiration");
	private By buyBtn = By.xpath("//*[contains(@class,'is-active')]//*[contains(@class,'js-brick-submit')]");
	private By processBtn = By.xpath("//*[contains(@class,'is-active')]/div/button/*[contains(text(),'Proceed to Bank')]");
	private By refIDContainer = By.id("brick-payment-form");
	private By useDifferentCard = By.xpath("//*[contains(@class,'js-brick-step-cards is-active')]//a[@data-phrase = 'use-different-card']");
	private By backToStoredCard = By.xpath("//*[contains(@class,'js-brick-step-form is-active')]//a[@data-phrase='back-to-stored-cards']");
	private By tryAgain = By.xpath("//*[contains(@class,'js-brick-step-3dsecure is-active')]//a[@data-phrase = 'Try again']");
	private By zipInput = By.id("brick-zip");
	private By emailInput = By.id("brick-email");
	private By thankyou = By.xpath("//h3[text()='Thank you for your purchase!']");
	private By paymentForm = By.id("brick-payment-form");
	private By terms = By.xpath("//*[contains(@class,'brick-terms')]//a");
	
	private By formError = By.xpath("//*[@class='brick-form-errors has-errors'][./following-sibling::*[./*[contains(@class,'brick-step-form') and contains(@class,'is-active')]]]");
	
	public By white = By.xpath("//div[contains(@class, 'is-active')]//*[@data-phrase='by-clicking-button-you-agree']");
	public By cardHolderTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Name on card')]]");
	public By cardNumberTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Card number')]]");
	public By expirationDateTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Expiration date')]]");
	public By cvvTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'CVV') or contains(text(),'CID')]]");
	public By zipTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'ZIP')]]");
	public By emailTxb = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div[./descendant::*[contains(text(),'Email')]]");
	
	By textboxList = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div");
	
	
	public Brick_1v6(Driver driver) {
		this.driver = driver;
	}

	
	
	public List<Element> getTextboxList(){
		driver.getElement(cardHolderInput);
		List<Element> list = driver.getElements(textboxList);
		return list;
	}
	
	public void clickWhiteSpace() {
		Element element = driver.getElement(white);
		element.click();
	}
	
	public String getErrorMessage(Element textbox) {
		Element label = textbox.getElement(By.xpath("./*[text()][1]"));
		String labelText = label.getText();
		String eText = null;
		if (labelText.contains("Name on card")) {
			eText = "Please enter your name on the card";
		}else if(labelText.contains("Card number")) {
			eText = "Please enter your card number";
		}else if(labelText.contains("Expiration date")) {
			eText = "Please enter an expiry date";
		}else if(labelText.contains("CVV")) {
			eText = "Please enter CVV code";
		}else if(labelText.contains("ZIP / Postal code")) {
			eText = "Please enter ZIP code";
		}else if(labelText.contains("Email")) {
			eText = "Please enter a valid email address";
		}
		return eText;
	}
	
	
	public void createPayment() throws Exception {
		setCardHolder();
		setCardNumber();
		setExpirationDate();
		setCVV();
		setZipCode();
		setEmail();
		clickBuyButton();
	}
	
	public Element getFormError() throws Exception {
		Element element = driver.getElement(formError);
		Thread.sleep(500);
		return element;
	}
	
	public Element getCardHolderTextbox() {
		return driver.getElement(cardHolderTxb);
	}
	
	public Element getCardNumberTextbox() {
		return driver.getElement(cardNumberTxb);
	}
	
	public Element getExpirationDateTextbox() {
		return driver.getElement(expirationDateTxb);
	}
	
	public Element getCvvTextbox() {
		return driver.getElement(cvvTxb);
	}
	
	public Element getZipTextbox() {
		return driver.getElement(zipTxb);
		
	}
	
	public Element getEmailTextbox() throws Exception {
		Element element =  driver.getElement(emailTxb);
		Thread.sleep(500);
		return element;
	}
	
	
	public void clickUserDifferentCard() {
		Element element = driver.getElement(useDifferentCard);
		element.click();
	}
	
	public void clickBackToStoredCard() {
		Element element = driver.getElement(backToStoredCard);
		element.click();
	}
	
	public void clickTryAgain() {
		Element element = driver.getElement(tryAgain);
		element.click();
	}
	
	public void clickTerms() {
		Element element = driver.getElement(terms);
		element.click();
	}

	
	public void setCardHolder() throws Exception {
		Element cardHolderNameTextbox = driver.getElement(cardHolderInput);
		cardHolderNameTextbox.sendKeys(NAME);
//		
	}
	
	public Element getActiveBrandIcon() {
		return driver.getElement(By.xpath("//*[@class='brick-icon-cc-container']/*[contains(@class,'active')]"));
	}
	
	private void getDeepIframe(Element cover) {
		WebElement frame = cover.getElement(By.tagName("iframe")).getWebElement();
		driver.switchTo().frame(frame);
	}

	public void setCardNumber() throws Exception {
		Element cover = getCardNumberTextbox();
		this.getDeepIframe(cover);
		Element container = driver.getElement(cardNumberInput);
		container.sendKeys(CARDNUMBER);
		Thread.sleep(2000);
		this.switchToBrickIframe();
		driver.getElement(white).click();
	}
	
	public void setCardNumber(String cardNumber) throws Exception {
		this.CARDNUMBER = cardNumber;
	}
	
	public void setCVV() throws Exception {
		Element cover = getCvvTextbox();
		this.getDeepIframe(cover);
		Element container = driver.getElement(cardNumberInput);
		container.sendKeys(CVV);
		this.switchToBrickIframe();
		driver.getElement(white).click();
	}
	
	private void switchToBrickIframe() {
		driver.switchTo().defaultContent();
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		driver.switchTo().frame(iframe.getWebElement());
		iframe = driver.getElement(By.id("iframecc"));
		driver.switchTo().frame(iframe.getWebElement());
	}
	
	public void setExpirationDate() throws Exception {
		Element cover = getExpirationDateTextbox();
		this.getDeepIframe(cover);
		Element container = driver.getElement(cardNumberInput);
		container.sendKeys(EXPDATE);
		Thread.sleep(500);
		this.switchToBrickIframe();
		driver.getElement(white).click();
	}

	
	public void setZipCode() throws Exception {
		Element zipTextbox = driver.getElement(zipInput);
		zipTextbox.sendKeys(ZIP);
//		this.clickImage();
	}
	
	private String generateEmail() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String email = "meo"+timestamp+"@spam4.me";
		return email;
	}

	
	private void setEmail() {
		EMAIL = "meo"+ AnnotationPage.timestamp() + "@spam4.me";
		Element element = driver.getElement(emailInput);
		element.sendKeys(EMAIL);
	}
	
	public String getEmail() {
		return this.EMAIL;
	}

	public void clickBuyButton() throws Exception {
		Element buyButton = driver.getElement(buyBtn);
		buyButton.click();
		Thread.sleep(500);
	}

	public void clickProcessButton() throws Exception {
		Thread.sleep(1000);
		Element processButton = driver.getElement(processBtn);
		processButton.click();
	}

	public String getRefId() {
		Element refIdContainer = driver.getElement(refIDContainer);
		String str = refIdContainer.getAttribute("action");
		str = str.substring(str.lastIndexOf("=") + 1).substring(1);
		return str;
	}

	public Boolean getSuccess() {
		return driver.isExist(thankyou);
	}

//	public PurchaseIframe getPurchaseFrame() {
//		Element iframe = driver.getElement(By.id("Cardinal-CCA-IFrame"));
//		driver.switchTo().frame(iframe.getWebElement());
//		return new PurchaseIframe(driver);
//	}

//	public class PurchaseIframe {
//		Driver driver;
//
//		public PurchaseIframe(Driver driver) {
//			this.driver = driver;
//		}
//
//		private void setOTP() throws Exception {
//			Element otpTextbox = driver.getElement(otpTxb);
//			otpTextbox.moveToTopView();
//			otpTextbox.sendKeysSlow(80, "1234");
//		}
//
//		private void clickOTPSubmitButton() throws Exception {
//			Element optSubmitButton = driver.getElement(otpSummitBtn);
//			optSubmitButton.highlight();
//			optSubmitButton.click();
//		}
//
//		private WidgetIframecc getIframecc() {
//			driver.switchTo().defaultContent();
//			Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
//			driver.switchTo().frame(iframe.getWebElement());
//			Element iframe2 = driver.getElement(By.id("iframecc"));
//			driver.switchTo().frame(iframe2.getWebElement());
//			return new WidgetIframecc(driver);
//		}
//
//		private String getVersionCase() throws Exception {
//			Map<By, String> map = new HashMap<By, String>();
//			map.put(otpTxb, "V2");
//			map.put(By.id("authWindow"), "V1");
//			return driver.getSelection(map);
//		}
//
//		public AuthWindow getAuthWindow() {
//			Element iframe = driver.getElement(By.id("authWindow"));
//			driver.switchTo().frame(iframe.getWebElement());
//			return new AuthWindow(driver);
//		}
//
//		public class AuthWindow {
//			Driver driver;
//
//			By password = By.id("password");
//			By submitBtn = By.xpath("//input[@value = 'Submit']");
//
//			public AuthWindow(Driver driver) {
//				this.driver = driver;
//			}
//
//			private void setPassword() {
//				Element element = driver.getElement(password);
//				element.sendKeys("1234");
//			}
//
//			private void clickSubmit() {
//				Element element = driver.getElement(submitBtn);
//				element.click();
//			}
//
//			private WidgetIframecc getIframecc() {
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
