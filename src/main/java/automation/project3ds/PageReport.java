package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class PageReport {

	Driver driver;

	By clickIDSearchTxb = By.name("search[cl_id]");
	By searchBtn = By.xpath("//input[@type='submit' and @value = 'Search']");
	By enrolledTxt = By.xpath(
			"//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='enrolled:']]/span");
	By paresStatusTxt = By.xpath(
			"//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='authenticating_status:']]/span");
	By signatureVerificationTxt = By.xpath(
			"//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='signature_status:']]/span");
	By cavvTxt = By
			.xpath("//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='cavv:']]/span");
	By eciTxt = By
			.xpath("//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='eci:']]/span");
	By acsUrlTxt = By.xpath(
			"//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='redirectURL:']]/span");
	By payloadTxt = By
			.xpath("//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='payload:']]/span");
	
	By paresStatusTxtAuth = By.xpath(
			"//*[@class='inquiry_details'][./li/span[text()='authenticateCard']]//li[./b[text()='auth:']]/ul/li[./b[text()='authenticating_status:']]/span");
	By signatureVerificationTxtAuth = By.xpath(
			"//*[@class='inquiry_details'][./li/span[text()='authenticateCard']]//li[./b[text()='auth:']]/ul/li[./b[text()='signature_status:']]/span");
	By cavvTxtAuth = By
			.xpath("//*[@class='inquiry_details'][./li/span[text()='authenticateCard']]//li[./b[text()='auth:']]/ul/li[./b[text()='cavv:']]/span");
	By eciTxtAuth = By
			.xpath("//*[@class='inquiry_details'][./li/span[text()='authenticateCard']]//li[./b[text()='auth:']]/ul/li[./b[text()='eci:']]/span");
	

	By viewLogBtn = By
			.xpath("//*[@class='inquiry_details'][./li/span[text()='check3DSecure']]//li[./b[text()='id:']]/a");
	By logTxt = By.xpath("//*[@class='panel-body']/ul/li/pre[contains(text(),'ErrorNo')]");
	By authlogTxt = By.xpath("//*[@class='panel-body']/ul/li/pre[contains(text(),'ErrorNumber')]");

	public PageReport(Driver driver) {
		this.driver = driver;
	}

	public void clickViewLogButton() {
		Element element = driver.getElement(viewLogBtn);
		element.click();
		driver.getElement(By.id("charge-info"));
	}

	public ResponseDS getResponse() throws Exception {
		this.clickViewLogButton();
		Thread.sleep(2000);
		XmlMapper xmlMapper = new XmlMapper();
		WebElement element;
		try {
			element = driver.findElement(logTxt);
		}catch(Exception e) {
			throw new Exception("No lookup logs found");
		}
		String text = element.getText();
		return xmlMapper.readValue(text, ResponseDS.class);
	}
	
	public ResponseAuth getResponseAuth() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		WebElement element;
		try {
			element  = driver.findElement(authlogTxt);
		}catch(Exception e) {
			return null;
		}
		String text = element.getText();
		JsonNode root = mapper.readTree(text);
		JsonNode path = root.get("Payload");
		return mapper.convertValue(path, ResponseAuth.class);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ResponseDS {
		public String Enrolled;
		public String PAResStatus;
		public String SignatureVerification;
		public String Cavv;
		public String EciFlag;
		public String ACSUrl;
		public String Payload;
		public String ErrorNo;
		public String ErrorDesc;
		public String WhiteListStatusSource = "null";
		public String WhiteListStatus = "null";

		public String getEnrolled() {
			return this.Enrolled;
		}

		public String getPAResStatus() {
			return this.PAResStatus;
		}

		public String getSignatureVerification() {
			return this.SignatureVerification;
		}

		public String getCavv() {
			return this.Cavv;
		}

		public String getEciFlag() {
			return this.EciFlag;
		}

		public String getACSUrl() {
			return this.ACSUrl;
		}

		public String getPayload() {
			return this.Payload;
		}

		public String getErrorNo() {
			return this.ErrorNo;
		}

		public String getErrorDesc() {
			return this.ErrorDesc;
		}

		public String getWhiteListStatusSource() {
			return this.WhiteListStatusSource;
		}

		public String getWhiteListStatus() {
			return this.WhiteListStatus;
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ResponseAuth {
		public String ErrorNumber = "NA";
		public String ErrorDescription = "NA";
		public JsonNode Payment;
		
		private String PAResStatus = "";
		private String SignatureVerification = "";
		private String Cavv = "";
		private String EciFlag = ""; 

		@JsonSetter("Payment")
		public void setPayment(JsonNode Payment) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode path = Payment.get("ExtendedData");
			if(path.isObject()) {
			Extended extended = mapper.convertValue(path, Extended.class);
			this.PAResStatus = extended.getPAResStatus();
			this.SignatureVerification = extended.getSignatureVerification();
			this.Cavv = extended.getCavv();
			this.EciFlag = extended.getEciFlag();
			}
		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Extended {
			public String PAResStatus;
			public String SignatureVerification;
			public String CAVV;
			public String ECIFlag; 
			
			public String getPAResStatus() {
				return this.PAResStatus;
			}

			public String getSignatureVerification() {
				return this.SignatureVerification;
			}

			public String getCavv() {
				return this.CAVV;
			}

			public String getEciFlag() {
				return this.ECIFlag;
			}
			
		}

		public String getPAResStatus() {
			return this.PAResStatus;
		}

		public String getSignatureVerification() {
			return this.SignatureVerification;
		}

		public String getCavv() {
			return this.Cavv;
		}

		public String getEciFlag() {
			return this.EciFlag;
		}


		public String getErrorNo() {
			return this.ErrorNumber;
		}

		public String getErrorDesc() {
			return this.ErrorDescription;
		}

	}

	public void setClickIDSearchTxb(String clickID) {
		Element clickIDSearchTextbox = driver.getElement(clickIDSearchTxb);
		clickIDSearchTextbox.sendKeys(clickID);
	}

	public void clickSearchButton() {
		Element searchButton = driver.getElement(searchBtn);
		searchButton.click();
	}

	public String getEnrolled() {
		driver.getElement(viewLogBtn);
		try {
			Element enrolledText = driver.findElement(enrolledTxt);
			String str = enrolledText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}

	public String getPAResStatus() {
		try {
			Element paresStatusText = driver.findElement(paresStatusTxt);
			String str = paresStatusText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}

	public String getSignatureVerification() {
		try {
			Element signatureVerificationText = driver.findElement(signatureVerificationTxt);
			String str = signatureVerificationText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}

	public String getCavv() {
		try {
			Element cavvText = driver.findElement(cavvTxt);
			String str = cavvText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}

	public String getEciFlag() {
		try {
			Element eciText = driver.findElement(eciTxt);
			String str = eciText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}
	
	public String getPAResStatusAuth() {
		try {
			Element paresStatusText = driver.findElement(paresStatusTxtAuth);
			String str = paresStatusText.getText();
			return str;
		} catch (Exception e) {
			return "NA";
		}
	}

	public String getSignatureVerificationAuth() {
		try {
			Element signatureVerificationText = driver.findElement(signatureVerificationTxtAuth);
			String str = signatureVerificationText.getText();
			return str;
		} catch (Exception e) {
			return "NA";
		}
	}

	public String getCavvAuth() {
		try {
			Element cavvText = driver.findElement(cavvTxtAuth);
			String str = cavvText.getText();
			return str;
		} catch (Exception e) {
			return "NA";
		}
	}

	public String getEciFlagAuth() {
		try {
			Element eciText = driver.findElement(eciTxtAuth);
			String str = eciText.getText();
			return str;
		} catch (Exception e) {
			return "NA";
		}
	}

	public String getACSUrl() {
		try {
			Element acsUrlText = driver.findElement(acsUrlTxt);
			String str = acsUrlText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}

	public String getPayload() {
		try {
			Element payloadText = driver.findElement(payloadTxt);
			String str = payloadText.getText();
			return str;
		} catch (Exception e) {
			return "Exception";
		}
	}

	public void printValue(String clickID) throws Exception {
		this.setClickIDSearchTxb(clickID);
		this.clickSearchButton();
		System.out.println(this.getEnrolled());
		System.out.println(this.getPAResStatus());
		System.out.println(this.getSignatureVerification());
		System.out.println(this.getCavv());
		System.out.println(this.getEciFlag());
		System.out.println(this.getACSUrl());
		System.out.println(this.getPayload());
		
		System.out.println(this.getPAResStatusAuth());
		System.out.println(this.getSignatureVerificationAuth());
		System.out.println(this.getCavvAuth());
		System.out.println(this.getEciFlagAuth());
		
//		ResponseDS response = this.getResponse();
//		System.out.println(response.getEnrolled());
//		System.out.println(response.getPAResStatus());
//		System.out.println(response.getSignatureVerification());
//		System.out.println(response.getCavv());
//		System.out.println(response.getEciFlag());
//		System.out.println(response.getACSUrl());
//		System.out.println(response.getPayload());
//		System.out.println(response.getErrorNo());
//		System.out.println(response.getErrorDesc());
//		System.out.println("_______________________________________________");
	}
}
