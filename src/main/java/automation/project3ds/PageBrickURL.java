package automation.project3ds;

import java.io.IOException;

import org.openqa.selenium.By;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.project3ds.Driver.Browser;
import automation.project3ds.PageBrickHTML.VisaPurchaseIframe;

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

	By otpTxb = By.name("challengeDataEntry");
	By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");

	public PageBrickURL(Driver driver) {
		this.driver = driver;
	}

	private void setEmail() {
		element = driver.getElement(emailTxb);
		element.sendKeys("meo@spam4.me");
	}

	private void setCardNumber(String cardNumber) {
		element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}

	private void setExpiryMonth() {
		element = driver.getElement(expiryMonthTxb);
		element.sendKeys("01");
	}

	private void setExpiryYear() {
		element = driver.getElement(expiryYearTxb);
		element.sendKeys("22");
	}

	private void setCvv() {
		element = driver.getElement(cvvTxb);
		element.sendKeys("123");
	}

	private void clickSubmit() throws Exception {
		Thread.sleep(10000);
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
			ExtentTestManager.getTest().info("Response:<br />" + printResult2);
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
			otpTextbox.sendKeys("1234");
		}

		private void clickOTPSubmitButton() throws Exception {
			Element optSubmitButton = driver.getElement(otpSummitBtn);
			optSubmitButton.highlight();
			optSubmitButton.clickJS();
		}
	}

	public void submitInformation(String cardNumber) throws Exception {
		this.setEmail();
		this.setCardNumber(cardNumber);
		this.setExpiryMonth();
		this.setExpiryYear();
		this.setCvv();
		this.clickSubmit();
		this.getResponse();
	}
}
