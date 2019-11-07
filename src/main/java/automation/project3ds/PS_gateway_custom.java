package automation.project3ds;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PS_gateway_custom {

	public String email;
	String name = "Payment Wall";
	public String cardNumber = "4000000000000002";
	String expMonth = "01";
	String expYear = "22";
	String cvv = "123";
	String zip = "32043";

	Driver driver;
	Element element;

	By emailTxb = By.id("email");
	By cardNumberTxb = By.id("card-number");
	By expiryMonthTxb = By.id("card-exp-month");
	By expiryYearTxb = By.id("card-exp-year");
	By cvvTxb = By.id("card-cvv");
	By submitBtn = By.xpath("//button");
	By jsonBody = By.xpath("//body[contains(text(),'{')]");
	int s = 110;

	By otpTxb = By.name("challengeDataEntry");
	By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");
	
	public PS_gateway_custom(Driver driver) {
		this.driver = driver;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail() {
		email = "meo" + AnnotationPage.timestamp() + "@spam4.me";
		element = driver.getElement(emailTxb);
		email = getEmail();
		element.sendKeys(email);
	}

	private void setCardNumber() throws Exception {
		element = driver.getElement(cardNumberTxb);
		element.click();
		element.sendKeys(cardNumber);
	}

	private void setExpiryMonth() throws Exception {
		element = driver.getElement(expiryMonthTxb);
		element.click();
		element.sendKeys(expMonth);
	}

	private void setExpiryYear() throws Exception {
		element = driver.getElement(expiryYearTxb);
		element.click();
		element.sendKeys(expYear);
	}

	private void setCvv() throws Exception {
		element = driver.getElement(cvvTxb);
		element.click();
		element.sendKeys(cvv);
	}

	private void clickSubmit() throws Exception {
//		Thread.sleep(10000);
		element = driver.getElement(submitBtn);
		element.clickJS();
		element = driver.getElement(jsonBody,60000);
	}

	public String getUrl() throws Exception {
		element = driver.getElement(jsonBody,60000);
		String str = element.getText();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(str);
		String url = jsonNode.path("secure").path("redirect").asText();
		if (url.equals("")) {
			throw new Exception(jsonNode.toString());
		} else {
			return url;
		}
	}
	
	private void printJsonResponse() throws Exception {
		driver.switchTo().defaultContent();
		Element element = driver.getElement(jsonBody,60000);
		String str = element.getText();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(str);
		String printResult2 = PrettyPrint.formatJson(jsonNode);
		ExtentManager.logInfo("Response:<br />" + printResult2);
	}
	
	public void finish3dsV1OFF() throws Exception {
		String url = getUrl();
		System.out.println(url);
		driver.get(url);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
		printJsonResponse();
	}

	public void finish3dsV2OFF() throws Exception {
		printJsonResponse();
	}

	public void finish3dsV1ON() throws Exception {
		String url = getUrl();
		System.out.println(url);
		driver.get(url);
		WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
		printJsonResponse();
	}

	public void finish3dsV2ON() throws Exception {
		String url = getUrl();
		System.out.println(url);
		driver.get(url);
		WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV2 obj = new Widget3dsV2(driver);
		obj.finish();
		printJsonResponse();
	}

	public void finish3dsPolk() throws Exception {
		String url = getUrl();
		System.out.println(url);
		driver.get(url);
		driver.getElement(By.xpath("//input[@value='Submit']")).click();
		try {
			driver.switchTo().alert().accept();
		}catch(Exception ignore) {
			
		}
		printJsonResponse();
	}

	public void createPayment() throws Exception {
		Thread.sleep(1000);
		setEmail();
		setCardNumber();
		setExpiryMonth();
		setExpiryYear();
		setCvv();
		clickSubmit();
	}

}
