package automation.project3ds.oldTest;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.project3ds.MySoftAssertAll;
import automation.project3ds.PrettyPrint;

	

public class Aa {
	String hostValue = null;
	
	@Parameters({"hostValue"})
	@BeforeClass
	public void setUp(@Optional("url") String hostValue) {
		this.hostValue = hostValue;
	}
	
	@DataProvider(name = "data")
	public Object[][] data() {
		String param1 = "param1Value";
		String param2 = "param2Value";
		Object[][] obj = {{param1, hostValue, param2}, {param1, hostValue, param2}};
		return obj;
	}
	
	private void killRemain() throws Exception {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
//				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
	
	@Test(dataProvider = "data")
	public void meo(String param1, String hostValue, String param2) throws Exception {
		this.killRemain();
//		String meo = "<CardinalMPI><ErrorNo>0</ErrorNo><TransactionId>g5nlVAKeLTOeBr2oSfs0</TransactionId><Payload></Payload><ErrorDesc></ErrorDesc><Cavv>Y2FyZGluYWxjb21tZXJjZWF1dGg=</Cavv><PAResStatus>Y</PAResStatus><Enrolled>Y</Enrolled><ACSTransactionId>514e2a2e-913c-4714-96ae-d498964723a6</ACSTransactionId><EciFlag>05</EciFlag><ACSUrl></ACSUrl><ThreeDSServerTransactionId>096d40af-80eb-4e1a-b6bb-d616656fe6a2</ThreeDSServerTransactionId><CardBin>400000</CardBin><CardBrand>VISA</CardBrand><DSTransactionId>feadbd01-4240-4769-af73-25a5e01867a1</DSTransactionId><ThreeDSVersion>2.2.0</ThreeDSVersion><OrderId>8000335856724672</OrderId><ChallengeRequired></ChallengeRequired><SignatureVerification>Y</SignatureVerification></CardinalMPI>";
		String meo = "{\"method\":\"AUTH\",\"request\":{\"type\":\"AUTH\",\"merchant\":{\"merchantId\":\"63103550\",\"name\":\"Embarcadero\"},\"merchantRef\":\"d65563082\",\"transactionId\":\"d65563082-36701\",\"operationId\":\"d65563082-36701-5d26ed7912860\",\"plainCardData\":{\"cardNumber\":\"400000******1000\",\"expiry\":\"2201\",\"cvv2\":\"***\"},\"amount\":{\"isoAmount\":78,\"currency\":\"EUR\"},\"ecomReqData\":{\"allow3dsDowngrade\":true,\"cardholderVerificationMethod\":\"THREEDS\",\"cardReadingMethod\":\"ECOM\",\"threeDSecure\":{\"xid\":\"\",\"cavv\":\"Y2FyZGluYWxjb21tZXJjZWF1dGg=\",\"version\":\"V2\",\"directoryServerTransactionId\":\"feadbd01-4240-4769-af73-25a5e01867a1\"}},\"transactionTimestamp\":\"2019-07-11T10:04:09\",\"retrievalRefNumber\":\"000000036701\"},\"headers\":{\"Content-Type\":\"application\\/json\",\"Authorization\":\"Bearer eyJ*****dTIRTF4WUg\"}}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(meo);
		String out =PrettyPrint.formatJson(jsonNode);
		System.out.println(out);
		
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		  Date date = new Date(stamp.getTime());
		  SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd_hh-mm");
		  String formattedDate = sdf.format(date);
	System.out.println("test do working");
	System.out.println(formattedDate);
	MySoftAssertAll assertion = new MySoftAssertAll();
	assertion.assertEquals(1,0, "[MESSAGE]");
	assertion.assertNotEquals("meo","meo", "[MESSAGE]");
	assertion.assertNotEquals(null,null, "[MESSAGE]");
	assertion.assertEquals("meo", null, "[MESSAGE]");
	assertion.assertNull("meo", "[MESSAGE]");
	assertion.assertNotNull(null, "[MESSAGE]");
	assertion.assertEquals("meo", "gau", "[MESSAGE]");
	assertion.assertAll();
	}
}
