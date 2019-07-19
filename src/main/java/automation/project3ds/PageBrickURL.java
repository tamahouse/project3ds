package automation.project3ds;

import org.openqa.selenium.By;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PageBrickURL {

	Driver driver;
	Element element;

	By emailTxb = By.id("email");
	By cardNumberTxb = By.id("pan");
	By expiryMonthTxb = By.id("card-exp-month");
	By expiryYearTxb = By.id("card-exp-year");
	By cvvTxb = By.id("cvv");
	By submitBtn = By.xpath("//button");
	By jsonBody = By.xpath("//body[contains(text(),'success')]");
	int s = 30;

	By otpTxb = By.name("challengeDataEntry");
	By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");

	public PageBrickURL(Driver driver) {
		this.driver = driver;
	}

	private void setEmail() throws Exception {
		element = driver.getElement(emailTxb);
		element.click();
		element.sendKeysSlow(s,"meo@spam4.me");
	}

	private void setCardNumber(String cardNumber) throws Exception {
		element = driver.getElement(cardNumberTxb);
		element.click();
		element.sendKeysSlow(cardNumber);
	}

	private void setExpiryMonth() throws Exception {
		element = driver.getElement(expiryMonthTxb);
		element.click();
		element.sendKeysSlow(s,"01");
	}

	private void setExpiryYear() throws Exception {
		element = driver.getElement(expiryYearTxb);
		element.click();
		element.sendKeysSlow(s,"22");
	}

	private void setCvv() throws Exception {
		element = driver.getElement(cvvTxb);
		element.click();
		element.sendKeysSlow(s,"123");
	}

	private void clickSubmit() throws Exception {
//		Thread.sleep(10000);
		element = driver.getElement(submitBtn);
		element.click();
	}

	private void getResponse() throws Exception {
		element = driver.getElement(jsonBody);
		String str = element.getText();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(str);
		
//		print temporary response
		String printResult1 = PrettyPrint.formatJson(jsonNode);
		try {
			System.out.println(printResult1);
		} catch (Exception ignore) {

		}
		
//		check response
		int success = jsonNode.path("success").asInt();
		String url = jsonNode.path("secure").path("redirect").asText();
		if (success == 0 && !url.equals("")) {
			
			System.out.println(url);
			driver.get(url);
			VisaPurchaseIframe threedsIframe = this.getPurchaseFrame();
			threedsIframe.setOTP();
			threedsIframe.clickOTPSubmitButton();
			element = driver.getElement(jsonBody);
			str = element.getText();
			jsonNode = mapper.readTree(str);
		} else {
		}
		
//		print final response
		String printResult2 = PrettyPrint.formatJson(jsonNode);
		try {
			ExtentManager.getTest().info("Response:<br />" + printResult2);
			System.out.println(printResult2);
		} catch (Exception ignore) {

		}
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
			otpTextbox.sendKeysSlow("1234");
		}

		private void clickOTPSubmitButton() throws Exception {
			Element optSubmitButton = driver.getElement(otpSummitBtn);
			optSubmitButton.highlight();
			optSubmitButton.clickJS();
		}
	}

	public void submitInformation(String cardNumber) throws Exception {
		Thread.sleep(1000);
		this.setEmail();
		this.setCardNumber(cardNumber);
		this.setExpiryMonth();
		this.setExpiryYear();
		this.setCvv();
		this.clickSubmit();
		this.getResponse();
	}
}
