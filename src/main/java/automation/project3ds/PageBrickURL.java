package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PageBrickURL {

	static Driver driver = AnnotationPage.getDriver();
	static Element element;
	public static String email;

	static By emailTxb = By.id("email");
	static By cardNumberTxb = By.id("card-number");
	static By expiryMonthTxb = By.id("card-exp-month");
	static By expiryYearTxb = By.id("card-exp-year");
	static By cvvTxb = By.id("card-cvv");
	static By submitBtn = By.xpath("//button");
	static By jsonBody = By.xpath("//body[contains(text(),'{')]");
	int s = 110;

	static By otpTxb = By.name("challengeDataEntry");
	static By otpSummitBtn = By.xpath("//input[@type='submit' and @class='button primary' and @value ='SUBMIT']");
	
	private static String getEmail() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String email = "meo"+timestamp+"@spam4.me";
		return email;
	}

	public static void setEmail() {
		element = driver.getElement(emailTxb);
		email = getEmail();
		element.sendKeys(email);
	}

	private static void setCardNumber(String cardNumber) throws Exception {
		element = driver.getElement(cardNumberTxb);
		element.click();
		element.sendKeys(cardNumber);
	}

	private static void setExpiryMonth() throws Exception {
		element = driver.getElement(expiryMonthTxb);
		element.click();
		element.sendKeys("01");
	}

	private static void setExpiryYear() throws Exception {
		element = driver.getElement(expiryYearTxb);
		element.click();
		element.sendKeys("22");
	}

	private static void setCvv() throws Exception {
		element = driver.getElement(cvvTxb);
		element.click();
		element.sendKeys("123");
	}

	private static void clickSubmit() throws Exception {
//		Thread.sleep(10000);
		element = driver.getElement(submitBtn);
		element.click();
	}

//	private static void finish3dsON() throws Exception {
//		element = driver.getElement(jsonBody);
//		String str = element.getText();
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode jsonNode = mapper.readTree(str);
//
////		print temporary response
//		String printResult1 = PrettyPrint.formatJson(jsonNode);
//		try {
//			System.out.println(printResult1);
//		} catch (Exception ignore) {
//
//		}
//
////		check response
////		int success = jsonNode.path("success").asInt();
//		String url = jsonNode.path("secure").path("redirect").asText();
//		if (!url.equals("")) {
//
//			System.out.println(url);
//			driver.get(url);
//			if (driver.isExist(By.id("Cardinal-CCA-IFrame"), 2000)) {
//				VisaPurchaseIframe threedsIframe = getPurchaseFrame();
//				String versionKey = threedsIframe.getVersionCase();
//				if (versionKey.equals("V2")) {
//					threedsIframe.setOTP();
//					threedsIframe.clickOTPSubmitButton();
//				} else if (versionKey.equals("V1")) {
//					automation.project3ds.PageBrickURL.VisaPurchaseIframe.AuthWindow authWindow = threedsIframe
//							.getAuthWindow();
//					authWindow.setPassword();
//					authWindow.clickSubmit();
//					driver.switchTo().defaultContent();
//				}
//
//			} else {
//				if (driver.isExist(By.id("authWindow"), 2000)) {
//					AuthWindow authWindow = getAuthWindow();
//					authWindow.setPassword();
//					authWindow.clickSubmit();
//					driver.switchTo().defaultContent();
//				} else {
//
//				}
//			}
//			element = driver.getElement(jsonBody);
//			str = element.getText();
//			jsonNode = mapper.readTree(str);
//		} else {
//		}
//
////		print final response
//		String printResult2 = PrettyPrint.formatJson(jsonNode);
//		try {
//			ExtentManager.getTest().info("Response:<br />" + printResult2);
//			System.out.println(printResult2);
//		} catch (Exception ignore) {
//
//		}
//	}
	
	private static void finish3dsPolkON() throws Exception {
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
//		int success = jsonNode.path("success").asInt();
		String url = jsonNode.path("secure").path("redirect").asText();
		if (!url.equals("")) {

			System.out.println(url);
			driver.get(url);
			driver.getElement(By.xpath("//input[@value='Submit']")).click();
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

	
	
	public static void createPayment(String cardNumber) throws Exception {
		Thread.sleep(1000);
		setEmail();
		setCardNumber(cardNumber);
		setExpiryMonth();
		setExpiryYear();
		setCvv();
		clickSubmit();
	}

//	public static void submitInformation(String cardNumber) throws Exception {
//		createPayment(cardNumber);
//		getResponse();
//	}
//	
//	public static void submitPolk(String cardNumber) throws Exception {
//		createPayment(cardNumber);
//		getResponsePolk();
//	}
//	
}
